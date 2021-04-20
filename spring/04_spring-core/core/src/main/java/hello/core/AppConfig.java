package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//public class AppConfig {
//
////    public MemberService memberService(){
////        return new MemberServiceImpl(new MemoryMemberRepository());
////    }
////
////    public OrderService orderService(){
////        return new OrderServiceImpl(
////                new MemoryMemberRepository(),
////                new RateDiscountPolicy());
////    }
//
//    public MemberService memberService(){
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    public MemoryMemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    public DiscountPolicy discountPolicy(){
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }
//}

@Configuration // -> 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장 <-> @Bean은 보장을 해주지 않음
public class AppConfig {
    // factoryMethod를 이용하는 방식..

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderSerivce -> new MemoryMemberRepository(), ...
    // L singleton이 꺠지는 것처럼 보임

    // 예상
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository

    // 결과
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    // Typically, @Bean methods are declared within @Configuration classes.
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

