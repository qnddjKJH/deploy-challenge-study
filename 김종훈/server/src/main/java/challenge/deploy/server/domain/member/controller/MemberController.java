package challenge.deploy.server.domain.member.controller;

import challenge.deploy.server.domain.member.dto.MemberRequestDto;
import challenge.deploy.server.domain.member.dto.MemberResponseDto;
import challenge.deploy.server.domain.member.service.MemberReadService;
import challenge.deploy.server.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {
    private final MemberReadService memberReadService;
    private final MemberWriteService memberWriteService;

    @GetMapping("/{id}")
    public MemberResponseDto findById(@PathVariable Long id) {
        return MemberResponseDto.from(memberReadService.findById(id));
    }

    @GetMapping("/{username}")
    public MemberResponseDto findByUsername(@PathVariable String username) {
        return MemberResponseDto.from(memberReadService.findByUsername(username));
    }

    @PostMapping
    public Long register(@RequestBody MemberRequestDto.Join join) {
        return memberWriteService.register(join);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody MemberRequestDto.Withdraw dto) {
        memberWriteService.withdraw(dto);
        return ResponseEntity.ok("정상적으로 삭제 되었습니다.");
    }
}
