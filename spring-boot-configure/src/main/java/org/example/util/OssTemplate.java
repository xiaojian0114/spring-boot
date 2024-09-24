package org.example.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import jakarta.annotation.Resource;
import org.example.config.OssConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class OssTemplate {
    @Resource
    private OssConfig ossConfig;

    public String ossUpload(MultipartFile file) {
        String endpoint = ossConfig.getEndpoint();
        String bucket = ossConfig.getBucket();
        String dir = ossConfig.getDir();
        String host = ossConfig.getHost();
        String ak = ossConfig.getAk();
        String secret = ossConfig.getSecret();

        OSS ossClient = new OSSClientBuilder().build(endpoint, ak, secret);

        String uploadPath = dir + file.getOriginalFilename();
        InputStream inputStream = null;
        try{
            inputStream = file.getInputStream();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        ossClient.putObject(bucket, uploadPath, inputStream);

        ossClient.shutdown();

        return host + "/" + uploadPath;
    }
}
