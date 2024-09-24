package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2775 {

    private static int T;
    private static int[][] apart;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        apart = new int[15][15];

        for (int i = 1; i <= 14; i++) {
            apart[0][i] = i;
        }

        for (int i = 0; i < T; i++) {
            int floor = Integer.parseInt(br.readLine());
            int ho = Integer.parseInt(br.readLine());

            System.out.println(countPerson(floor, ho));
        }
    }

    private static int countPerson(int floor, int ho) {
        if (apart[floor][ho] != 0) return apart[floor][ho];

        for (int i = 1; i <= ho; i++) {
            apart[floor][ho] += countPerson(floor-1, i);
        }

        return apart[floor][ho];
    }
}
