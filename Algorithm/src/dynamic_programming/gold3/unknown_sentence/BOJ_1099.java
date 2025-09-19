package dynamic_programming.gold3.unknown_sentence;

/*
 *
 * 알 수 없는 문장 - https://www.acmicpc.net/problem/1099
 *
 *
 * 새로운 언어를 만들었다.
 *  1. 이 언어에는 단어가 N개 있다.
 *  2. 이 언어의 문장은 단어를 공백없이 붙여쓴 것이다.
 *  3. 이 문장에서 각 단어는 0번 또는 그 이상 나타날 수 있다.
 *  4. 단어에 쓰여 있는 문자의 순서를 바꿔도 된다.
 *  5. 원래 단어의 위치와 다른 위치에 있는 문자의 개수 만큼이 그 순서를 바꾼 단어를 만드는 비용이다.
 *
 * 문제
 *  한 문장을 여러 가지 방법으로 해석할 수 있다. 이때 비용의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 *  첫째 줄에 문장이 주어진다. 문장의 길이는 최대 50이다.
 *  둘째 줄에 단어의 개수 N이 주어지며, N은 50보다 작거나 같은 자연수이다.
 *  셋째 줄부터 N개의 줄에 각 단어가 주어진다. 단어의 길이는 최대 50이다.
 *
 * 출력
 *  첫째 줄에 문제의 정답을 출력한다. 만약 문장을 해석할 수 없다면 -1을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1099 {

    private static int INF = 51;
    private static int N;
    private static int[] dp;
    private static String sentence;
    private static int sLen;
    private static String[] words;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            sentence = br.readLine();
            sLen = sentence.length();
            dp = new int[sLen+1];
            Arrays.fill(dp, INF);

            N = Integer.parseInt(br.readLine());
            words = new String[N];

            for (int i = 0; i < N; i++) {
                words[i] = br.readLine();
            }
        }

        dp[sLen] = 0;

        for (int to = sLen; to >= 0; to--) {
            for (String candidate : words) {
                int from = to - candidate.length();
                if (from < 0) continue;
                int result = countMissMatch(candidate, from, to);
                if (result != INF) {
                    dp[from] = Math.min(dp[from], result + dp[to]);
                }
            }
        }

        System.out.println(dp[0]);
    }

/************ 탑 다운 ************/
/*
    private static int decryptSentenceTryMinCount(int from, int to) {

        if (from > to) return 0;
        if (dp[from] != INF) return dp[from];

        for (int i = 0; i < N; i++) {
            String candidate = words[i];
            int targetIndex = from + candidate.length();
            int missMatchCount = countMissMatch(candidate, from, targetIndex);
            if (missMatchCount != INF)
                dp[from] = Math.min(dp[from], missMatchCount + decryptSentenceTryMinCount(targetIndex, to));
        }

        return dp[from];
    }
*/

    private static int countMissMatch(String candidate, int from, int to) {
        if (to > sentence.length()) return INF;

        String target = sentence.substring(from, to);
        int[] freq = new int[26];

        int count = 0;

        for (int i = 0; i < target.length(); i++) {
            char t =  target.charAt(i);
            char c =  candidate.charAt(i);

            if (t != c) {
                count++;
                freq[c - 'a']++;
                freq[t - 'a']--;
            }
        }

        int sum = 0;
        for (int f : freq) sum += Math.abs(f);

        return (sum == 0) ? count : INF;
    }
}
