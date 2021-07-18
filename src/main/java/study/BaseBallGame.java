package study;

import java.util.ArrayList;
import java.util.List;

public class BaseBallGame {
	private List<Ball> systemNumbers = new ArrayList<>();
	private List<Ball> userNumbers = new ArrayList<>();
	private int strikeCount;
	private int ballCount;

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

	public BallStatus singlePlay(int index) {
		return this.systemNumbers.stream()
			.map(ball -> getBallStatus(ball, userNumbers.get(index)))
			.filter(status -> status.isBall() || status.isStrike())
			.findFirst()
			.orElse(BallStatus.NOTHING);
	}

	public void play() {
		for (int index = 0; index < userNumbers.size(); index++) {
			report(singlePlay(index));
		}
	}

	private void report(BallStatus ballStatus) {
		if (ballStatus.isStrike()) {
			increaseStrikeCount();
		}

		if (ballStatus.isBall()) {
			increaseBallCount();
		}
	}

	private void increaseBallCount() {
		this.ballCount++;
	}

	private void increaseStrikeCount() {
		this.strikeCount++;
	}

	private BallStatus getBallStatus(Ball systemBall, Ball userBall) {
		if (systemBall.equals(userBall)) {
			return BallStatus.STRIKE;
		}

		if (systemBall.getValue() == userBall.getValue()) {
			return BallStatus.BALL;
		}

		return BallStatus.NOTHING;
	}

	public void setUserNumber(List<Ball> balls) {
		this.userNumbers = balls;
	}

	public boolean isEnd() {
		return this.getStrikeCount() == 3;
	}

	private int getStrikeCount() {
		return this.strikeCount;
	}

	public String print() {
		String result = "낫싱";
		if (getStrikeCount() > 0 || getBallCount() > 0) {
			return String.format("%d 볼, %d 스트라이크", ballCount, strikeCount);
		}

		return result;
	}

	private int getBallCount() {
		return this.ballCount;
	}
}
