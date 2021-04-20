package test_04_stream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
	static class Product {
		private int amount;
		private String name;
		
		public Product() {
			
		}
		
		public Product(int amount, String name) {
			this.amount = amount;
			this.name = name;
		}
		
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}		
	}
	
	static <V> void printStream(String streamName, Stream<V> s) {
		System.out.println("streamName : " + streamName);
		s.forEach((e)->{
			System.out.print(e + " ");
		});
		System.out.println();
	}
	
	static void check_makeStreamObject() {
		Stream<String> stream0 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		printStream("stream0", stream0);
	}
	
	static void check_emptyStreamObject() {
		Stream<String> emptyStream = Stream.empty();
		printStream("emptyStream", emptyStream);
	}
	
	static void check_arrayStream() {
		String[] arr = new String[] {"a", "b", "c"};
		Stream<String> stream1 = Arrays.stream(arr);		// [a, b, c]
		Stream<String> stream2 = Arrays.stream(arr, 1, 3);	// [b, c]
		printStream("stream1", stream1);
		printStream("stream2", stream2);
	}
	
	static void check_collectionStream() {
		ArrayList<String> arrayList = new ArrayList<String>();
		Stream<String> stream3 = arrayList.stream();
		printStream("stream3", stream3);
	}
	
	static void check_streamBuilder() {
		Stream<String> builderStream = 
				Stream.<String>builder()
				.add("Eric").add("Elena").add("Java")
				.build();	// [Eric, Elena, Java]
		printStream("builderStream", builderStream);
	}

	static void check_generateStream() {
		// public static<T> Stream<T> generate(Supplier<T> s) { ... }
		// Supplier<T>는 인자는 없고 리턴값만 있는 함수형 인터페이스
		Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);	// [gen, gen, gen, gen, gen]
		printStream("generatedStream", generatedStream);
	}
	
	static void check_streamIterate() {
		// 초기값과 해당 값을 다루는 람다를 이용해서 스트림에 들어갈 요소를 생성
		Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5); // [30, 32, 34, 36, 38]
		printStream("iteratedStream", iteratedStream);
	}
	

	static void check_primitiveTypeStream() {
		IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
		LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]
		
		System.out.println("streamName : intStream");
		intStream.forEach((e)->{System.out.print(e + " ");});
		System.out.println();
		System.out.println("streamName : longStream");
		longStream.forEach((e)->{System.out.print(e + " ");});
		System.out.println();
	}
	
	static void check_chartsetStream() {
		// 각 문자(char)를 IntStream으로 변환		
		IntStream charsStream = 
				  "Stream".chars(); // [83, 116, 114, 101, 97, 109]
		System.out.println("streamName : charsStream");
		charsStream.forEach((e)->{System.out.print(e + " ");});
		System.out.println();
		
		// RegEx을 이용한 문자열 자르기
		Stream<String> stringStream = 
				  Pattern.compile(", ").splitAsStream("Eric, Elena, Java");	// [Eric, Elena, Java]
		printStream("stringStream", stringStream);
	}
	
	static void check_fileStream() throws IOException {
		String rootPath = new File(".").getAbsolutePath();
		Stream<String> lineStream = Files.lines(Paths.get(rootPath+"\\src\\test_04_stream\\file.txt"), Charset.forName("UTF-8"));
		printStream("lineStream", lineStream);

	}
	
	static void check_filtering() {
		// Stream<T> filter(Predicate<? super T> predicate);
		// 인자로 받는 Predicate 는 boolean 을 리턴하는 함수형 인터페이스
		
		List<String> names = Arrays.asList("Eric", "Elena", "Java");
		Stream<String> stream4 = 
				  names.stream()
				  .filter(name -> name.contains("a"));	// [Elena, Java] // 이름에 'a'가 들어간 이름만 들어간 스트림이 리턴됨
	}
	
	static void check_mapping() {
		// <R> Stream<R> map(Function<? super T, ? extends R> mapper);
		
		List<String> names = Arrays.asList("Eric", "Elena", "Java");
		Stream<String> stream5 = 
				  names.stream()
				  .map(String::toUpperCase);			// [ERIC, ELENA, JAVA]
		
	}
	
	static void check_flattening() {
		// <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
		
		List<List<String>> list = 
				  Arrays.asList(Arrays.asList("a"), 
				                Arrays.asList("b"));// [[a], [b]]
		List<String> flatList = 
				  list.stream()
				  .flatMap(Collection::stream)			// Collection모듈의 stream() 메소드를 인자로 넘김
				  .collect(Collectors.toList());
				// [a, b]
		System.out.println("check flatList : " + flatList);
	}
	
	static void check_parallelStream() {
		// 병렬 스트림 생성
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("abc");
		Stream<String> parallelStream = myList.parallelStream();
		// 병렬 여부 확인
		boolean isParallel = parallelStream.isParallel();
		System.out.println("isParallel Stream ? => " + isParallel);
	}
	
	static void check_streamConcat() {
		Stream<String> stream1 = Stream.of("Java", "Scala", "Groovy");
		Stream<String> stream2 = Stream.of("Python", "Go", "Swift");
		Stream<String> concatStream = Stream.concat(stream1, stream2);
		printStream("concat", concatStream);
	}
	
	static void check_streamSorting() {
		List<Integer> defaultSortedIntList = IntStream.of(14, 11, 20, 39, 23)
		  .sorted()							// 기본 정렬은 오름차순
		  .boxed()
		  .collect(Collectors.toList());	// [11, 14, 20, 23, 39]

		List<String> lang = 
				  Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");
		
		System.out.println("defaultSortedIntList(sorting) : " + defaultSortedIntList);
		
		// 알파벳 순 정렬
		List<String> lang1 = lang.stream()
		  .sorted()
		  .collect(Collectors.toList());
			// [Go, Groovy, Java, Python, Scala, Swift]
		System.out.println("defaultSortedStringList(sorting) : " + lang1);

		// 역순으로 정렬
		List<String> lang2 = lang.stream()
		  .sorted(Comparator.reverseOrder())
		  .collect(Collectors.toList());
			// [Swift, Scala, Python, Java, Groovy, Go]
		System.out.println("reverseSortedStringList(sorting) : " + lang2);
		
		// 문자열 길이가 작은 순
		List<String> lang3 = lang.stream()
		  .sorted(Comparator.comparingInt(String::length))
		  .collect(Collectors.toList());
		// [Go, Java, Scala, Swift, Groovy, Python]
		System.out.println("defaultSortedStringListByStringLength(sorting) : " + lang3);

		// 문자열 길이가 큰 순
		List<String> lang4 = lang.stream()
		  .sorted((s1, s2) -> s2.length() - s1.length())
		  .collect(Collectors.toList());
		// [Groovy, Python, Scala, Swift, Java, Go]
		System.out.println("reverseSortedStringListByStringLength(sorting) : " + lang4);
	}
	
	static void check_streamPeek() {
//		Stream<T> peek(Consumer<? super T> action);
//		Consumer : 특정 값을 반환하지 않는 함수형 인터페이스
		
		System.out.println("check peek ...");
		int sum = IntStream.of(1, 3, 5, 7, 9)
				  .peek(System.out::println)
				  .sum();
	}
	
	static void check_calculating() {
		System.out.println("check_calculating ...");
		long count = IntStream.of(1, 3, 5, 7, 9).count();
		long sum = LongStream.of(1, 3, 5, 7, 9).sum();
		OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
		OptionalInt max = IntStream.of(1, 3, 5, 7, 9).max();

		System.out.println("count : " + count);
		System.out.println("sum : " + sum);
		System.out.println("min : " + min);
		System.out.println("max : " + max);
		
		DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5)
		  .average()
		  .ifPresent(System.out::println);
	}
	
	static void check_reduction() {
		// 인자 1개 -> Optional<T> reduce(BinaryOperator<T> accumulator);
		// 인자 2개 -> T reduce(T identity, BinaryOperator<T> accumulator);
		// 인자 3개 -> <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
		// accumulator : 각 요소를 처리하는 계산 로직. 각 요소가 올 때마다 중간 결과를 생성하는 로직
		// identity : 계산을 위한 초기값으로 스트림이 비어서 계산할 내용이 없더라도 이 값은 리턴
		// combiner : 병렬(parallel) 스트림에서 나눠 계산한 결과를 하나로 합치는 동작하는 로직
		
		OptionalInt reduced = 		// OptionalInt는 Optional<Integer>와 같음
			  IntStream.range(1, 4) // [1, 2, 3]
			  .reduce((a, b) -> {
			    return Integer.sum(a, b);
			  });
		System.out.println("reduced value1 : " + reduced);	// 1+2+3
		
		int reducedTwoParams = 
			  IntStream.range(1, 4) // [1, 2, 3]
			  .reduce(10, Integer::sum); // method reference
		System.out.println("reduced value2 : " + reducedTwoParams);	// 10+1+2+3
		
		
		// combiner가 있는 경우에는 parallelStream을 사용해야함
		// combiner는 여러 쓰레드에 나눠 계산한 결과를 합치는 역할
		
		Integer reducedParallel = Arrays.asList(1, 2, 3)
			  .parallelStream()
			  .reduce(10,
			          Integer::sum,	// integer.sum() 메소드를 전달
			          (a, b) -> {
			            System.out.println("combiner was called");
			            return a + b;
			          });
		System.out.println("reduced value2 : " + reducedParallel);	// (10+1)+(10+2)+(10+3) = 36
		
		// 결과값
		// combiner was called
		// combiner was called
		// 36
	}
	
	static void check_matching() {
		// 하나라도 조건을 만족하는 요소가 있는지(anyMatch)
		// 모두 조건을 만족하는지(allMatch)
		// 모두 조건을 만족하지 않는지(noneMatch)
		
		List<String> names = Arrays.asList("Eric", "Elena", "Java");

		boolean anyMatch = names.stream()
		  .anyMatch(name -> name.contains("a"));
		boolean allMatch = names.stream()
		  .allMatch(name -> name.length() > 3);
		boolean noneMatch = names.stream()
		  .noneMatch(name -> name.endsWith("s"));
		
		System.out.println("anyMatch : " + anyMatch);	// true
		System.out.println("allMatch : " + allMatch);	// true
		System.out.println("noneMatch : " + noneMatch);	// true
	}
	
	static void check_iterating() {
		// foreach 는 요소를 돌면서 실행되는 최종 작업 
		// 보통 System.out.println 메소드를 넘겨서 결과를 출력할 때 사용 
		// 앞서 살펴본 peek 과는 중간 작업과 최종 작업의 차이
		
		List<String> names = Arrays.asList("Eric", "Elena", "Java");
		names.stream().forEach(System.out::println);
	}
	
	static void check_collecting() {
		List<Product> productList = 
				  Arrays.asList(new Product(23, "potatoes"),
				                new Product(14, "orange"),
				                new Product(13, "lemon"),
				                new Product(23, "bread"),
				                new Product(13, "sugar"));
		
		List<String> collectorCollection =
				  productList.stream()
				    .map(Product::getName)
				    .collect(Collectors.toList());
				// [potatoes, orange, lemon, bread, sugar]
		
		System.out.println("collectorCollection : " + collectorCollection);
		
		/*
		 * ...
		 * Collectors.joining() : 스트림에서 작업한 결과를 하나의 스트링으로
		 * Collectors.averageingInt()
		 * Collectors.summingInt()
		 * Collectors.summarizingInt()
		 * Collectors.groupingBy()
		 * Collectors.partitioningBy() : 평가를 하는 함수를 통해서 스트림 내 요소들을 true 와 false 두 가지로 분류
		 * Collectors.collectingAndThen() : collect 한 이후에 추가 작업이 필요한 경우에 사용
		 * Collector.of() : collector 생성
		 * ...
		 */

	}
	
	public static void main(String[] args) throws IOException {
		
		// 0. Stream 객채 셍성
		// Stream.of()
		// stream 객체 생성
		check_makeStreamObject();
		
		// Stream.empty()
		// 비어있는 스트림 생성
		check_emptyStreamObject();
		
		// 1. Array Stream
		check_arrayStream();
		
		// 2. Collection stream
		check_collectionStream();
		
		// 3. Stream.builder()
		check_streamBuilder();
		
		// 4. Strean.generate()
		check_generateStream();
		
		// 5. Stream.interate()
		check_streamIterate();
		
		// 6. 기본 타입형 스트림
		check_primitiveTypeStream();
		
		// 7. 문자열 스트림
		check_chartsetStream();
		
		// 8. 파일 스트림
		check_fileStream();
		
		// 9. Filtering
		check_filtering();
		
		// 10. Mapping
		check_mapping();
		
		// 11. Flattening
		check_flattening();
		
		// 12. 병렬 스트림
		check_parallelStream();
		
		// 13. 스트림 연결하기
		check_streamConcat();
		
		// 14. sorting
		check_streamSorting();
		
		// 15. peek
		check_streamPeek();
		
		// 16. calculating
		check_calculating();
				
		// 17. reduction
		check_reduction();
		
		// 18. Matching
		check_matching();
		
		// 19. iterating
		check_iterating();
		
		// 20. collecting
		check_collecting();
		
	}
}
