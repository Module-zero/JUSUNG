import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 하나의 동전만 떨어지는 이동 횟수를 구하는 문제
// 즉 재귀를 사용할 경우 답이 나올 때까지의 재귀의 깊이를 구하는 문제임

// 재귀를 활용한 백트래킹
// 보드의 매 좌표에서 상,하,좌,우로 이동할 수 있으므로 매 단계에서 동일한 일을 수행함
// 따라서 재귀의 특성을 사용하기에 적합함
// 또한 문제에서 '두 동전이 모두 떨어지거나 이동회수가 10이 넘어가는 경우' 와 같은 조건이 주어져있으므로
// 백트래킹을 사용하기에도 적합함

// 관련 문제를 더 많이 풀어보아야 할듯함

public class Q16197 {

    static int N;
    static int M;
    static char[][] board;
    static boolean[][] check;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Integer> loc;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        check = new boolean[N][M];
        loc = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'o') {
                    loc.add(i);
                    loc.add(j);
                }
            }
        }

        go(loc.get(0), loc.get(1), loc.get(2), loc.get(3), 0, 0, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        }
        else {
            System.out.print(min);
        }
    }

    static void go(int x1, int y1, int x2, int y2, int backX, int backY, int count) {

        // 재귀 호출을 10번 넘게 할 경우 리턴
        if (count > 10) {
            return;
        }

        // 두 동전이 같은 위치에 있게되면 이 후 하나만 떨어지는 경우는 발생하지 않으므로 리턴
        if ((x1 == x2) && (y1 == y2)) {
            return;
        }

        // 두 동전이 동시에 떨어진 경우 리턴
        if (check(x1, y1) && check(x2, y2)) {
            return;
        }

        // 한 동전만 떨어진 경우 정답 후보 검사
        if ((check(x1, y1) && !check(x2, y2)) || (!check(x1, y1) && check(x2, y2))) {
            if (min > count) {
                min = count;
            }
            return;
        }

        // 여기까지 과정에 해당하지 않을 경우 두 동전은 일단 반드시 보드 안에 있는 경우임
        // 두 동전 모두 벽을 만난 경우 리턴
        if (board[x1][y1] == '#' && board[x2][y2] == '#') {
            return;
        }

        // 1번 동전만 벽을 만난 경우 1번 동전의 위치만 원상복귀
        if (board[x1][y1] == '#' && board[x2][y2] != '#') {
            x1 += backX;
            y1 += backY;
        }

        // 2번 동전만 벽을 만난 경우 2번 동전의 위치만 원상복귀
        if (board[x1][y1] != '#' && board[x2][y2] == '#') {
            x2 += backX;
            y2 += backY;
        }

        // 여기까지의 과정에 해당하지 않을 경우 현 위치에서 다시 모든 방향으로 이동가능
        // 그러나 현 위치에서의 탐색을 이전에 진행했던 경우에는 중복탐색을 막기 위해 리턴
        if (check[x1][y1] && check[x2][y2]) {
            return;
        }

        // 이후 재귀 과정에서 중복탐색을 막음
        check[x1][y1] = check[x2][y2] = true;

        // 위 방향으로 이동
        go(x1 - 1, y1, x2 - 1, y2, 1, 0, count + 1);

        // 아래 방향으로 이동
        go(x1 + 1, y1, x2 + 1, y2, -1, 0, count + 1);

        // 왼쪽 방향으로 이동
        go(x1, y1 - 1, x2, y2 - 1, 0, 1, count + 1);

        // 오른쪽 방향으로 이동
        go(x1, y1 + 1, x2, y2 + 1, 0, -1, count + 1);

        check[x1][y1] = check[x2][y2] = false;
    }

    // 좌표가 보드를 벗어났는지를 체크하는 함수
    static boolean check(int x, int y) {
        if ((x < 0 || x >= N) || (y < 0 || y >= M)) {
            return true;
        }
        else {
            return false;
        }
    }
}
