package test_02_generics;

public class Main {
	static void test_01() {
		System.out.println("test_01 ===============");
		MyClass<String, Object> myclass = new MyClass<String, Object>();
		myclass.setKey("abc");
		myclass.setValue("bcd");
		System.out.println(myclass.toString());
	}
	
	static void test_02() {
		System.out.println("test_02 ===============");
		sample_generic_method_01("aaa");
		sample_generic_method_01(111);
	}
	
	static void test_03() {
		System.out.println("test_03 ===============");
		ChildClass<String, Integer> myclass = new ChildClass<String, Integer>();
		myclass.setKey("abc");
		myclass.setValue(123);
		System.out.println(myclass.toString());
	}
	
	static void test_04() {
		System.out.println("test_04 ===============");
		sample_generic_method_02(5);
//		sample_generic_method_02("5");		// 에러
	}
	
	// 클래스 정의에 제너릭 타입이 명시되어 있지 않거나, 독립적인? 메소드인 경우에는 메소드의 반환타입 앞에 제너릭타입을 명시해줘야 한다.
	// static <V> void sample_generic_method(V v) {}	// (O)
	// static void sample_generic_method(V v) {}		// (X)
	static <V> void sample_generic_method_01(V v) {
		System.out.println("check value : " + v );
	}
	
	static <V extends Number> void sample_generic_method_02(V v) {
		System.out.println("check value : " + v );
	}
	
	public static void main(String[] args) {
		test_01();
		test_02();
		test_03();
		test_04();
	}
}
