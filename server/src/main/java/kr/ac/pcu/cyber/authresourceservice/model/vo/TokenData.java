package kr.ac.pcu.cyber.authresourceservice.model.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenData {
    private String tokenId;
    private String accessToken;
    private String refreshToken;
    private String name;
    private String email;
    private String profileImage;
}
