package programmers.lv3.train_rails;

public class Solution {
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, 1, -1};

    private int UP = 0;
    private int DOWN = 1;
    private int RIGHT = 2;
    private int LEFT = 3;

    private int n, m;

    private int[][] map;
    private int[][] visited;
    
    public int solution(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        map = grid;
        visited = new int[n][m];
        visited[0][0] = 1;
        return dfs(0, 0, RIGHT);
    }

    private int dfs(int x, int y, int dir) {
        // 도착 했는지 확인
        if (x == n - 1 && y == m - 1 && isValid()) return 1;

        int answer = 0;

        // 다음 좌표를 구한다.
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 가. 갈 수 없다면 0 반환
        if (isOverGrid(nx, ny) || map[nx][ny] == -1) return 0;

        // 나. 갈 수 있다면 놓을 수 있는 레일 배열 구하기
        int[] availableRails = availableRails(dir);

        // 다음 그리드가 빈 배열일 경우
        int nextGrid = map[nx][ny];

        // 다음 그리드가 빈 배열이 아닐 경우
        if (nextGrid != 0) {
            // 다음 그리드를 갈 수 있고 && 방문 하지 않은 경우
            if (contains(availableRails, nextGrid) && isPassable(nx, ny, nextGrid)) {
                return move(nx, ny, nextDir(nextGrid, dir));
            } else {
                return 0;
            }
        }

        for (int nextRail : availableRails) {
            map[nx][ny] = nextRail;
            answer += move(nx, ny, nextDir(nextRail, dir));
            map[nx][ny] = 0;
        }

        // 가능한 경우의 수 반환
        return answer;
    }

    private boolean isValid() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (1 <= map[i][j] && map[i][j] <= 7) {
                    if (map[i][j] == 3 && visited[i][j] != 2)
                        return false;
                    else if (map[i][j] != 3 && visited[i][j] != 1)
                        return false;
                }
            }
        }
        return true;
    }

    private boolean isOverGrid(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    private boolean isPassable(int x, int y, int rail) {
        return rail == 3
                ? visited[x][y] < 2
                : visited[x][y] == 0;
    }

    private int move(int nx, int ny, int nextDir) {
        visited[nx][ny]++;
        int result = dfs(nx, ny, nextDir);
        visited[nx][ny]--;
        return result;
    }

    private int[] availableRails(int dir) {
        if (dir == UP) {
            return new int[] {2, 3, 6, 7};
        } else if (dir == DOWN) {
            return new int[] {2, 3, 4, 5};
        } else if (dir == RIGHT) {
            return new int[] {1, 3, 4, 7};
        } else if (dir == LEFT) {
            return new int[] {1, 3, 5, 6};
        } else {
            throw new IllegalArgumentException("Invalid direction in availableRails, dir: " + dir);
        }
    }

    private boolean contains(int[] rails, int rail) {
        for (int r : rails) {
            if (r == rail) return true;
        }
        return false;
    }

    private int nextDir(int rail, int dir) {
        if (dir == UP) {
            if (rail == 2 || rail == 3) return UP;
            else if (rail == 6) return RIGHT;
            else if (rail == 7) return LEFT;
            else throw new IllegalArgumentException("Invalid rail : " + rail + " dir: " + dir);
        } else if (dir == DOWN) {
            if (rail == 2 || rail == 3) return DOWN;
            else if (rail == 4) return LEFT;
            else if (rail == 5) return RIGHT;
            else throw new IllegalArgumentException("Invalid rail : " + rail + " dir: " + dir);
        } else if (dir == RIGHT) {
            if (rail == 1 || rail == 3) return RIGHT;
            else if (rail == 4) return UP;
            else if (rail == 7) return DOWN;
            else throw new IllegalArgumentException("Invalid rail : " + rail + " dir: " + dir);
        } else if (dir == LEFT) {
            if (rail == 1 || rail == 3) return LEFT;
            else if (rail == 5) return UP;
            else if (rail == 6) return DOWN;
            else throw new IllegalArgumentException("Invalid rail : " + rail + " dir: " + dir);
        }
        else throw new IllegalArgumentException("Invalid direction in fromTo, dir: " + dir);
    }
}
