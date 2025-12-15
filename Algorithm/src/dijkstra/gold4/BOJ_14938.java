package dijkstra.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.AbstractMap.*;
import static java.util.Comparator.*;

/*
 * 서강 그라운드
 *
 * 문제.
 *  여러 지역중 하나에 내려 그 지역의 아이템을 먹는다.
 *  어디에 떨어져야 수색 범위 내에 가장 많은 아이템을 얻을 수 있는가?
 *
 * 조건.
 *  각 지역은 일정한 길이의 길로 다른 지여고가 연결되어 있고 양방향이다.
 *  낙하한 지역을 중심으로 수색 범위 m 이내의 모든 지역의 아이템을 얻을 수 있다고 할 때 최대 갯수를 구하자.
 *
 * 입력.
 *  첫째 줄 : 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)이 주어진다.
 *  둘째 줄 : n개의 숫자가 차례대로 각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)를 알려준다.
 *  셋째 줄 : r+2번째 줄 까지 길 양 끝에 존재하는 지역의 번호 a, b, 그리고 길의 길이 l (1 ≤ l ≤ 15)가 주어진다.
 */
public class BOJ_14938 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Ground ground = new Ground();

        int cityCount = Integer.parseInt(st.nextToken());
        int searchAreaWidth = Integer.parseInt(st.nextToken());
        int roadCount = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int cityName = 1; cityName <= cityCount; cityName++) {
            int itemCount = Integer.parseInt(st.nextToken());
            ground.addCity(new City(cityName, itemCount));
        }

        for (int i = 0; i < roadCount; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            ground.link(from, to, cost);
        }

        int maxCount = ground.searchMaxItemIn(searchAreaWidth);

        System.out.println(maxCount);
    }

    private static class Ground {
        private List<City> cities = new ArrayList<>();

        private void addCity(City city) {
            this.cities.add(city);
        }

        private City getCityById(int id) {
            return cities.get(id-1);
        }

        private void link(int from, int to, int cost) {
            City city1 = getCityById(from);
            City city2 = getCityById(to);

            city1.addAvailableCity(new SimpleEntry<>(city2, cost));
            city2.addAvailableCity(new SimpleEntry<>(city1, cost));
        }

        private int getCityCount() {
            return this.cities.size();
        }

        private int searchMaxItemIn(int areaWidth) {
            int maxItemCount = 0;

            for (int i = 1; i <= this.getCityCount(); i++) {
                maxItemCount = Math.max(maxItemCount, dijkstra(i, areaWidth));
            }

            return maxItemCount;
        }

        //방문자, 누적 아이템수, 잔여 수색 범위
        private int dijkstra(int startCity, int maxAreaWidth) {
            Queue<SimpleEntry<City, Status>> queue = new PriorityQueue<>(comparingInt(entry -> entry.getValue().getAreaWidth()));
            boolean[] visited = new boolean[this.getCityCount()+1];
            int result = 0;

            City city = this.getCityById(startCity);
            queue.add(new SimpleEntry<>(city, new Status(city.getItemCount(), 0)));

            while (!queue.isEmpty()) {
                SimpleEntry<City, Status> entry = queue.poll();
                City currentCity = entry.getKey();
                Status currentStatus = entry.getValue();

                int currentSearchAreaWidth = currentStatus.getAreaWidth();
                int currentItemCount = currentStatus.getItems();

                if (visited[currentCity.getCityName()]) continue;

                visited[currentCity.getCityName()] = true;

                result += currentItemCount;

                for (SimpleEntry<City, Integer> nextEntry : currentCity.getAvailableCity()) {
                    City nextCity = nextEntry.getKey();
                    Integer nextSearchAreaWidth = nextEntry.getValue();

                    if (visited[nextCity.getCityName()] || currentSearchAreaWidth + nextSearchAreaWidth > maxAreaWidth)
                        continue;

                    queue.add(new SimpleEntry<>(nextCity, new Status(currentItemCount + nextCity.getItemCount(), currentSearchAreaWidth + nextSearchAreaWidth)));
                }
            }

            return result;
        }
    }

    private static class Status {
        private int items;
        private int areaWidth;

        public Status(int items, int areaWidth) {
            this.items = items;
            this.areaWidth = areaWidth;
        }

        public int getItems() {
            return items;
        }

        public int getAreaWidth() {
            return areaWidth;
        }
    }

    private static class City {
        private int cityName;
        private int itemCount;
        private List<SimpleEntry<City, Integer>> availableCity = new ArrayList<>();

        private City(int cityName, int itemCount) {
            this.cityName = cityName;
            this.itemCount = itemCount;
        }

        private void addAvailableCity(SimpleEntry<City, Integer> city) {
            this.availableCity.add(city);
        }

        private List<SimpleEntry<City, Integer>> getAvailableCity() {
            return availableCity;
        }

        private int getItemCount() {
            return itemCount;
        }

        public int getCityName() {
            return cityName;
        }
    }
}
