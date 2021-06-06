package kr.ac.pcu.cyber.authresourceservice.model.dto.kakao;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoTokenIssuanceRequestData {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String code;
}
