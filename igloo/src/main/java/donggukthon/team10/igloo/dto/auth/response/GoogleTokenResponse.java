package donggukthon.team10.igloo.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record GoogleTokenResponse (
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("expires_in") Long expiresIn,
        @JsonProperty("refresh_token") String refreshToken,
        String scope,
        @JsonProperty("token_type") String tokenType,
        @JsonProperty("id_token") String idToken
) {}
