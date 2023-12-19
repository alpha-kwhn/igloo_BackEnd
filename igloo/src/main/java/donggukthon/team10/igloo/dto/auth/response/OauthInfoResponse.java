package donggukthon.team10.igloo.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OauthInfoResponse(
        String id,
        String email,
        String name,
        String picture,
        String locale,
        @JsonProperty("verified_email") Boolean verifiedEmail,
        @JsonProperty("given_name") String givenName,
        @JsonProperty("family_name") String familyName
) {}