//package com.monitortools.api.util;
//
//import com.nimbusds.jose.JWSObject;
//import com.nimbusds.jose.jwk.JWK;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.proc.BadJOSEException;
//import com.nimbusds.jwt.SignedJWT;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.net.URL;
//import java.text.ParseException;
//import java.util.List;
//
//@Component
//public class JwtUtils {
//
//    @Value("${aws.cognito.jwksUrl}")
//    private String jwksUrl;
//
//    public boolean validateToken(String token) {
//        try {
//            SignedJWT signedJWT = SignedJWT.parse(token);
//            JWKSet jwkSet = JWKSet.load(new URL(jwksUrl));
//            List<JWK> keys = jwkSet.getKeys();
//            return !keys.isEmpty(); // Validação simplificada; em prod deve verificar assinatura
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String extractUsername(String token) {
//        try {
//            JWSObject jwsObject = JWSObject.parse(token);
//            //return jwsObject.getPayload().toJSONObject().getAsString("username");
//            return (String) jwsObject.getPayload().toJSONObject().get("username");
//          
//        } catch (ParseException e) {
//            return null;
//        }
//    }
//}
