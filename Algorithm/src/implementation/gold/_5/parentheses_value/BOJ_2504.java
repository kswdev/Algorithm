package implementation.gold._5.parentheses_value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 괄호 '(', ')', '[', ']'
 * 조건
 *  가. 한 상의 괄호로만 이뤄진 '()' 와 '[]' 는 올바른 괄호열이다.
 *  나. 만일 x 가 올바른 괄호열이면 '(x)' 나 '[x]' 도 올바른 괄호열이 된다.
 *  다. x, y  모두 올바른 괄호열이라면 이들을 결합한 xy도 올바른 괄효열이 된다.
 *
 *  라. '()' 인 괄호열의 값은 2이다.
 *  마. '[]' 인 괄호열의 값은 3이다.
 *  바. '(x)' 인 괄호열의 값은 2 x 값(x) 으로 계산된다.
 *  사. '[x]' 인 괄호열의 값은 3 x 값(x) 으로 계산된다.
 *  아. 올바른 괄호열 x와 y가 결합된 xy 괄호열의 값은 값(xy)= 값(x) + 값(y)로 계산된다.
 *
 * 문제
 *  첫째 줄에 그 괄호열의 값을 나타내는 정수를 출력한다. 값이 올바르지 못하다면 0을 출력한다.
 */
public class BOJ_2504 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String parenthesesSequence = br.readLine();

    }
}
