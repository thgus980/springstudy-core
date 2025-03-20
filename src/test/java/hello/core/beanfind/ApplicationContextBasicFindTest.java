package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService=ac.getBean("memberService",MemberService.class);
        System.out.println("memberService= "+memberService); //memberService= hello.core.member.MemberServiceImpl@2ca65ce4. memberService 는 바로 윗 줄에서 variable 로 선언했기 때문에 출력 가능하게 되는 것.
        System.out.println("memberService.getClass()= "+memberService.getClass()); //memberService.getClass()= class hello.core.member.MemberServiceImpl
        System.out.println("memberServiceImpl = "+MemberServiceImpl.class); //class hello.core.member.MemberServiceImpl
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //class 단에서 비교
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService=ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByType2(){
        MemberServiceImpl memberService=ac.getBean(MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패 테스트")
    void findBeanByNameX() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXX", MemberServiceImpl.class)); //해당 메서드를 실행하면 NoSuchBeanDefinitionException 오류가 터져야한다 -> 해당 오류 터지면 테스트 성공으로 처리
    }

}
