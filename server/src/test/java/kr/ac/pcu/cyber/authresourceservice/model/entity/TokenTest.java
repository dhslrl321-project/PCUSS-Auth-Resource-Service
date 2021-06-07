package kr.ac.pcu.cyber.authresourceservice.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    private final Token token = Token.builder()
            .id(1L)
            .userId("uuid")
            .type("type")
            .tokenId("tokenId")
            .accessToken("accessToken")
            .refreshToken("refreshToken")
            .build();

    @Test
    void update() {
        String accessTokenForUpdate = "updateAccessToken";
        String refreshTokenForUpdate = "updateRefreshToken";

        token.update(accessTokenForUpdate, refreshTokenForUpdate);

        assertAll(
                () -> assertEquals(accessTokenForUpdate, token.getAccessToken()),
                () -> assertEquals(refreshTokenForUpdate, token.getRefreshToken())
        );
    }

}