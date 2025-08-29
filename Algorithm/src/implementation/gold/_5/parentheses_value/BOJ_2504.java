package implementation.gold._5.parentheses_value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    private static char openSmallParentheses = '(';
    private static char closeSmallParentheses = ')';
    private static char openBigParentheses = '[';
    private static char closeBigParentheses = ']';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String parentheses = br.readLine();

        try {
            int result = calculateParentheses(parentheses, 0, parentheses.length() - 1);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println(0);
        }
    }

    private static int calculateParentheses(String parentheses, int startIndex, int endIndex) {
        int sum = 0;

        for (int i = startIndex; i < endIndex; i = getMatchingParenthesesEndIndex(parentheses, i) + 1) {
            if (endIndex == -1)
                throw new IllegalArgumentException();

            char startParentheses = parentheses.charAt(i);

            if (startParentheses == openSmallParentheses && parentheses.charAt(i+1) == closeSmallParentheses)
                sum += 2;
            else if (startParentheses == openBigParentheses && parentheses.charAt(i+1) == closeBigParentheses)
                sum += 3;
            else if (startParentheses == openSmallParentheses)
                sum += 2 * calculateParentheses(parentheses, i + 1, endIndex - 1);
            else if (startParentheses == openBigParentheses)
                sum += 3 * calculateParentheses(parentheses, i + 1, endIndex - 1);
        }

        return sum;
    }

    private static int getMatchingParenthesesEndIndex(String parentheses, int startIndex) {
        Stack<Character> stack = new Stack<>();
        stack.push(parentheses.charAt(startIndex));

        for (int i = startIndex + 1; i < parentheses.length(); i++) {

            char startParentheses = stack.peek();

            if (matching(startParentheses, parentheses.charAt(i)))
                stack.pop();
            else
                stack.push(parentheses.charAt(i));

            if (stack.isEmpty()) return i;
        }

        return -1;
    }

    private static boolean matching(char parenthesesA,  char parenthesesB) {

        if (parenthesesA == openSmallParentheses && parenthesesB == closeSmallParentheses) {
            return true;
        } else {
            return parenthesesA == openBigParentheses && parenthesesB == closeBigParentheses;
        }
    }
}
