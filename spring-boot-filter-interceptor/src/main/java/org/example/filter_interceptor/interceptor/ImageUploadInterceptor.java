package org.example.filter_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.mock.web.MockMultipartFile;

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

    // 文件大小限制
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int MAX_WIDTH = 1920; // 最大宽度
    private static final int MAX_HEIGHT = 1080; // 最大高度
    // 可上传类型
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

                // 添加水印
                String watermarkText = "ys666"; // 可以自定义水印内容
                BufferedImage watermarkedImage = addWatermark(img, watermarkText);

                // 创建新的 MultipartFile
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(watermarkedImage, "png", baos);
                byte[] bytes = baos.toByteArray();
                MultipartFile newFile = new MockMultipartFile("file", file.getOriginalFilename(), "image/png", bytes);

                // 生成唯一的文件名
                String objectName = generateUniqueFileName(file.getOriginalFilename());
                uploadFileToOSS(objectName, newFile);
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

    private BufferedImage addWatermark(BufferedImage originalImage, String watermarkText) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage watermarked = new BufferedImage(width, height, originalImage.getType());

        Graphics2D g = (Graphics2D) watermarked.getGraphics();
        g.drawImage(originalImage, 0, 0, null);

        // 设置水印的透明度和字体
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.RED);
        g.drawString(watermarkText, width / 5, height / 2); // 在中心位置绘制水印

        g.dispose();
        return watermarked;
    }

    private String generateUniqueFileName(String originalFilename) {
        // 获取文件扩展名
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        // 生成唯一标识符
        String uniqueId = UUID.randomUUID().toString();
        // 返回唯一文件名
        return uniqueId + extension;
    }
}
