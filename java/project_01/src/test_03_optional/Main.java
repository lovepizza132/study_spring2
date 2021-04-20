package test_03_optional;

import java.util.Optional;

public class Main {
	
	static String foo() {
		return "foo...";
	}
	
	static class MyException extends Exception{
		MyException(String msg){
			super(msg);
		}
	}
	
	public static void main(String[] args) throws MyException {
		
		// of() 혹은 ofNullable() 메소드를 사용하여 Optional 객체 생성
		
		// 1. of()
		// Optional 객체 반환
		// of() 메소드를 통해 생성된 Optional 객체에 null이 저장되면 NullPointerException 발생
		Optional<String> opt1 = Optional.of("abc");
		System.out.println(opt1.get());
//		Optional<String> opt2 = Optional.of(null); 			// NullPointerException 발생
//		System.out.println(opt2.get());						// 객체 생성부터 오류가 발생			
		
		// 2. ofNullable()
		// null 될 가능성이 있을 때 사용
		Optional<String> opt3 = Optional.ofNullable("bcd");
		System.out.println(opt3.get());
		Optional<String> opt4 = Optional.ofNullable(null);
//		System.out.println(opt4.get());						// Optional 객체에 저장된 값이 null -> NoSuchElementException 발생
		
		
		// Optional 객체에 접근
		// 1. opt.get()			-> 객체에 저장된 값에 접근, 값이 null이라면 NoSuchElementException 발생
		// 2. opt.isPresent()	-> Optional 객체에 저장된 값이 null인지 확인
		// 3. orElse()			-> 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 값을 반환
		// 4. orElseGet()		-> 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 람파 됴현식의 결과값을 반환
		// 5. orElseThrow()		-> 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 예외를 발생시킴
		System.out.println("opt3 isPresent ?: " + opt3.isPresent());
		System.out.println("opt4 isPresent ?: " + opt4.isPresent());
		if(opt4.isPresent()) {
		    System.out.println(opt4.get());
		}
		System.out.println(opt4.orElse("empty object ..."));
		System.out.println(opt4.orElseGet( () -> foo() ));
//		System.out.println(opt4.orElseThrow( () -> new MyException("My Exception ...")));
	}
}
