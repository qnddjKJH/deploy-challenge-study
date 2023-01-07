package challenge.deploy.server.domain.member.service;

import challenge.deploy.server.domain.member.entity.Member;
import challenge.deploy.server.domain.member.repository.MemberReadRepository;
import challenge.deploy.server.util.MemberTestUtil;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class MemberReadServiceTest {

    @Mock
    private MemberReadRepository memberReadRepository;

    @InjectMocks
    private MemberReadService memberReadService;


    @Test
    public void findById_test() {
        Member member = MemberTestUtil.getMember();
        Long idx = MemberTestUtil.getIdx();
        ReflectionTestUtils.setField(member, "id", idx);

        doReturn(Optional.of(member)).when(memberReadRepository)
                .findById(idx);

        Member target = memberReadService.findById(idx);

        assertThat(target.getId()).isEqualTo(idx);
    }

    @Test
    public void findById_fail_test() {
        Member member = MemberTestUtil.getMember();
        Long idx = MemberTestUtil.getIdx();
        ReflectionTestUtils.setField(member, "id", idx);

        doReturn(Optional.empty()).when(memberReadRepository)
                .findById(idx);

        EntityNotFoundException result = assertThrows(EntityNotFoundException.class,
                () -> memberReadService.findById(idx));

        assertThat(result.getMessage()).isEqualTo("index : {" + idx + "} - 회원을 찾지 못하였습니다.");
    }
}