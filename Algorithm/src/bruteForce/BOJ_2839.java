package Algorithm.src.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2839 {

    private static int N;
    private static int three;
    private static int five;
    private static int minNum = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for( int i = 0; i <= 1666; i++) {
            three = 3*i;

            if(three > N) {
                break;
            }

            for(int j = 0; j <= 1000; j++) {
                five  = 5*j;

                if(three + five > N) break;

                if(three + five == N) {
                    minNum = i + j;
                    break;
                }
            }

            if(minNum != -1) break;
        }

        System.out.println(minNum);
    }
}
