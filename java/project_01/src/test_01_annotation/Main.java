package test_01_annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {
	@SampleAnnotation(value = "a", values = {123, 456})
	static void myPrint() {
		Method myMethod = new Object(){}.getClass().getEnclosingMethod();
		SampleAnnotation sampleAnnotation = myMethod.getDeclaredAnnotation(SampleAnnotation.class);
		System.out.println(sampleAnnotation.value());
		int [] sample_array = sampleAnnotation.values();
		for (int sample : sample_array) {
			System.out.printf("%d\n", sample);
		}
	}
	@SampleAnnotation(value = "b", values = {789, 456})
	static void myPrint2() {
		Method myMethod = new Object(){}.getClass().getEnclosingMethod();
        Annotation[] annotations = myMethod.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof SampleAnnotation) {
            	SampleAnnotation myAnnotation = (SampleAnnotation) annotation;
                System.out.println("name: " + myAnnotation.value());
            }
        }
	}
	
	public static void main(String[] args) {
		System.out.println("========== myPrint ==========");
		myPrint();
		System.out.println("========== myPrint2 ==========");
		myPrint2();
	}
}
