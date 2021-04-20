package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
// @ComponentScan : Configures component scanning directives for use with @Configuration classes.
@ComponentScan(
        // basePackages, basePackageClasses를 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        // L 권장방법 : 패키지의 위치를 지정하지 않고, 프로젝트 최상단에 둠
        basePackages = "hello.core",
        basePackageClasses = AutoAppConfig.class, // 해당 클래스의 패키지부터 탐색...
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
) // 기존 설정 파일들을 유지하면서 테스트하기 위해서 사용함
public class AutoAppConfig {

//    자동 빈 등록 <-> 수동 빈 등록 => 수동으로 등록한 빈이 우선 / 수동으로 빈을 등록하는 것은 권장하지 않음...(+ springboot는 기본적으로 수동빈과 자동빈이 겹칠 시에는 오류가 나도록 만들어둠)
//    @Bean(name="memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
