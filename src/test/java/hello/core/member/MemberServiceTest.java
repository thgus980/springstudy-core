package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class MemberServiceTest {
    //MemberService memberService=new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach //각 테스트 전에 무조건 실행됨. @Test가 2개 있으면 2번 돌아감.
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        memberService= appConfig.memberService();
    }

    @Test
    void join() { //테스트를 잘 작성하는 것이 중요하다!
        //given
        Member member=new Member(1L,"memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember=memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember); // 똑같으면 test 넣고 빼는 게 가능해지는 것
    }

}
