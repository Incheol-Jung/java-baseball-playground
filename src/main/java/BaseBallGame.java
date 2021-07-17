import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseBallGame {
	private List<Integer> systemNumbers = new ArrayList<>();
	private List<Integer> userNumbers = new ArrayList<>();
	private Integer ballCount = 0;
	private Integer strikeCount = 0;

	public List<Integer> getSystemNumbers() {
		return systemNumbers;
	}

	public void setup() {
		for (int i = 0; i < 3; i++) {
			systemNumbers.add((int)((i * Math.random() * 100) / 9) + 1);
		}
	}

	public void addUserNumber(int i) {
		userNumbers.add(i);
	}

	public void initializeCount() {
		ballCount = 0;
		strikeCount = 0;
	}

	public boolean play() {
		for (int i = 0; i < 3; i++) {
			int index = systemNumbers.indexOf(userNumbers.get(i));
			// -1, 1, 2
			setCount(i, index);
		}
		return strikeCount != 3;
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

	public static void main(String[] args) {
		BaseBallGame baseBallGame = new BaseBallGame();
		baseBallGame.setup();
		baseBallGame.printSystemNumbers();
		Scanner sc = new Scanner(System.in);
		boolean isContinue = true;

		while (isContinue) {
			baseBallGame.initializeCount();
			System.out.println("세 개의 숫자를 입력하세요.");
			for (int i = 0; i < 3; i++) {
				System.out.println("숫자를 입력 해주세요.");
				baseBallGame.addUserNumber(sc.nextInt());
			}

			System.out.println("결과를 알려 드리겠습니다.");
			isContinue = baseBallGame.play();
			baseBallGame.printOut();
		}
	}

	private void printSystemNumbers() {
		for(Integer systemNumber : getSystemNumbers()){
			System.out.println(systemNumber + ", ");
		}
	}
}
