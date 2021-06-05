package kr.ac.pcu.cyber.authresourceservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestData {
    private String email;
    private String nickname;
    private String pictureUrl;
}
