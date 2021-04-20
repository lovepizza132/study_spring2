package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient{
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }
    
    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }
    
    // 1. 스프링 인터페이스를 사용하는 방법
//    // 의존관계 주입이 끝나면 실행이 됨
//    @Override
//    public void afterPropertiesSet() throws Exception{
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    // 빈이 소멸되기 직전에
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    // 2. bean의 initMethod, destoryMethod 속성을 사용하는 방법
    // 의존관계 주입이 끝나면 실행이 됨
//    public void init(){
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    // 빈이 소멸되기 직전에
//    public void close(){
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    // 3. java interface -> closeable

    /*
    4. javax.annotation -> @PostConstruct, @PreDestroy

    최신 스프링에서 가장 권장하는 방법이다.
    애노테이션 하나만 붙이면 되므로 매우 편리하다.
    패키지를 잘 보면 javax.annotation.PostConstruct 이다. 스프링에 종속적인 기술이 아니라 JSR-250
    라는 자바 표준이다. 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.
    컴포넌트 스캔과 잘 어울린다.
    유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것이다. 외부 라이브러리를 초기화, 종료 해야 하면
    @Bean의 기능을 사용하자
     */
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈이 소멸되기 직전에
    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
