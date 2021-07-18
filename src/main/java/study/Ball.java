package study;

import java.util.Objects;

public class Ball {
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ball ball = (Ball)o;
		return index == ball.index && value == ball.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(index, value);
	}

	private int index;
	private int value;
	
	public Ball(int index, int value) {
		this.index = index;
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
