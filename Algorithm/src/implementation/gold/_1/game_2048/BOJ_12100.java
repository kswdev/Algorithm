package implementation.gold._1.game_2048;

/*
 *
 * 이 게임에서 한 번의 이동은 보드 위에 있는 전체 블록을 상하좌우 네 방향 중 하나로 이동시키는 것이다.
 * 이때, 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다.
 * 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.
 * (실제 게임에서는 이동을 한 번 할 때마다 블록이 추가되지만, 이 문제에서 블록이 추가되는 경우는 없다)
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_12100 {

    private static int n;
    private static int max = 0;
    private static List<List<Integer>> comb = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(3, map);
        for (int i = 1; i <= n; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        //start(map);
        //System.out.println(max);
    }

    private static void start(int[][] board) {
        combination(5, new ArrayList<>());


        for (List<Integer> list : comb) {
            int[][] copyBoard = copyBoard(board);
            max = Math.max(game(list, copyBoard), max);
        }
    }

    private static int game(List<Integer> directions, int[][] board) {

        for (int dir : directions) {
            move(dir, board);
        }

        return findMax(board);
    }

    // block : board[index][i] 가 가리키는 요소
    private static void move(int dir, int[][] board) {
        switch (dir) {
            case 0:
                for (int i = 1; i <= n; i++) {
                    int block = 0;
                    int index = 1;
                    for (int j = 1; j <= n; j++) {
                        if (board[j][i] != 0) {
                            if (board[j][i] == block) {
                                board[index - 1][i] = block * 2;
                                board[j][i] = 0;
                                block = 0;
                            } else {
                                block = board[j][i];
                                board[j][i] = 0;
                                board[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;

            case 1:
                for (int i = 1; i <= n; i++) {
                    int block = 0;
                    int index = n;
                    for (int j = n; j >= 1; j--) {
                        if (board[j][i] != 0) {
                            if (board[j][i] == block) {
                                board[index + 1][i] = block * 2;
                                board[j][i] = 0;
                                block = 0;
                            } else {
                                block = board[j][i];
                                board[j][i] = 0;
                                board[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;

            case 2:
                for (int i = 1; i <= n; i++) {
                    int block = 0;
                    int index = 1;
                    for (int j = 1; j <= n; j++) {
                        if (board[i][j] != 0) {
                            if (board[i][j] == block) {
                                board[i][index - 1] = block * 2;
                                board[i][j] = 0;
                                block = 0;
                            } else {
                                block = board[i][j];
                                board[i][j] = 0;
                                board[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
                break;

            case 3:
                for (int i = 1; i <= n; i++) {
                    int block = 0;
                    int index = n;
                    for (int j = n; j >= 1; j--) {
                        if (board[i][j] != 0) {
                            if (board[i][j] == block) {
                                board[i][index + 1] = block * 2;
                                board[i][j] = 0;
                                block = 0;
                            } else {
                                block = board[i][j];
                                board[i][j] = 0;
                                board[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
        }
    }

    private static void combination(int depth, List<Integer> list) {
        if (depth == 0) {
            comb.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i <= 3; i++) {
            list.add(i);
            combination(depth - 1, list);
            list.remove(list.size() - 1);
        }
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] copyBoard = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            copyBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copyBoard;
    }

    public static int findMax(int[][] map) {
        int answer = 0;
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                answer = Math.max(answer, map[i][j]);

        return answer;
    }
}
