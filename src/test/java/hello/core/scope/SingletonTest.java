package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class); // SingletonBean.class가 컴포넌트 스캔 대상이 되어 빈으로 등록, 이 과정에서 SingletonBean 클래스가 컴포넌트로 등록되고 싱글톤 인스턴스가 생성

        SingletonBean singletonBean1=ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2=ac.getBean(SingletonBean.class);
        System.out.println("singleton1 = "+singletonBean1); //singleton1 = hello.core.scope.SingletonTest$SingletonBean@2c1b9e4b
        System.out.println("singleton2 = "+singletonBean2); //singleton2 = hello.core.scope.SingletonTest$SingletonBean@2c1b9e4b

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton") //이 클래스는 싱글톤 스코프로 정의되었습니다. 따라서, 컨테이너 내에 단 하나의 인스턴스만 생성되고 공유됩니다.
    static class SingletonBean{
        @PostConstruct
        void init() {
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        void destroy() {
            System.out.println("SingletonBean.destroy");
        }

    }
}
