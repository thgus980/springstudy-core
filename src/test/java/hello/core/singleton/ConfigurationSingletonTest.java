package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService=ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService=ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository=ac.getBean("memberRepository",MemberRepository.class);

        MemberRepository memberRepository1=memberService.getMemberRepository();
        MemberRepository memberRepository2=orderService.getMemberRepository();

        //3개의 스프링빈이 다 똑같음. 같은 인스턴스가 조회됨. 스프링이 싱글톤을 보장해주는구나.
        System.out.println("memberService -> memberRepository = "+memberRepository1); //memberService -> memberRepository = hello.core.member.MemoryMemberRepository@2f3c6ac4
        System.out.println("orderService -> memberRepository = "+memberRepository2); //orderService -> memberRepository = hello.core.member.MemoryMemberRepository@2f3c6ac4
        System.out.println("memberRepository = "+memberRepository); //memberRepository = hello.core.member.MemoryMemberRepository@2f3c6ac4

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);//이때 AppConfig 도 스프링빈으로 등록이 됨
        AppConfig bean=ac.getBean(AppConfig.class);

        System.out.println("bean = "+bean.getClass()); //bean = class hello.core.AppConfig$$SpringCGLIB$$0
        //순수한 클래스라면 bean = class hello.core.AppConfig 출력이 되어야 함
        //내가 만든 클래스가 아니라 스프링이 조작해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것
    }
}
