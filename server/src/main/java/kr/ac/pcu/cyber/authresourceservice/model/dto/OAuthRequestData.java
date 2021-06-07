package kr.ac.pcu.cyber.authresourceservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthRequestData {
    private String code;
}
