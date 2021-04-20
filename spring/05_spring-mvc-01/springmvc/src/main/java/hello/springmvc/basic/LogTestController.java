package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
스프링 부트 라이브러리를 사용하면 스프링 부트 로깅 라이브러리( spring-boot-starter-logging )가 함께 포함된다.

스프링 부트 로깅 라이브러리는 기본으로 다음 로깅 라이브러리를 사용한다.
SLF4J - http://www.slf4j.org
Logback - http://logback.qos.ch

로그 라이브러리는 Logback, Log4J, Log4J2 등등 수 많은 라이브러리가 있는데, 그것을 통합해서 인터페이스로 제공하는 것이 바로 SLF4J 라이브러리다.
쉽게 이야기해서 SLF4J는 인터페이스이고, 그 구현체로 Logback 같은 로그 라이브러리를 선택하면 된다.
실무에서는 스프링 부트가 기본으로 제공하는 Logback을 대부분 사용한다.

로그 선언
private Logger log = LoggerFactory.getLogger(getClass());
private static final Logger log = LoggerFactory.getLogger(Xxx.class)
@Slf4j : 롬복 사용 가능

로그가 출력되는 포멧 확인
시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스명, 로그 메시지

로그 레벨 설정을 변경해서 출력 결과를 보자.
LEVEL: TRACE > DEBUG > INFO > WARN > ERROR

개발 서버는 debug 출력
운영 서버는 info 출력
@Slf4j 로 변경
 */

//@Slf4j
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
        
        /*
        log.trace("trace my log=" + name);
        => logging level 설정에 의하여 출력이 되지 않더라도, 연산(문자열 더하기)을 실행
         */

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
