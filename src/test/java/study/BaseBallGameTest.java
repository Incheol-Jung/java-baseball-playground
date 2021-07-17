package study;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BaseBallGameTest {
	// 자동으로 세개의 값을 설정한다.  
	// 세개의 입력값을 파라미터로 전달받는다. 
	// 숫자가 일치하면 게임을 종료한다. 
	// 같은 수가 같은 자리에 있으면 스트라이크를 출력한다. 
	// 다른 자리에 있으면 볼을 출력한다.
	// 같은 수가 전혀 없으면 포볼 또는 낫싱을 출력한다.
	static class BaseBallGame {
		private List<Integer> systemNumbers = new ArrayList<>();
		private List<Integer> userNumbers = new ArrayList<>();
		private Integer ballCount = 0;
		private Integer strikeCount = 0;

		public List<Integer> getSystemNumbers() {
			return systemNumbers;
		}

		public void setup() {
			for (int i = 0; i < 3; i++) {
				systemNumbers.add((int)(i * Math.random() / 9) + 1);
			}
		}

		public void addUserNumber(int i) {
			userNumbers.add(i);
		}

		public boolean play() {
			for (int i = 0; i < 3; i++) {
				int index = systemNumbers.indexOf(userNumbers.get(i));
				// -1, 1, 2
				setCount(i, index);
			}
			return ballCount + strikeCount == 0;
		}

		private void setCount(int i, int index) {
			if (index == -1)
				return;

			if (i == index) {
				strikeCount++;
			}

			if (i != index) {
				ballCount++;
			}
		}

		public void printOut() {
			if (getBallCount() == 0 && getStrikeCount() == 0) {
				System.out.println("낫싱");
				return;
			}
			
			System.out.printf("%d 볼, %d 스트라이크\n", ballCount, strikeCount);
		}

		public void setSystemNumbers(ArrayList<Integer> integers) {
			this.systemNumbers = integers;
		}

		public void setUserNumbers(ArrayList<Integer> integers) {
			this.userNumbers = integers;
		}

		public Integer getStrikeCount() {
			return strikeCount;
		}

		public Integer getBallCount() {
			return ballCount;
		}
	}

	// 자동으로 세개의 값을 설정한다.
	@Test
	public void setupVariables() {
		// given
		BaseBallGame baseBallGame = new BaseBallGame();

		// when
		baseBallGame.setup();

		// then
		assertThat(baseBallGame.getSystemNumbers().size()).isEqualTo(3);
	}

	// 세개의 입력값을 파라미터로 전달받는다. 
	@Test
	public void inputCheck() {
		// given
		BaseBallGame baseBallGame = new BaseBallGame();

		// when
		for (int i = 0; i < 3; i++) {
			baseBallGame.addUserNumber((int)(i * Math.random() / 9) + 1);
		}

		// then
		assertThat(baseBallGame.getSystemNumbers().size()).isEqualTo(3);
	}

	// 숫자가 일치하면 게임을 종료한다. 
	@Test
	public void success() {
		// given
		BaseBallGame baseBallGame = new BaseBallGame();
		baseBallGame.setup();
		for (Integer number : baseBallGame.getSystemNumbers()) {
			baseBallGame.addUserNumber(number);
		}

		// when
		boolean isCompleted = baseBallGame.play();

		// then
		assertThat(isCompleted).isEqualTo(true);
	}

	// 같은 수가 같은 자리에 있으면 스트라이크를 출력한다.  
	@Test
	public void strike() {
		// given
		BaseBallGame baseBallGame = new BaseBallGame();
		baseBallGame.setSystemNumbers(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
		baseBallGame.setUserNumbers(new ArrayList<Integer>(Arrays.asList(1, 2, 4)));

		// when
		boolean isCompleted = baseBallGame.play();

		// then
		baseBallGame.printOut();
		assertThat(baseBallGame.getStrikeCount() > 0).isTrue();
	}

	// 다른 자리에 있으면 볼을 출력한다.
	@Test
	public void ball() {
		// given
		BaseBallGame baseBallGame = new BaseBallGame();
		baseBallGame.setSystemNumbers(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
		baseBallGame.setUserNumbers(new ArrayList<Integer>(Arrays.asList(1, 2, 1)));

		// when
		boolean isCompleted = baseBallGame.play();

		// then
		baseBallGame.printOut();
		assertThat(baseBallGame.getBallCount() > 0).isTrue();
	}

	// 같은 수가 전혀 없으면 낫싱을 출력한다.
	@Test
	public void nothing() {
		// given
		BaseBallGame baseBallGame = new BaseBallGame();
		baseBallGame.setSystemNumbers(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
		baseBallGame.setUserNumbers(new ArrayList<Integer>(Arrays.asList(4, 5, 6)));

		// when
		boolean isCompleted = baseBallGame.play();

		// then
		baseBallGame.printOut();
		assertThat(baseBallGame.getBallCount() == 0).isTrue();
	}
}
