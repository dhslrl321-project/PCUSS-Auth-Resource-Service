package kr.ac.pcu.cyber.authresourceservice.model.dto.google;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleTokenIssuanceRequestData {
    private String code;
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
}
