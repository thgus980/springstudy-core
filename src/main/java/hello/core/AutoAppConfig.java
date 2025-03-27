package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ComponentScan -> 클래스에 있는 것들 자동으로 스프링 빈 등록
@ComponentScan (
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //Configuration 제외하겠다. AppConfig에서 수동으로 등록한 Bean과의 충돌 방지를 위해. (예제를 안전하게 실행하기 위해!)
)
public class AutoAppConfig {
}
