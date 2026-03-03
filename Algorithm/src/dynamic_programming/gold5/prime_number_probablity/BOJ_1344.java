package dynamic_programming.gold5.prime_number_probablity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BOJ_1344 {

    private static float aProbability;
    private static float bProbability;

    private static float[][] aTeam;
    private static float[][] bTeam;

    private static List<Integer> primeNumber = List.of(2, 3, 5, 7, 11, 13, 17);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        aProbability = Integer.parseInt(br.readLine()) / 100.0f;
        bProbability = Integer.parseInt(br.readLine()) / 100.0f;

        aTeam = new float[19][19];
        bTeam = new float[19][19];

        aTeam[0][0] = 1;
        bTeam[0][0] = 1;

        for (int i = 1; i <= 18; i++) {

            aTeam[i][0] = aTeam[i-1][0] * (1 - aProbability);
            bTeam[i][0] = bTeam[i-1][0] * (1 - bProbability);

            for (int j = 1; j < i; j++) {
                aTeam[i][j] = aTeam[i-1][j] * (1 - aProbability) + aTeam[i-1][j-1] * aProbability;
                bTeam[i][j] = bTeam[i-1][j] * (1 - bProbability) + bTeam[i-1][j-1] * bProbability;
            }

            aTeam[i][i] = aTeam[i-1][i-1] * aProbability;
            bTeam[i][i] = bTeam[i-1][i-1] * bProbability;
        }

        float aPrimeProbability = 0;
        float bPrimeProbability = 0;
        float aNotPrimeProbability = 0;
        float bNotPrimeProbability = 0;

        for (int i = 0; i <= 18; i++) {
            if (primeNumber.contains(i)) {
                aPrimeProbability += aTeam[18][i];
                bPrimeProbability += bTeam[18][i];
            } else {
                aNotPrimeProbability += aTeam[18][i];
                bNotPrimeProbability += bTeam[18][i];
            }
        }

        System.out.println((aPrimeProbability * bPrimeProbability) + (aPrimeProbability * bNotPrimeProbability) + (aNotPrimeProbability * bPrimeProbability));
    }
    // 소수 : 2, 3, 5, 7, 11, 13, 17

    // 두 팀 소수 확률 = (a 소 * b 소x) + (a 소x * b소) + (a 소 * b 소)
}
