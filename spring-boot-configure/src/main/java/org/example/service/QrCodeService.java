package org.example.service;


import cn.hutool.extra.qrcode.QrCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.File;

@Slf4j
@Service

public class QrCodeService {

    @Value("${custom.qrcode.content}")
    private String qrCodeContent;

    public void generateCode(){
        QrCodeUtil.generate(qrCodeContent,400,400,new File("qrcode.png"));
        log.info("二维码生成成功");


    }

}
