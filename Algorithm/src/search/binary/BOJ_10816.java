package search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10816 {

    private static int N, M;
    private static int[] sang, nature;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sang = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            sang[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        nature = new int[M+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            nature[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sang);


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            sb.append(uSearch(nature[i]) - dSearch(nature[i])).append(' ');
        }

        System.out.println(sb);
    }

    private static int dSearch(int key) {

        int from = 0;
        int to = sang.length;

        while (from < to) {
            int mid = (from + to) / 2;
            int midVal = sang[mid];

            if (midVal >= key)
                to = mid;
            else
                from = mid + 1;
        }

        return to;
    }

    private static int uSearch(int key) {

        int from = 0;
        int to = sang.length;

        while (from < to) {
            int mid = (from + to) / 2;
            int midVal = sang[mid];

            if (midVal > key)
                to = mid;
            else
                from = mid + 1;
        }

        return to;
    }
}
