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

        int pointXShare = (T + x) / W;
        int pointYShare = (T + y) / H;
        int pointXRemain = (T + x) % W;
        int pointYRemain = (T + y) % H;

        int pointX;
        int pointY;

        if (pointXShare % 2 == 0)
            pointX = pointXRemain;
        else
            pointX = W - pointXRemain;

        if (pointYShare % 2 == 0)
            pointY = pointYRemain;
        else
            pointY = H - pointYRemain;

        System.out.println(pointX + " " + pointY);
    }
}
