package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor // private final 붙은 필드들 생성자 만들고 @Autowired 까지 해주는 효과(의존관계 주입)
public class LogDemoController {
    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider; //MyLogger 가 주입되는 게 아니라 MyLogger 를 찾을 수 있는 DL이 주입된다
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL=request.getRequestURL().toString();
//        MyLogger myLogger=myLoggerProvider.getObject(); // MyLogger 객체 생김. 이후 init 호출

        System.out.println("껍데기 myLogger = " +myLogger.getClass() ); //myLogger = class hello.core.common.MyLogger$$SpringCGLIB$$0 -> CGLIB 내가 만든 게 아니라 스프링이 조작해서 만든 것(껍데기 가짜 myLogger)
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");

        return "OK";

    }
}
