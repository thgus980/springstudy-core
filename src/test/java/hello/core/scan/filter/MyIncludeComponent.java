package hello.core.scan.filter;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //TYPE -> 클래스에 붙는 것이다
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
