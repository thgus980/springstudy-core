package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    //순수한 java 코드 버전 (스프링 X) -> 스프링 적용
    public static void main(String[] args) {
        //AppConfig appConfig=new AppConfig();
        //MemberService memberService= appConfig.memberService(); // MemberServiceImpl 을 줌
        //MemberService memberService=new MemberServiceImpl();

        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class); //스프링 시작할 때. 얘가 @Bean 붙인 모든 것을 관리해줌.
        MemberService memberService=applicationContext.getBean("memberService", MemberService.class); //메소드 이름, 타입
        Member member=new Member(1L, "memberA", Grade.VIP); //Long type 이라 L 붙어야함.
        memberService.join(member);

        Member findMember=memberService.findMember(1L);

        System.out.println("new member: "+member.getName());
        System.out.println("find member: "+findMember.getName());
    }
}
