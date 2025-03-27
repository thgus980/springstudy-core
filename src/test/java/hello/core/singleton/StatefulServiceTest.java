package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1=ac.getBean(StatefulService.class);
        StatefulService statefulService2=ac.getBean(StatefulService.class);

        //ThreadA : A 사용자 10000원 주문
        statefulService1.order("userA",10000);
        //ThreadB : B 사용자 20000원 주문 (중간에 사용자 B가 끼어들었다 - 사용자 B가 바꿔버림. 인스턴스를 같은 것을 쓰니까.)
        statefulService2.order("userB",20000);

        //ThreadA: 사용자 A 주문 금액 조회
        int price=statefulService1.getPrice();
        System.out.println("price = "+price); // 20000 나와버림

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig { // 다른 클래스 내에 클래스를 정의할 때 붙는 static

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }

}