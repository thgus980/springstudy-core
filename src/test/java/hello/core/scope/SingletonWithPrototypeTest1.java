package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUserPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1=ac.getBean(ClientBean.class);
        int count1=clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2=ac.getBean(ClientBean.class);
        int count2=clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);


    }

    @Scope("singleton")
    static class ClientBean{
//        private final PrototypeBean prototypeBean; //생성 시점에 주입

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean){ //생성자 주입
//            this.prototypeBean=prototypeBean;
//        }

        @Autowired
        private Provider<PrototypeBean> prototypeBeanObjectProvider; //필드 주입


        public int logic() {
            PrototypeBean prototypeBean=prototypeBeanObjectProvider.get(); // 항상 새로운 프로토타입 빈이 생성됨.
            prototypeBean.addCount();
            int count=prototypeBean.getCount();
            return count;
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count=0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init "+this); //this 는 나를 가리킴. 나의 참조값을 볼 수 있다. // PrototypeBean.init hello.core.scope.SingletonWithPrototypeTest1$PrototypeBean@319988b0 // PrototypeBean.init hello.core.scope.SingletonWithPrototypeTest1$PrototypeBean@32fe9d0a
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy"); //수동으로 지정하지 않는 이상 호출 안 됨
        }
    }
}
