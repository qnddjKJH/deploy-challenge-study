package challenge.deploy.server.domain.member.repository;

import challenge.deploy.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberWriteRepository extends JpaRepository<Member, Long> {
}
