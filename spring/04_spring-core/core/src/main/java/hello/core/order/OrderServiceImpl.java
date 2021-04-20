package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final이 붙은 변수들을 인자로 갖는 생성자를 만들어 줌
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private DiscountPolicy discountPolicy; // 인터페이스에만 의존하기 위해서.. (클래스, 인터페이스 의존 => 인터페이스 의존)

//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // @Autowired => 기본적으로 타입을 중심으로 주입을 함 (타입이 같다면 빈이름이 같은 것을 우선으로...)
    // (DiscountPolicy |
    // @Component RateDiscountPolicy implements DiscountPolicy,
    // @Component FixDiscountPolicy implements DiscountPolicy
    // => NoUniqueBeanDefinitionException ...)

    // @Qualifier => 일단 등록된 Qualifier를 찾고 없다면, SpringBean을 찾아감
    // Component, Bean에 사용 가능
    
    // @Primary => 중복되는 스프링빈이 존재할 경우, 이 어노테이션이 붙은 bean을 주입
    // 단 @Qualifier보다 우선순위가 밀림
    
    // 필드 주입
    // not recommended
    // @Autowired private MemberRepository memberRepository;
    // @Autowired private DiscountPolicy discountPolicy;

    // 생성자 주입
    // 생성자가 2개 이상일 경우에는 @Autowired가 필수적으로 필요함(1개일 경우에는 @Autowired 생략 가능)
    // 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
    // 불변, 필수 의존관계에 사용
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy ){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 수정자
    // 선택, 변경 가능성이 있는 의존관계에 사용
    // 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
    // 수정자 사용시 생성자 생략 가능?..
    // @Autowired(required = false)
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        this.discountPolicy = discountPolicy;
//    }

    // 일반 메소드 주입
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy
//            discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
