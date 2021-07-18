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
		if (!systemNumbers.contains(number)) {
			systemNumbers.add(number);
		}
	}

	public List<Integer> getSystemNumbers() {
		return this.systemNumbers;
	}

	public List<Integer> getUserNumbers() {
		return this.userNumbers;
	}

	public void addUserNumber(int i) {
		if (!this.userNumbers.contains(i)) {
			this.userNumbers.add(i);
		}
	}
}
