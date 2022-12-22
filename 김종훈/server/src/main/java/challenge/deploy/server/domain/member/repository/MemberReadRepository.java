package challenge.deploy.server.domain.member.repository;

import challenge.deploy.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberReadRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(@Param("username") String username);
}
