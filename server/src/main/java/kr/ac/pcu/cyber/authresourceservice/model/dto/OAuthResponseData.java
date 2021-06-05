package kr.ac.pcu.cyber.authresourceservice.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthResponseData {
    private Long id;
    private String jwtAccessToken;
    private String jwtRefreshToken;
    private String nickname;
    private String profileImage;
}
