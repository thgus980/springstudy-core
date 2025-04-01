package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //Scope 지정. MyLogger myLogger 는 HTTP 요청 당 각각 구분되므로 다른 HTTP 요청 때문에 값이 섞이는 걱정 X
//Proxy 를 만든다 -> 가짜를 만든다
//MyLogger 의 가짜 프록시 클래스를 만들어두고 HTTP request 와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해둘 수 있다
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL=requestURL;
    }

    public void log(String message) {
        System.out.println("[ "+uuid+" ]"+"[ "+requestURL+" ]"+message);
    }

    @PostConstruct //고객 요청 들어올 때
    public void init() {
        uuid=UUID.randomUUID().toString(); //유니크한 아이디가 생성됨
        System.out.println("[ "+uuid+" ] request scope bean create: "+this); //this -> 객체 주소 나옴
    }

    @PreDestroy //서버에서 빠져나갈 때 호출. Request Scope 니까 호출 됨
    public void close() {
        System.out.println("[ "+uuid+" ] request scope bean close: "+this); //this -> 객체 주소 나옴
    }
}
