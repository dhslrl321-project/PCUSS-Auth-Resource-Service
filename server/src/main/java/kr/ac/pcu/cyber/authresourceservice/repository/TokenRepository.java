package kr.ac.pcu.cyber.authresourceservice.repository;

import kr.ac.pcu.cyber.authresourceservice.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenIdAndType(String tokenId, String type);
}
