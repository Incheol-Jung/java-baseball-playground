package study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }
    
    @Test
    public void split() throws Exception {
        // given
        String firstText = "1";
        String secondText = "2";
        String plainText = firstText + "," + secondText;
        
        // when
        String[] splitTexts = plainText.split(","); 
        
        // then
        assertThat(splitTexts.length > 0).isTrue();
        assertThat(splitTexts[0].equals(firstText)).isTrue();
        assertThat(splitTexts[1].equals(secondText)).isTrue();
        
        // 고민 1. firstText, secondText를 array 형으로 선언해서 자동으로 + 되도록 하면 assert문이나 할당하는 로직을 루프로 자동화 할수 있지 않을까?
        // 고민 2. 현재는 케이스가 "1,2"라서 두개의 String 인스턴스를 생성하였는데 문자열이 더 길어지면 '+' 로직은 힙 메모리에 새로운 객체로 생성되므로 테스트케이스가 애플리케이션 성능에 영향을 주지는 않을까?
        
    }
}
