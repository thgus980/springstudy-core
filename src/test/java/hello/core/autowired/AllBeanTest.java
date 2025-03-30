package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac=new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class); //AutoAppConfig 도 등록하고 DiscountService 도 등록

        DiscountService discountService=ac.getBean(DiscountService.class);
        Member member=new Member(1L, "userA", Grade.VIP);
        int discountPrice=discountService.discount(member, 10000, "fixDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice=discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired //생성자 주입 부분에 붙이기
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap=policyMap;
            this.policies=policies;
            System.out.println("policyMap = "+policyMap);//의존관계 주입 확인. policyMap = {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@320e400, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@5167268}
            System.out.println("policies = "+policies);//의존관계 주입 확인. policies = [hello.core.discount.FixDiscountPolicy@320e400, hello.core.discount.RateDiscountPolicy@5167268]

        }

        public int discount(Member member, int price, String discountCode){
            DiscountPolicy discountPolicy=policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }


    }
}
