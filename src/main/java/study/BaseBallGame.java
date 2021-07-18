package study;

import java.util.ArrayList;
import java.util.List;

public class BaseBallGame {
	private List<Ball> systemNumbers = new ArrayList<>();
	private List<Ball> userNumbers = new ArrayList<>();

	public void initGame() {
		for (int i = 0; systemNumbers.size() < 3; i++) {
			Integer value = (int)((Math.random() * 100) % 9 + 1);
			addNumbers(new Ball(i + 1, value));
		}
	}

	private void addNumbers(Ball ball) {
		validateNumbers(ball);

		if (!systemNumbers.contains(ball)) {
			systemNumbers.add(ball);
		}
	}

	private void validateNumbers(Ball ball) {
		if (ball.getValue() < 0 || ball.getValue() > 9) {
			throw new IllegalArgumentException("유효하지 않은 값입니다.");
		}
	}

	public List<Ball> getSystemNumbers() {
		return this.systemNumbers;
	}

	public List<Ball> getUserNumbers() {
		return this.userNumbers;
	}

	public void addUserNumber(Ball ball) {
		validateNumbers(ball);

		if (!userNumbers.contains(ball)) {
			userNumbers.add(ball);
		}
	}

	public void setSystemNumbers(List<Ball> numbers) {
		this.systemNumbers = numbers;
	}

	public BallStatus play() {
		return this.systemNumbers.stream()
			.map(ball -> compareBall(ball, userNumbers.get(0)))
			.findFirst()
			.orElse(BallStatus.NOTHING);
	}

	private BallStatus compareBall(Ball systemBall, Ball userBall) {
		if (systemBall.equals(userBall)) {
			return BallStatus.STRIKE;
		}

		if (systemBall.getValue() == userBall.getValue()) {
			return BallStatus.BALL;
		}

		return BallStatus.NOTHING;
	}
}
