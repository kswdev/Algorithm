package dynamic_programming.gold4.hotel;

/*
 *
 * 문제.
 *  형택이가 홍보를 할 수 있는 도시가 주어지고, 각 도시별로 홍보하는데 드는 비용과, 그 때 몇 명의 호텔 고객이 늘어나는지에 대한 정보가 있다.
 *  예를 들어, “어떤 도시에서 9원을 들여서 홍보하면 3명의 고객이 늘어난다.”와 같은 정보이다.
 *  이때, 이러한 정보에 나타난 돈에 정수배 만큼을 투자할 수 있다.
 *  각 도시에는 무한 명의 잠재적인 고객이 있다.
 *  이때, 호텔의 고객을 적어도 C명 늘이기 위해 형택이가 투자해야 하는 돈의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * 입력.
 *  첫째 줄에 C와 형택이가 홍보할 수 있는 도시의 개수 N이 주어진다.
 *  둘째 줄부터 N개의 줄에는 각 도시에서 홍보할 때 대는 비용과 그 비용으로 얻을 수 있는 고객의 수가 주어진다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1106 {

    private static List<City> cities = new ArrayList<>();
    private static int[][] dp;
    private static int MAX = 20000;
    private static int MIN_CUSTOMER;
    private static int NUM_OF_CITY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        MIN_CUSTOMER = Integer.parseInt(st.nextToken());
        NUM_OF_CITY = Integer.parseInt(st.nextToken());

        dp = new int[NUM_OF_CITY+1][MIN_CUSTOMER+1];
        cities.add(new City(0, 0));

        for (int i = 1; i <= NUM_OF_CITY; i++) {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            cities.add(new City(price, customer));

            for (int j = 1; j <= MIN_CUSTOMER; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(minCost(1, cities.get(1).getCustomer()));
        System.out.println(dp);
    }

    private static int minCost(int cityNum, int numOfCustomer) {
        if (cityNum > NUM_OF_CITY) return MAX;

        City city = cities.get(cityNum);

        if (numOfCustomer >= MIN_CUSTOMER) return city.getPrice();
        if (dp[cityNum][city.getPrice()] != -1) return dp[cityNum][city.getPrice()];

        int visitSameCityCost = city.getPrice() + minCost(cityNum, numOfCustomer + city.getCustomer());
        int visitNextCityCost = city.getPrice() + minCost(cityNum + 1, numOfCustomer);

        dp[cityNum][city.getPrice()] = Math.min(visitSameCityCost, visitNextCityCost);

        return dp[cityNum][city.getPrice()];
    }

    private static class City {
        private int price;
        private int customer;

        public City(int price, int customer) {
            this.price = price;
            this.customer = customer;
        }

        public int getPrice() {
            return price;
        }

        public int getCustomer() {
            return customer;
        }
    }
}
