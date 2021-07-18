package study;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BaseBallGameTest {
	private BaseBallGame baseBallGame;

	@BeforeEach
	void setup() {
		// given
		baseBallGame = new BaseBallGame();

		// when
		baseBallGame.setSystemNumbers(Arrays.asList(new Ball(1, 1), new Ball(2, 2), new Ball(3, 3)));
	}

	@Test
	void 시스템은_세_가지_번호를_랜덤_생성한다() {
		// then
		assertThat(baseBallGame.getSystemNumbers().size() == 3).isTrue();
	}

	@Test
	void 생성된_번호는_중복되면_안된다() {
		// then
		assertThat(baseBallGame.getSystemNumbers().stream().distinct().count() == 3).isTrue();
	}

	@Test
	void 사용자에게_숫자_입력을_받는다() {
		// when
		baseBallGame.addUserNumber(new Ball(1, 1));
		baseBallGame.addUserNumber(new Ball(2, 1));
		baseBallGame.addUserNumber(new Ball(3, 1));

		// then
		assertThat(baseBallGame.getUserNumbers().size() > 0).isTrue();
	}

	@Test
	void 숫자는_중복되면_안된다() {
		// when
		baseBallGame.addUserNumber(new Ball(1, 1));
		baseBallGame.addUserNumber(new Ball(2, 2));
		baseBallGame.addUserNumber(new Ball(3, 3));
		baseBallGame.addUserNumber(new Ball(4, 3));

		// then
		assertThat(
			baseBallGame.getUserNumbers().stream().map(ball -> ball.getValue()).distinct().count() == 3).isTrue();
	}

	@Test
	void 숫자는_0_9_이내만_가능하다() {
		// when
		assertThatThrownBy(() -> baseBallGame.addUserNumber(new Ball(1, 11))).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void 같은_수가_같은_자리에_있으면_스트라이크() {
		// given
		baseBallGame.setSystemNumbers(Arrays.asList(new Ball(1, 1), new Ball(2, 2), new Ball(3, 3)));
		baseBallGame.addUserNumber(new Ball(1, 1));

		// when
		BallStatus status = baseBallGame.singlePlay(0);

		// then
		assertThat(status).isEqualTo(BallStatus.STRIKE);
	}

	@Test
	void 다른_자리에_있으면_볼() {
		// given
		baseBallGame.setSystemNumbers(Arrays.asList(new Ball(1, 1), new Ball(2, 2), new Ball(3, 3)));
		baseBallGame.addUserNumber(new Ball(2, 1));

		// when
		BallStatus status = baseBallGame.singlePlay(0);

		// then
		assertThat(status).isEqualTo(BallStatus.BALL);
	}

	@Test
	void 같은_수가_전혀_없으면_낫싱() {
		// given
		baseBallGame.setSystemNumbers(Arrays.asList(new Ball(1, 1), new Ball(2, 2), new Ball(3, 3)));
		baseBallGame.addUserNumber(new Ball(1, 4));

		// when
		BallStatus status = baseBallGame.singlePlay(0);

		// then
		assertThat(status).isEqualTo(BallStatus.NOTHING);
	}

	@Test
	void 컴퓨터가_선택한_숫자를_모두_맞히면_게임이_종료된다() {
		// given
		baseBallGame.setSystemNumbers(Arrays.asList(new Ball(1, 1), new Ball(2, 2), new Ball(3, 3)));
		baseBallGame.setUserNumber(Arrays.asList(new Ball(1, 1), new Ball(2, 2), new Ball(3, 3)));

		// when
		baseBallGame.play();
		boolean isEnd = baseBallGame.isEnd();

		// then
		assertThat(isEnd).isTrue();
	}

	@Test
	void 투볼_투스트라이크() {
		// given
		baseBallGame.setSystemNumbers(Arrays.asList(new Ball(1, 1), new Ball(2, 2), new Ball(3, 3)));
		baseBallGame.setUserNumber(Arrays.asList(new Ball(1, 1), new Ball(2, 3), new Ball(3, 2)));

		// when
		baseBallGame.play();
		boolean isEnd = baseBallGame.isEnd();
		String result = baseBallGame.print();

		// then
		assertThat(isEnd).isFalse();
		assertThat(result).isEqualTo("2 볼, 1 스트라이크");
	}

}
