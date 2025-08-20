package com.api.qrcode.generatorqrcode.controller;

import com.api.qrcode.generatorqrcode.services.QRCodeGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/qrcode")
@RequiredArgsConstructor
@Profile("dev")
public class QrCodeController {
    private final QRCodeGeneratorService qrCodeGeneratorService;

    @GetMapping(value = "/generateQRCode", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateQRCode(@RequestParam String text,
                                 @RequestParam(defaultValue = "200") int width,
                                 @RequestParam(defaultValue = "200") int height) throws Exception {
        return qrCodeGeneratorService.generateQRCodeImage(text, width, height);
    }

}
