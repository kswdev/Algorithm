package dynamic_programming.gold2.news;

/*
 * 문제.
 *  민식이는 회사의 매니저이다. 그리고, 민식이는 회사의 중요한 뉴스를 모든 직원에게 빠르게 전달하려고 한다.
 *  민식이의 회사는 트리 구조이다.
 *  모든 직원은 정확하게 한 명의 직속 상사가 있다.
 *  자기자신은 그들 자기 자신의 직접 또는 간접 상사가 아니고,
 *  모든 직원은 민식이의 직접 또는 간접적인 부하이다.
 *
 *  민식이는 일단 자기 자신의 직속 부하에게 한 번에 한 사람씩 전화를 한다.
 *  뉴스를 들은 후에, 각 부하는 그의 직속 부하에게 한 번에 한 사람씩 전화를 한다.
 *  이 것은 모든 직원이 뉴스를 들을 때 까지 계속된다.
 *  모든 사람은 자신의 직속 부하에게만 전화를 걸 수 있고,
 *  전화는 정확하게 1분 걸린다.
 *  이때 모든 직원이 소식을 듣는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.
 *
 *  오민식의 사원 번호는 0이고, 다른 사원의 번호는 1부터 시작한다.
 *
 * 입력.
 *  첫째 줄에 직원의 수 N이 주어진다.
 *  둘째 줄에는 0번 직원부터 그들의 상사의 번호가 주어진다.
 *  0번 직원 (오민식)은 상사가 없기 때문에 -1이고, 나머지 직원 i의 상사 번호는 i보다 작거나 같은 음이 아닌 정수이다.
 *
 * 출력.
 *  첫째 줄에 모든 소식을 전하는데 걸리는 시간의 최솟값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1135 {

    private static List<Employee> employees = new ArrayList<>();
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        dp = new int[n];

        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            Employee employee = new Employee(i);
            employees.add(employee);

            if (parent != -1) {
                employees.get(parent).addSubordinate(employee);
            }
        }

        System.out.println(dfs(employees.get(0)));
    }

    private static int dfs(Employee employee) {

        if (employee.getSubordinates().isEmpty()) return 0;

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < employee.getSubordinates().size(); i++ ) {
            int subordinateId = employee.getSubordinates().get(i).getId();

            dp[subordinateId] = dfs(employee.getSubordinates().get(i));
            queue.add(dp[subordinateId]);
        }

        int currTime = 0;
        while (!queue.isEmpty()) {
            int time = queue.poll();
            currTime++;

            dp[employee.getId()] = Math.max(dp[employee.getId()], currTime + time);
        }

        return dp[employee.getId()];
    }

    private static class Employee {
        private int id;
        private List<Employee> subordinates = new ArrayList<>();

        public Employee(int id) {
            this.id = id;
        }

        public void addSubordinate(Employee employee) {
            this.subordinates.add(employee);
        }

        public int getId() {
            return id;
        }

        public List<Employee> getSubordinates() {
            return subordinates;
        }
    }
}
