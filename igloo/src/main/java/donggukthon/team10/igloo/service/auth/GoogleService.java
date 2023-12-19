package donggukthon.team10.igloo.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.dto.auth.response.GoogleTokenResponse;
import donggukthon.team10.igloo.dto.auth.response.LoginResponse;
import donggukthon.team10.igloo.dto.auth.response.OauthInfoResponse;
import donggukthon.team10.igloo.exception.AuthException;
import donggukthon.team10.igloo.exception.CustomErrorCode;
import donggukthon.team10.igloo.repository.UserRepository;
import donggukthon.team10.igloo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${GOOGLE_AUTH_URL}")
    private String url;

    @Value("${CALLBACK_URL}")
    private String callback;

    @Value("${GOOGLE_SCOPE}")
    private String scope;

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${CLIENT_SECRET}")
    private String clientSecret;

    @Value("${GOOGLE_TOKEN_URL}")
    private String tokenUrl;

    @Value("${GOOGLE_INFO_URL}")
    private String infoUrl;

    public String getCallbackUrl() {
        return url + "?scope=" + scope + "&client_id=" + clientId + "&redirect_uri=" + callback + "&response_type=code";
    }

    public String getAccessCode(String code) {

        // 파라미터 구성
        Map<String, String> param = new HashMap<>();
        param.put("code", code);
        param.put("client_id", clientId);
        param.put("client_secret", clientSecret);
        param.put("redirect_uri", callback);
        param.put("grant_type", "authorization_code");

        // POST로 구글서버로부터 토큰 발급 요청

        try {
            GoogleTokenResponse responseEntity = restTemplate.postForEntity(
                    tokenUrl,
                    param,
                    GoogleTokenResponse.class).getBody();

            if (responseEntity != null) {
                return responseEntity.accessToken();
            } else {
                throw new AuthException(CustomErrorCode.GOOGLE_TOKEN_RESPONSE_ERROR);
            }
        } catch (HttpClientErrorException e) {
            throw new AuthException(CustomErrorCode.INVALID_GOOGLE_CODE);
        } catch (HttpServerErrorException e) {
            throw new AuthException(CustomErrorCode.GOOGLE_SERVER_ERROR);
        }
    }

    public OauthInfoResponse requestUserInfo(String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    infoUrl,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.getBody(), OauthInfoResponse.class);

        } catch (HttpClientErrorException e) {
            throw new AuthException(CustomErrorCode.INVALID_GOOGLE_TOKEN);
        } catch (HttpServerErrorException e) {
            throw new AuthException(CustomErrorCode.GOOGLE_SERVER_ERROR);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
