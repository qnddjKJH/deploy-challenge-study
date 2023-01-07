package challenge.deploy.server.domain.member.service;

import challenge.deploy.server.domain.member.dto.MemberRequestDto;
import challenge.deploy.server.domain.member.entity.Member;
import challenge.deploy.server.domain.member.repository.MemberWriteRepository;
import challenge.deploy.server.util.MemberTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class MemberWriteServiceTest {

    @Mock
    private MemberReadService memberReadService;
    @Mock
    private MemberWriteRepository memberWriteRepository;

    @InjectMocks
    private MemberWriteService memberWriteService;

    @Test
    public void member_join() {
        MemberRequestDto.Join joinRequest = MemberRequestDto.Join.builder()
                .username("test")
                .password("1234")
                .email("test@test.com")
                .name("TEST")
                .build();


        Member member = joinRequest.toEntity();
        ReflectionTestUtils.setField(member, "id", -1L);

        // memberWriteRepository save(어떠한 Member.class) 메서드 실행시 doReturn - member 를 리턴하겠다
        doReturn(member).when(memberWriteRepository)
                .save(any(Member.class));

        Long savedId = memberWriteService.register(joinRequest);

        assertThat(savedId).isEqualTo(member.getId());
        // 메서드 실행 횟수 확인
        verify(memberWriteRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void member_delete() {
        MemberRequestDto.Withdraw withdraw = new MemberRequestDto.Withdraw("test");

        Member member = MemberTestUtil.getMember();
        ReflectionTestUtils.setField(member, "id", MemberTestUtil.getIdx());

        doReturn(member).when(memberReadService).findByUsername(any(String.class));

        memberWriteService.withdraw(withdraw);

        verify(memberReadService, times(1)).findByUsername(any(String.class));
        verify(memberWriteRepository, times(1)).delete(any(Member.class));
    }
}