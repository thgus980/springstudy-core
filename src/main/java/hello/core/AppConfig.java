package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링 적용 시작! 애플리케이션의 구성, 설정 담당
public class AppConfig { //기존 DIP, OCP 원칙 위배 문제를 해결하기 위한 중간 역할. => 구현 객체를 생성한다. 생성자를 통해서 주입(연결) 해준다.
    //애플리케이션 간의 관계를 정해주는 공연 기획자와 같은 역할
    //객체의 생성과 연결은 AppConfig 가 담당
    //리팩터링(역할과 구현을 명확하게 분리, 역할이 잘 들어남, 중복 제거 / Ctrl+Alt+M 하여 Extract Method). 역할과 구현 클래스가 한 눈에 들어오도록.

    @Bean // 각 메소드에 Bean -> 스프링 컨테이너에 등록됨
    public MemberService memberService() { //역할
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //구현
        // 이렇게 반환된 객체가 스프링 컨테이너에 등록됨. -> 이렇게 등록된 객체를 스프링 빈이라고 한다
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository(); //생성자를 호출하여 생성자 주입. 생성자를 통해서 들어간다. 여기서 구현을 정해주는 것. 의존 관계 주입. 리팩터링.
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy()); //의존관계 주입
    }
    @Bean
    public DiscountPolicy discountPolicy (){
       // return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //OCP 준수 - 소프트웨어 요소는 확장에는 열려있으나 변경에는 닫혀 있어야 한다 (이 부분 외에는 수정할 필요가 없으니까   )
    }

}
