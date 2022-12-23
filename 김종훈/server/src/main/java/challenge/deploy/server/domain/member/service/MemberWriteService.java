package challenge.deploy.server.domain.member.service;

import challenge.deploy.server.domain.member.dto.MemberRequestDto;
import challenge.deploy.server.domain.member.repository.MemberWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberWriteService {
    private final MemberWriteRepository memberWriteRepository;
    private final MemberReadService memberReadService;

    public Long register(MemberRequestDto.Join joinDto) {
        return memberWriteRepository.save(joinDto.toEntity()).getId();
    }

    public void withdraw(MemberRequestDto.Withdraw withdrawDto) {
        memberWriteRepository.delete(memberReadService.findByUsername(withdrawDto.getUsername()));
    }
}
