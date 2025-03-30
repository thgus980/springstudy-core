package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
//@Primary // RateDiscountPolicy 가 무조건 먼저 선택되도록
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent=10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade()== Grade.VIP) {
            return price * discountPercent/100;
        }else {
            return 0;
        }
    }
}
