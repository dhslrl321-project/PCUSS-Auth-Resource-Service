package kr.ac.pcu.cyber.authresourceservice.service;

import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.vo.TokenData;

public interface OAuthService {
    TokenData oauth(OAuthRequestData requestData);
}
