package kr.ac.pcu.cyber.authresourceservice.model.dto.naver;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NaverTokenIssuanceRequestData {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String code;
    private String state;
}