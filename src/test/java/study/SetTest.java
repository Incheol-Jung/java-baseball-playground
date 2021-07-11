package study;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SetTest {
	private Set<Integer> numbers;

	@BeforeEach
	void setUp() {
		numbers = new HashSet<>();
		numbers.add(1);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
	}
	
	@Test
	@DisplayName("요구사항 1. numbers의 크기가 3인지 size 메소드로 확인한다.")
	public void splitContains() throws Exception {
		// given

		// when
		Integer numbersSize = numbers.size();

		// then
		assertThat(numbersSize).isEqualTo(3);

		// 고민 1. numbers를 변경하게 된다면 size가 변경될 텐데 3으로 고정해도 괜찮을까?
	}

	@DisplayName("요구사항 2. contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인")
	@ParameterizedTest
	@ValueSource(ints = {1,2,3})
	public void containsTest(int number) throws Exception {
		// given
		// when

		// then
		assertThat(numbers.contains(number)).isTrue();
	}

	@DisplayName("요구사항 3. 1, 2, 3 값은 contains 메소드 실행결과 true, 4, 5 값을 넣으면 false 가 반환")
	@ParameterizedTest
	@CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
	public void containsTest(String input, String expected) throws Exception {
		// given
		Integer number = Integer.valueOf(input);
		Boolean result = Boolean.valueOf(expected);
		
		// when

		// then
		assertThat(numbers.contains(number)).isEqualTo(result);
	}
}
