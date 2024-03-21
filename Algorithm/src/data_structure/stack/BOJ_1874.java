package data_structure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1874 {

    private static int N;
    private static Stack<Integer> stack;
    private static List<Character> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        stack = new Stack<>();
        result = new ArrayList<>();

        int start = 1;
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());

            while (start != N+2) {
                if (!stack.isEmpty() && stack.peek() == num) {
                    stack.pop();
                    result.add('-');
                    break;
                }

                stack.push(start);
                start++;
                result.add('+');

                if (stack.peek() == num) {
                    stack.pop();
                    result.add('-');
                    break;
                }
            }
        }


        if (stack.isEmpty()) {
            for (char c : result)
                System.out.println(c);
        } else {
            System.out.println("NO");
        }

    }
}
