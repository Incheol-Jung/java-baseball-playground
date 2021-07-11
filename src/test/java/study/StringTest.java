package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
	@Test
	void replace() {
		String actual = "abc".replace("b", "d");
		assertThat(actual).isEqualTo("adc");
	}

	@Test
	@DisplayName("\"1,2\"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인")
	public void splitContains() throws Exception {
		// given
		String firstText = "1";
		String secondText = "2";
		String plainText = firstText + "," + secondText;

		// when
		String[] splitTexts = plainText.split(",");

		// then
		assertThat(splitTexts).contains(firstText, secondText);

		// assertThat(splitTexts.length > 0).isTrue();
		// assertThat(splitTexts[0].equals(firstText)).isTrue();
		// assertThat(splitTexts[1].equals(secondText)).isTrue();

		// 고민 1. firstText, secondText를 array 형으로 선언해서 자동으로 + 되도록 하면 assert문이나 할당하는 로직을 루프로 자동화 할수 있지 않을까?
		// 고민 2. 현재는 케이스가 "1,2"라서 두개의 String 인스턴스를 생성하였는데 문자열이 더 길어지면 '+' 로직은 힙 메모리에 새로운 객체로 생성되므로 테스트케이스가 애플리케이션 성능에 영향을 주지는 않을까?

	}

	@Test
	@DisplayName("\"1\"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지 확인")
	public void splitContainsExactly() throws Exception {
		// given
		String firstText = "1";
		String plainText = firstText + ",";

		// when
		String[] splitTexts = plainText.split(",");

		// then
		assertThat(splitTexts).containsExactly(firstText);
	}

	@Test
	@DisplayName("\"1\"을 ,로 split 했을 때 1,2을 포함하지 않는지 확인")
	public void splitContainsExactlyException() throws Exception {
		// given
		String firstText = "1";
		String secondText = "2";
		String plainText = firstText + ",";

		// when
		String[] splitTexts = plainText.split(",");

		// then
		assertThat(splitTexts).doesNotContain(secondText);


        // 고민 1. 요구사항의 true인 케이스만 테스트 케이스를 작성하는게 맞을지? 지금처럼 false인 케이스도 추가하는게 좋지 않을까? 혹은 의미 없는 테스트 케이스일까?
	}
}
