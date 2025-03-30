package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 붙은 필드를 생성자 메소드에서 초기화 해주는 코드 대신 해줌
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository=new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy=new RateDiscountPolicy();
    //위 코드 문제점: 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 수정해야한다
    //추상(인터페이스, DiscountPolicy)뿐만 아니라 구체(구현)클래스(RateDiscountPolicy, FixDiscountPolicy)에도 의존하고 있다
    //DIP(인터페이스에만 의존해야한다) 위반!
    //클라이언트 쪽 코드도 수정해야하기 때문에 OCP 위반! (지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다)

    //해결법
    private final MemberRepository memberRepository; //인터페이스에만 의존
    private final DiscountPolicy discountPolicy;

    @Autowired //생성자에 붙이는 Annotation. 자동 의존 관계 주입. 생성자 하나뿐이라 생략 가능.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
   }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discountPrice=discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice); //최종 생성된 주문 반환
    }
    
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
