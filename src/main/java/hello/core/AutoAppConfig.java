package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ComponentScan -> 클래스에 있는 것들 자동으로 스프링 빈 등록
@ComponentScan (
        //basePackages ="hello.core.member", // 이 package 부터 ComponentScan 의 대상이 된다, 지정하지 않으면 AutoAppConfig 가 속한 패키지인 hello.core 부터 뒤져본다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //Configuration 제외하겠다. AppConfig에서 수동으로 등록한 Bean과의 충돌 방지를 위해. (예제를 안전하게 실행하기 위해!)
)
public class AutoAppConfig {
}
