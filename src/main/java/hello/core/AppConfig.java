package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig { //기존 DIP, OCP 원칙 위배 문제를 해결하기 위한 중간 역할. => 구현 객체를 생성한다. 생성자를 통해서 주입(연결) 해준다.
    //애플리케이션 간의 관계를 정해주는 공연 기획자와 같은 역할
    //객체의 생성과 연결은 AppConfig가 담당

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); //생성자 주입. 생성자를 통해서 들어간다. 여기서 구현을 정해주는 것. 의존 관계 주입.
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
