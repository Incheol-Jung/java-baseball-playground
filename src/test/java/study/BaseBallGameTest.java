package study;

import static org.assertj.core.api.AssertionsForClassTypes.*;

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

}
