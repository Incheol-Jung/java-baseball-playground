package study;

import java.util.ArrayList;
import java.util.List;

public class BaseBallGame {
	private final List<Integer> systemNumbers = new ArrayList<>();
	private List<Integer> userNumbers = new ArrayList<>();

	public void initGame() {
		while (systemNumbers.size() < 3) {
			Integer number = (int)((Math.random() * 100) % 9 + 1);
			addNumbers(number);
		}
	}

	private void addNumbers(Integer number) {
		validateNumbers(number);
		
		if (!systemNumbers.contains(number)) {
			systemNumbers.add(number);
		}
	}

	private void validateNumbers(Integer number) {
		if (number < 0 || number > 9) {
			throw new IllegalArgumentException("유효하지 않은 값입니다.");
		}
	}

	public List<Integer> getSystemNumbers() {
		return this.systemNumbers;
	}

	public List<Integer> getUserNumbers() {
		return this.userNumbers;
	}

	public void addUserNumber(int number) {
		validateNumbers(number);
		
		if (!userNumbers.contains(number)) {
			userNumbers.add(number);
		}
	}
}
