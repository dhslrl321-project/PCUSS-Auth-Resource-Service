package kr.ac.pcu.cyber.authresourceservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseData {
    private Long id;
    private String userId;
    private String accessToken;
    private String refreshToken;
    private String nickname;
    private String profileImage;
}
