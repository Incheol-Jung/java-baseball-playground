package study;

import java.util.ArrayList;
import java.util.List;

public class BaseBallGame {
	private final List<Integer> systemNumbers = new ArrayList<>();
	
	public void initGame() {
		systemNumbers.add(1);
		systemNumbers.add(2);
		systemNumbers.add(3);
	}

	public List<Integer> getSystemNumbers() {
		return this.systemNumbers;
	}
}
