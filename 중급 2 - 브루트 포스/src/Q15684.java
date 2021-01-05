import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15684 {
    static int n, m, h;
    static int[][] ladder = new int[100][100];
    static ArrayList<int[]> candidate = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = b+1;
            ladder[a][b+1] = b;
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                if (j != n && ladder[i][j] == 0 && ladder[i][j+1] == 0) {
                    // 사다리를 놓을 수 있는 후보 좌표들
                    candidate.add(new int[]{i, j});
                }
            }
        }

        // 사다리를 0개 추가하는 경우
        if (checkResult()) {
            System.out.print(0);
            return;
        }

        // 사다리를 i 개 추가하는 경우
        for (int i = 1; i <= 3; i++) {
            visited = new boolean[9*30+1];
            if (go(0, 0, i)) {
                System.out.print(i);
                return;
            }
        }

        // 답이 나오지 않는 경우 혹은 답이 3 을 넘는 경우
        System.out.print(-1);
    }

    static boolean go(int index, int start, int ladderCount) {

        if (index == ladderCount) {
            return checkResult();
        }

        for (int i = start; i < candidate.size(); i++) {
            if (!visited[i]) {
                int[] p = candidate.get(i);
                int x = p[0], y = p[1];

                // 백트래킹
                if (ladder[x][y] != 0 || ladder[x][y+1] != 0) {
                    continue;
                }

                visited[i] = true;
                ladder[x][y] = y+1;
                ladder[x][y+1] = y;
                if (go(index+1, i+1, ladderCount)) {
                    return true;
                }
                ladder[x][y] = 0;
                ladder[x][y+1] = 0;
                visited[i] = false;
            }
        }
        return false;
    }

    // i 번 세로선의 결과가 i 번이 나오는지를 체크
    static boolean checkResult() {
        for (int i = 1; i <= n; i++) {
            int prevCol = i, nextCol = 0;
            for (int j = 1; j <= h + 1; j++) {
                nextCol = ladder[j][prevCol];
                if (nextCol != 0) {
                    prevCol = nextCol;
                }
            }
            if (i != prevCol) {
                return false;
            }
        }
        return true;
    }
}
