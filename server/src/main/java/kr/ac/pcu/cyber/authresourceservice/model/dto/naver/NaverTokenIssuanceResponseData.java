package kr.ac.pcu.cyber.authresourceservice.model.dto.naver;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverTokenIssuanceResponseData {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
}
