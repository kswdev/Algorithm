package ad_hoc.silver.ant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10158 {

    private static int W, H, T;
    public static void main(String[] args) throws IOException {

        int x;
        int y;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            T = Integer.parseInt(br.readLine());
        }

        int[] point = destination(x, y, T);

        System.out.println(point[0] + " " + point[1]);
    }

    private static int[] destination(int x, int y, int t) {
        int initDx = 1;
        int initDy = 1;


        for (int i = 0; i < t; i++) {
            if (x + initDx > W || x + initDx < 0) {
                initDx *= -1;
            }

            if (y + initDy > H || y + initDy < 0) {
                initDy *= -1;
            }

            x += initDx;
            y += initDy;
        }

        return new int[]{x, y};
    }
}
