package test_02_generics;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
	/*
	 * 매개변수화 타입(Parameterized type)
	 * 언바운드 와일드카드 타입(Unbounded wildcard type)
	 * 바운드 타입 매개변수(Bounded type parameter)
	 * 재귀적 타입 바운드(Recursive type bound)
	 * 제네릭의 서브타이핑(Subtyping in generics)
	 * 와일드카드 서브타이핑(Wildcard and subtyping)
	 * 바운드 와일드카드 타입(Bounded wildcard type)
	 * 제네릭 메소드(Generic method)
	 */
	static void check_unboundedWildcardType(List<?> list) {
		for (Object elem: list) {
			System.out.println(elem + " ");
		}
	}
	
	static <V extends Number> void check_boundTypeParameter(V v) {
		System.out.println(v);
	}
	
	public static void main(String[] args) {
		// 매개변수화 타입(Parameterized type)
		List<String> parameterizedList = new ArrayList<>();
		parameterizedList.add("str1");
		parameterizedList.add("str2");
		parameterizedList.add("str3");
		
		// 언바운드 와일드카드 타입(Unbounded wildcard type)
		check_unboundedWildcardType(parameterizedList);
		
		// 바운드 타입 매개변수(Bounded type parameter)
		check_boundTypeParameter(3);
		check_boundTypeParameter(3.5);
//		check_boundTypeParameter("abc"); //error
		
		
	}
}
