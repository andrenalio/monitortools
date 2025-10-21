package com.monitortools.api.config;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CognitoSecretHashUtil {

	public static String calculateSecretHash(String username, String clientId, String clientSecret) {
        try {
            String message = username + clientId;
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(clientSecret.getBytes("UTF-8"), "HmacSHA256");
            mac.init(keySpec);
            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular SECRET_HASH", e);
        }
    }
}
