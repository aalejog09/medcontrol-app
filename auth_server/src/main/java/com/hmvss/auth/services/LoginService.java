package com.hmvss.auth.services;

import com.hmvss.auth.config.properties.SecurityProperties;
import com.hmvss.auth.config.properties.ServerProperties;
import com.hmvss.auth.dto.token.OriginalTokenResponse;
import com.hmvss.auth.dto.token.RefreshTokenDTO;
import com.hmvss.auth.dto.token.TokenResponseDTO;
import com.hmvss.auth.dto.token.TokenRequestDTO;
import com.hmvss.auth.persistence.mapper.TokenMapper;
import com.hmvss.auth.services.interfaces.ILoginService;
import com.hmvss.auth.util.exceptions.APIError;
import com.hmvss.auth.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
class LoginService implements ILoginService {

    @Autowired
    TokenMapper tokenMapper;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public TokenResponseDTO getToken(TokenRequestDTO userRequest) {
        OriginalTokenResponse response;
        try {
            response = getTokenResponse(userRequest);
        } catch (Exception e) {
            throw new APIException(APIError.VALIDATION_ERROR);
        }
        return tokenMapper.mapTokenDTO(response);
    }


    @Override
    public TokenResponseDTO getRefreshToken(RefreshTokenDTO request) {
        OriginalTokenResponse response;
        try {
            response = getRefreshTokenResponse(request);
        } catch (Exception e) {
            throw new APIException(APIError.TOKEN_EXPIRED);
        }
        return tokenMapper.mapTokenDTO(response);
    }


    public OriginalTokenResponse getTokenResponse(TokenRequestDTO userRequest){
        ResponseEntity<OriginalTokenResponse> result = sendRequestToken(getResponseForToken(userRequest));
        return result.getBody();
    }

    private ResponseEntity<OriginalTokenResponse> sendRequestToken(HttpEntity<MultiValueMap<String, String>> response){
        String uri = serverProperties.getOauthRq();
        RestTemplate restTemplate = new RestTemplate();
        log.info("requesting token to : "+uri);
        return  restTemplate.postForEntity( uri, response , OriginalTokenResponse.class );
    }

    private HttpEntity<MultiValueMap<String, String>> getResponseForToken(TokenRequestDTO userRequest){
        return getMultiValueForTokenRequest(userRequest);
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueForTokenRequest(TokenRequestDTO userRequest) {
        MultiValueMap<String, String> requestParams= new LinkedMultiValueMap<String, String>();
        requestParams.addAll(getRequestParamsForToken(userRequest));
        return new HttpEntity<>(requestParams, getHeaders());
    }

    private MultiValueMap<String, String> getRequestParamsForToken(TokenRequestDTO userRequest){
        MultiValueMap<String, String> requestParams= new LinkedMultiValueMap<String, String>();
        requestParams.add("grant_type",securityProperties.getAuthPassword());
        requestParams.add("username", userRequest.getUsername());
        requestParams.add("password", userRequest.getPassword());
        requestParams.add("scope","read,write");
        return requestParams;
    }



    public OriginalTokenResponse getRefreshTokenResponse (RefreshTokenDTO request){
        ResponseEntity<OriginalTokenResponse> result = sendRequestToken(getMultiValueForTokenRefresh(request));
        return result.getBody();
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueForTokenRefresh(RefreshTokenDTO request) {
        MultiValueMap<String, String> requestParams= new LinkedMultiValueMap<String, String>();
        requestParams.addAll(getRequestParamsForRefreshToken(request));
        return new HttpEntity<>(requestParams, getHeaders());
    }

    private MultiValueMap<String, String> getRequestParamsForRefreshToken(RefreshTokenDTO request){
        MultiValueMap<String, String> requestParams= new LinkedMultiValueMap<String, String>();
        requestParams.add("grant_type",securityProperties.getAuthRefreshToken());
        requestParams.add("refresh_token",request.getRefreshToken());
        return requestParams;
    }

    private HttpHeaders getHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(securityProperties.getClientId(),securityProperties.getClientSecret());
        return httpHeaders;
    }

}
