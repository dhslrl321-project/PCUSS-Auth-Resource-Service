package kr.ac.pcu.cyber.authresourceservice.model.dto.kakao;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoProfileResponseData {
    private Long id;
    private String connected_at;
    Map<String, Object> properties;
    Map<String, Object> kakao_account;
}
