package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class singletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너") //문제점을 살펴보자 (스프링의 효과와 비교하기 위해)
    void pureContainer() {
        AppConfig appConfig=new AppConfig();
        //1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1=appConfig.memberService();

        //2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2=appConfig.memberService();

        // 참조 값이 다른 것을 확인, 고객 요청이 많은 웹 애플리케이션에서는 문제점
        System.out.println("memberService1 = "+memberService1); //memberService1 = hello.core.member.MemberServiceImpl@6a57ae10 -> 뒷 숫자 주목
        System.out.println("memberService2 = "+memberService2); //memberService2 = hello.core.member.MemberServiceImpl@797b0699

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);


    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // new SingletonService(); 불가능 -> error: SingletonService() has private access
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = "+singletonService1); //singletonService1 = hello.core.singleton.SingletonService@797b0699
        System.out.println("singletonService2 = "+singletonService2); //singletonService2 = hello.core.singleton.SingletonService@797b0699 뒷 숫자 동일 -> 같은 인스턴스를 가져온다

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

    }
}
