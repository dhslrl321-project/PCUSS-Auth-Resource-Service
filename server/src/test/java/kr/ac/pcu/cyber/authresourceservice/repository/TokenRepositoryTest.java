package kr.ac.pcu.cyber.authresourceservice.repository;

import kr.ac.pcu.cyber.authresourceservice.model.entity.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TokenRepositoryTest {
    private final TokenRepository tokenRepository = mock(TokenRepository.class);

    Token token = Token.builder()
            .id(1L)
            .userId("uuid")
            .type("type")
            .tokenId("tokenId")
            .accessToken("accessToken")
            .refreshToken("refreshToken")
            .build();

    @BeforeEach
    void setUp() {
        given(tokenRepository.findByTokenIdAndType(anyString(), anyString()))
                .willReturn(Optional.of(token));
    }

    @Test
    void find_by_token_id_and_type() {
        Optional<Token> optionalToken = tokenRepository.findByTokenIdAndType("any", "string");

        Token token = optionalToken.get();

        assertAll(
                () -> assertNotNull(token.getId()),
                () -> assertNotNull(token.getUserId()),
                () -> assertNotNull(token.getType()),
                () -> assertNotNull(token.getTokenId()),
                () -> assertNotNull(token.getAccessToken()),
                () -> assertNotNull(token.getRefreshToken())
        );
    }
}