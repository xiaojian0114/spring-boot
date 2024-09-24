package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.QrCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @GetMapping("/qrcode")
    public void qrcode(){
        qrCodeService.generateCode();
    }

}
