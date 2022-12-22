package challenge.deploy.server.domain.member.dto;

import challenge.deploy.server.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class MemberRequestDto {
    @Builder
    @AllArgsConstructor
    @Data
    public static class Join {
        private String username;
        private String password;
        private String email;
        private String name;

        public Member toEntity() {
            return Member.of(
                    username,
                    password,
                    email,
                    name
            );
        }
    }

    @AllArgsConstructor
    @Data
    public static class Withdraw {
        private String username;
    }
}
