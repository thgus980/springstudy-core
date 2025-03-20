package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {

        String[] beanDefinitionNames= ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean=ac.getBean(beanDefinitionName); //어떤 type 인지 모르니까 여러 타입의 객체를 유연하게 처리할 수 있는 최상위 클래스 Object 타입 사용
            System.out.println("name = "+beanDefinitionName+" object = "+bean);
        }

    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기 (스프링 기본 내부 빈 제외)")
    void findApplicationBean() {

        String[] beanDefinitionNames= ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition=ac.getBeanDefinition(beanDefinitionName); //Bean 하나하나에 대한 정보

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION) {
                Object bean=ac.getBean(beanDefinitionName); //어떤 type 인지 모르니까 여러 타입의 객체를 유연하게 처리할 수 있는 최상위 클래스 Object 타입 사용
                System.out.println("name = "+beanDefinitionName+" object = "+bean);
            }

        }
    }
}

