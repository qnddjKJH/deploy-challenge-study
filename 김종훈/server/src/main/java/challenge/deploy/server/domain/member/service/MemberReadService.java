package challenge.deploy.server.domain.member.service;

import challenge.deploy.server.domain.member.entity.Member;
import challenge.deploy.server.domain.member.repository.MemberReadRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberReadService {

    private final MemberReadRepository memberReadRepository;

    public Member findById(Long id) {
        return memberReadRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("index : {" + id + "} - 회원을 찾지 못하였습니다.")
        );
    }

    public Member findByUsername(String username) {
        return memberReadRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("index : {" + username + "} - 회원을 찾지 못하였습니다.")
        );
    }

    public List<Member> findAll() {
        return memberReadRepository.findAll();
    }
}
