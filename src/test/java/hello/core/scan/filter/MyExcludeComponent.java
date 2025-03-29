package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //TYPE -> 클래스에 붙는 것이다
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent { //해당 Annotation 이 붙으면 Component Scan 에 제외하겠다
}
