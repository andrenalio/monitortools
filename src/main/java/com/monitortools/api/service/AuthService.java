package com.monitortools.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.monitortools.api.config.CognitoSecretHashUtil;
import com.monitortools.api.dto.AuthRequestDTO;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthFlowType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.InitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.InitiateAuthResponse;

@Service
public class AuthService {

    @Value("${aws.cognito.region}")
    private String region;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.clientSecret}")
    private String clientSecret;
    
    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;
    
    public Map<String, String> authenticate(AuthRequestDTO request) {
        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.of(region))
                .build();

        Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", request.getUsername());
        authParams.put("PASSWORD", request.getPassword());
        authParams.put("SECRET_HASH", CognitoSecretHashUtil.calculateSecretHash(request.getUsername(), clientId, clientSecret));


        InitiateAuthRequest authRequest = InitiateAuthRequest.builder()
                .authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .clientId(clientId)
                .authParameters(authParams)
                .build();

        InitiateAuthResponse response = cognitoClient.initiateAuth(authRequest);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", response.authenticationResult().accessToken());
        tokens.put("idToken", response.authenticationResult().idToken());
        tokens.put("refreshToken", response.authenticationResult().refreshToken());

        return tokens;
    }
    
//    public ResponseEntity<?> authenticate(@RequestBody AuthRequestDTO request) {
//        String url = String.format("https://cognito-idp.%s.amazonaws.com/", region);
//
//        Map<String, Object> body = new HashMap<>();
//        body.put("AuthParameters", Map.of(
//                "USERNAME", request.getUsername(),
//                "PASSWORD", request.getPassword()
//        ));
//        body.put("AuthFlow", "USER_PASSWORD_AUTH");
//        body.put("ClientId", clientId);
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<Map> response = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                entity,
//                Map.class
//        );
//
//        return ResponseEntity.ok(response.getBody());
//    }
}