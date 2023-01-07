package challenge.deploy.server.util;

import challenge.deploy.server.domain.member.entity.Member;

public class MemberTestUtil {
    private static Long idx = 1L;
    public static Member getMember() {
        return Member.of("test", "1234", "test@test.com", "TEST");
    }

    public static Long getIdx() {
        return idx++;
    }
}
