package dynamic_programming.gold1.palindrome;

/*
 *
 * 팰린드롬 공장 - https://www.acmicpc.net/problem/1053
 *
 * 팰린드롬이란, 앞에서부터 읽었을 때와, 뒤에서부터 읽었을 때가 같은 문자열이다.
 *
 * 4가지 연산으로 보통 문자열을 팰린드롬으로 만든다.
 *  1. 문자열의 어떤 위치에 어떤 문자를 삽입 (시작과 끝도 가능)
 *  2. 어떤 위치에 있는 문자를 삭제
 *  3. 어떤 위치에 있는 문자를 교환
 *  4. 서로 다른 문자를 교환
 *  5. 1, 2, 3번 연산은 마음껏 사용할 수 있지만, 마지막 연산은 많아야 한 번 사용할 수 있다.
 *
 * 문제
 *  문자열이 주어졌을 때, 팰린드롬으로 만들기 위해 필요한 연산의 최솟값을 출력하는 프로그램을 작성하시오.
 *
 * 입력
 *  첫째 줄에 문자열이 주어진다. 영어 소문자로만 이루어져 있고, 길이는 최대 50이다.
 *
 * 출력
 *  첫째 줄에 문제의 정답을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1053 {

    private static int[][] dp;
    private static String sequence;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            sequence = br.readLine();
        }

        dp = new int[sequence.length()][sequence.length()];

        initDp();

        int ans = makePalindrome(0, sequence.length()-1, sequence);

        // 문자열 내 교환
        for (int i = 0; i < sequence.length(); i++) {
            for (int j = i+1; j < sequence.length(); j++) {
                initDp();
                String swaped = swap(sequence, i, j);
                ans = Math.min(ans, makePalindrome(0, swaped.length()-1, swaped) + 1);
            }
        }

        System.out.println(ans);
    }

    private static int makePalindrome(int left, int right, String sequence) {

        if (left >= right) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        // 삽입, 삭제는 같음
        // 왼쪽 삽입, 오른쪽 삽입, 교환
        int isEqual = sequence.charAt(left) == sequence.charAt(right) ? 0 : 1;
        dp[left][right] = tripleMin(makePalindrome(left, right-1, sequence) + 1,
                                    makePalindrome(left+1, right, sequence) + 1,
                                    makePalindrome(left+1, right-1, sequence) + isEqual);

        return dp[left][right];
    }

    private static int tripleMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private static void initDp() {
        for (int[] ints : dp) Arrays.fill(ints, -1);
    }

    private static String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();

        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

        return new String(chars);
    }
}
