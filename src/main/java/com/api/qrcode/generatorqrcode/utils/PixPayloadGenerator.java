package com.api.qrcode.generatorqrcode.utils;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class PixPayloadGenerator {

    private final String pixKey;
    private final String merchantName;
    private final String merchantCity;
    private final String amount;
    private final String txid;

    public PixPayloadGenerator(String pixKey, String merchantName, String merchantCity, String amount, String txid) {
        this.pixKey = pixKey;
        this.merchantName = merchantName;
        this.merchantCity = merchantCity;
        this.amount = amount;
        this.txid = txid;
    }

    public String getPayload() {
        String gui = format("00", "BR.GOV.BCB.PIX");
        String key = format("01", pixKey);
        String merchantAccountInfo = format("26", gui + key);

        String merchantCategoryCode = format("52", "0000");
        String transactionCurrency = format("53", "986");
        String transactionAmount = amount != null && !amount.isEmpty() ? format("54", amount) : "";
        String countryCode = format("58", "BR");
        String merchantNameField = format("59", merchantName);
        String merchantCityField = format("60", merchantCity);
        String txidField = format("62", format("05", txid));

        String payload = "000201" +
                merchantAccountInfo +
                merchantCategoryCode +
                transactionCurrency +
                transactionAmount +
                countryCode +
                merchantNameField +
                merchantCityField +
                txidField;

        String crc16 = generateCRC16(payload + "6304");
        payload += "6304" + crc16;

        return payload;
    }

    private String format(String id, String value) {
        return id + String.format("%02d", value.length()) + value;
    }

    private String generateCRC16(String payload) {
        int crc = 0xFFFF;
        for (byte b : payload.getBytes(StandardCharsets.UTF_8)) {
            crc ^= (b & 0xFF) << 8;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0) {
                    crc = (crc << 1) ^ 0x1021;
                } else {
                    crc <<= 1;
                }
                crc &= 0xFFFF;
            }
        }
        return String.format(Locale.ROOT, "%04X", crc);
    }
}
