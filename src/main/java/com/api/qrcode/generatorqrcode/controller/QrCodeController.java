package com.api.qrcode.generatorqrcode.controller;

import com.api.qrcode.generatorqrcode.services.QRCodeGeneratorService;
import com.api.qrcode.generatorqrcode.utils.PixPayloadGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/qrcode")
@RequiredArgsConstructor
public class QrCodeController {
    private final QRCodeGeneratorService qrCodeGeneratorService;

    @GetMapping(value = "/generatePixQRCode", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generatePixQRCode(
            @RequestParam String pixKey,
            @RequestParam String nome,
            @RequestParam String cidade,
            @RequestParam(required = false) String valor,
            @RequestParam(defaultValue = "***") String txid
    ) throws Exception {
        String payload = new PixPayloadGenerator(pixKey, nome, cidade, valor, txid).getPayload();

        return qrCodeGeneratorService.generateQRCodeImage(payload, 300, 300);
    }
}
