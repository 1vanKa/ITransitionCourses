package main;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC {
    protected static byte[] hmacSHA256(byte[] secretKey, byte[] message) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA256");
            mac.init(sks);
            return mac.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMACSHA3-256 encrypt", e);
        }
    }

    protected static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length*2);
        for(byte b: bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
