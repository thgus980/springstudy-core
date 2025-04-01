package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {

        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(PrototypeBean.class); // PrototypeBean.class 가 component scan 대상이 되면서 bean 으로도 등록된다
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1=ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2=ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = "+prototypeBean1); //prototypeBean1 = hello.core.scope.PrototypeTest$PrototypeBean@2c1b9e4b
        System.out.println("prototypeBean2 = "+prototypeBean2); //prototypeBean2 = hello.core.scope.PrototypeTest$PrototypeBean@757d6814

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        
        prototypeBean1.destroy(); // 종료 메소드 직접 수작업 필요
        prototypeBean2.destroy();
        ac.close(); //프로토타입은 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다. 종료 메소드가 호출되지 않는다.

    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
