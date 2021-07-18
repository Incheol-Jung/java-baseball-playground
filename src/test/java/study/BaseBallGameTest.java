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
		baseBallGame.initGame();
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
		baseBallGame.addUserNumber(1);
		baseBallGame.addUserNumber(1);
		baseBallGame.addUserNumber(1);

		// then
		assertThat(baseBallGame.getUserNumbers().size() > 0).isTrue();
	}

	@Test
	void 숫자는_중복되면_안된다() {
		// when
		baseBallGame.addUserNumber(1);
		baseBallGame.addUserNumber(2);
		baseBallGame.addUserNumber(3);
		baseBallGame.addUserNumber(3);

		// then
		assertThat(baseBallGame.getUserNumbers().stream().distinct().count() == 3).isTrue();
	}

	@Test
	void 숫자는_0_9_이내만_가능하다() {
		// when
		assertThatThrownBy(() -> baseBallGame.addUserNumber(11)).isInstanceOf(IllegalArgumentException.class);
	}

}
