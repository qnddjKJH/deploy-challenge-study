package challenge.deploy.server.domain.member.dto;

import challenge.deploy.server.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MemberResponseDto {
    private Long id;
    private String username;
    private String email;
    private String name;

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .name(this.name)
                .build();
    }
}
