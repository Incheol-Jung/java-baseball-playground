package study;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
	// 1. 문자열을 숫자로 리턴한다. 
	// 2. 문자열을 띄어쓰기 기준으로 분리하였을때, 숫자, 사측연산 패턴으로 되어있는지 확인한다.
	// 3. 문자열에 사측연산을 추가하여 계산을 한다. eg. 1 + 1, 2 * 2
	// 4. 문자열을 띄어쓰기 기준으로 분리하였을때, 숫자나 사측연산 형식이 아닐 경우 예외처리 되어 있는가?
	// 5. 문자열을 띄어쓰기 기준으로 분리하였을때, 숫자 사측연산 까지만 작성되었다면 예외처리 되어 있는가?
	static class StringCalculator {
		public enum Operator {
			PLUS("+"),
			MINUS("-"),
			MULTIPLY("*"),
			DIVISION("/");

			private String operator;

			Operator(String operator) {
				this.operator = operator;
			}

			public String getValue() {
				return operator;
			}

			public static Operator getByValue(String value) {
				return Arrays.stream(values()).filter(it -> it.getValue().equals(value)).findFirst().get();
			}
		}

		public Integer calc1(String expression) {
			return Integer.parseInt(expression);
		}

		public Integer calc2(String expression) {
			String[] values = expression.split(" ");
			int operand = 0;
			for (int count = 0; count < values.length; count++) {
				String value = values[count];
				if ((count % 2) == 0) {
					operand = Integer.parseInt(value);
				} else {
					switch (Operator.getByValue(value)) {
						case PLUS:
						case MINUS:
						case MULTIPLY:
						case DIVISION:
							break;
						default:
							break;
					}
				}
			}
			return operand;
		}

		public Integer calc3(String expression) {
			String[] values = expression.split(" ");
			Stack<Operator> operators = new Stack<>();
			int sum = 0;
			int operand;
			for (int count = 0; count < values.length; count++) {
				String value = values[count];
				if ((count % 2) == 0) {
					operand = Integer.parseInt(value);
					if (!operators.isEmpty()) {
						sum = calculate(operators.pop(), sum, operand);
					} else {
						sum = operand;
					}
				} else {
					operators.push(Operator.getByValue(value));
				}
			}
			return sum;
		}

		private Integer calculate(Operator operator, Integer sum, Integer operand) {
			switch (operator) {
				case PLUS:
					sum += operand;
					break;
				case MINUS:
					sum -= operand;
					break;
				case MULTIPLY:
					sum *= operand;
					break;
				case DIVISION:
					sum /= operand;
					break;
				default:
					break;
			}

			return sum;
		}

		public Integer calc4(String expression) {
			String[] values = expression.split(" ");
			Stack<StringCalculator.Operator> operators = new Stack<>();
			int sum = 0;
			int operand;
			for (int count = 0; count < values.length; count++) {
				String value = values[count];
				if ((count % 2) == 0) {
					try {
						operand = Integer.parseInt(value);
						if (!operators.isEmpty()) {
							sum = calculate(operators.pop(), sum, operand);
						} else {
							sum = operand;
						}
					} catch (NumberFormatException ex) {
						System.out.println("숫자 형태가 아닙니다. ");
						throw ex;
					}
				} else {
					try {
						operators.push(StringCalculator.Operator.getByValue(value));
					} catch (Exception ex) {
						System.out.println(ex);
					}

				}
			}
			return sum;
		}

		public Integer calc5(String expression) {
			String[] values = expression.split(" ");
			Stack<StringCalculator.Operator> operators = new Stack<>();
			int sum = 0;
			int operand;
			for (int count = 0; count < values.length; count++) {
				String value = values[count];
				if ((count % 2) == 0) {
					try {
						operand = Integer.parseInt(value);
						if (!operators.isEmpty()) {
							sum = calculate(operators.pop(), sum, operand);
						} else {
							sum = operand;
						}
					} catch (NumberFormatException ex) {
						System.out.println("숫자 형태가 아닙니다. ");
						throw ex;
					}
				} else {
					try {
						operators.push(StringCalculator.Operator.getByValue(value));
					} catch (Exception ex) {
						System.out.println(ex);
					}

				}
			}
			if(!operators.isEmpty()){
				throw new RuntimeException("연산 형식이 잘못 되었습니다.");
			}
			return sum;
		}
	}

	@Test
	@DisplayName("TC 1. 문자열을 숫자로 리턴한다.")
	public void calc1() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThat(stringCalculator.calc1("1")).isEqualTo(1);
		assertThat(stringCalculator.calc2("1")).isEqualTo(1);
		assertThat(stringCalculator.calc3("1")).isEqualTo(1);
		assertThat(stringCalculator.calc4("1")).isEqualTo(1);
		assertThat(stringCalculator.calc5("1")).isEqualTo(1);
	}

	@Test
	@DisplayName("TC 2. 문자열을 띄어쓰기 기준으로 분리하였을때, 숫자, 사측연산 패턴으로 되어있는지 확인한다. ")
	public void calc2() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThat(stringCalculator.calc2("1 + 1")).isEqualTo(1);
		assertThat(stringCalculator.calc3("1 + 1")).isEqualTo(2);
		assertThat(stringCalculator.calc4("1 + 1")).isEqualTo(2);
		assertThat(stringCalculator.calc5("1 + 1")).isEqualTo(2);
	}

	@Test
	@DisplayName("TC 3. 문자열에 사측연산을 추가하여 계산을 한다. eg. 1 + 1, 2 * 2 ")
	public void calc3() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThat(stringCalculator.calc3("1 + 1 * 4 / 2 - 1")).isEqualTo(3);
		assertThat(stringCalculator.calc4("1 + 1 * 4 / 2 - 1")).isEqualTo(3);
		assertThat(stringCalculator.calc5("1 + 1 * 4 / 2 - 1")).isEqualTo(3);
	}

	@Test
	@DisplayName("TC 4. 문자열을 띄어쓰기가 제대로 되지 않을 경우는 예외처리 되어 있는가?")
	public void calc4() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThatThrownBy(() -> assertThat(stringCalculator.calc4("w + d")).isEqualTo(0)).isInstanceOf(NumberFormatException.class);
		assertThatThrownBy(() -> assertThat(stringCalculator.calc5("w + d")).isEqualTo(0)).isInstanceOf(NumberFormatException.class);
	}

	@Test
	@DisplayName("TC 5. 문자열을 띄어쓰기 기준으로 분리하였을때, 숫자 사측연산 까지만 작성되었다면 예외처리 되어 있는가?")
	public void calc5() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThatThrownBy(() -> assertThat(stringCalculator.calc5("1 +")).isEqualTo(0)).isInstanceOf(RuntimeException.class);
	}
}
