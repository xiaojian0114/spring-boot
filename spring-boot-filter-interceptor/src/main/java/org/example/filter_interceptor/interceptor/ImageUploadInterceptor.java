package org.example.filter_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

@Component
@Slf4j
public class ImageUploadInterceptor implements HandlerInterceptor {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int MAX_WIDTH = 1920; // 最大宽度
    private static final int MAX_HEIGHT = 1080; // 最大高度
    private static final String[] ALLOWED_TYPES = {"image/png", "image/jpeg", "image/gif"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("file");

            if (file != null) {
                // 文件大小检查
                if (file.getSize() > MAX_FILE_SIZE) {
                    log.warn("文件大小超出限制: {} bytes", file.getSize());
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File size exceeds limit");
                    return false;
                }

                // 文件类型检查
                if (!isValidFileType(file)) {
                    log.warn("无效的文件类型: {}", file.getContentType());
                    response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid file type");
                    return false;
                }

                // 图片尺寸检查
                BufferedImage img = ImageIO.read(file.getInputStream());
                if (img.getWidth() > MAX_WIDTH || img.getHeight() > MAX_HEIGHT) {
                    log.warn("图片尺寸超出限制: {}x{}", img.getWidth(), img.getHeight());
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Image size exceeds limit");
                    return false;
                }

                // 上传到阿里云 OSS
                String objectName = file.getOriginalFilename();
                uploadFileToOSS(objectName, file);
            }
        }

        String path = request.getRequestURI();
        String clientIp = request.getRemoteAddr();
        log.info("请求已经到达拦截器：path:{}, clientIp:{}, beginTime:{}", path, clientIp, LocalDateTime.now());
        return true;
    }

    private boolean isValidFileType(MultipartFile file) {
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase() : "";

        // 输出调试信息
        log.info("文件名: {}, MIME类型: {}, 扩展名: {}", originalFilename, contentType, extension);

        // 检查 MIME 类型和扩展名
        return switch (extension) {
            case "png" -> contentType.equals("image/png");
            case "jpg", "jpeg" -> contentType.equals("image/jpeg") || contentType.equals("application/octet-stream"); // 允许 application/octet-stream
            case "gif" -> contentType.equals("image/gif");
            default -> false; // 不支持的类型返回 false
        };
    }


    private void uploadFileToOSS(String objectName, MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            File tempFile = File.createTempFile("upload-", objectName);
            file.transferTo(tempFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, tempFile);
            ossClient.putObject(putObjectRequest);
            log.info("文件上传成功: {}", objectName);
            tempFile.delete(); // 删除临时文件
        } finally {
            ossClient.shutdown();
        }
    }
}
