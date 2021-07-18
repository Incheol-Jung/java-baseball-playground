package study;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

public class BaseBallGameTest {
	@Test
	void 시스템은_세_가지_번호를_랜덤_생성한다() {
		// given
		BaseBallGame baseBallGame = new BaseBallGame();
		
		// when
		baseBallGame.initGame();
		
		// then
		assertThat(baseBallGame.getSystemNumbers().size() == 3).isTrue();
	}
}
