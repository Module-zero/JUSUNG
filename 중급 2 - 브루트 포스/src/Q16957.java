import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16957 {
    static int r, c;
    static int[][] arr = new int[500][500];
    static int[][] answer = new int[500][500];
    static Pair[][] dp = new Pair[500][500];
    static int[] dx = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 0, 1, 0, -1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                moveBall(i, j, new Pair(-1, -1));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(answer[i][j]).append(" ");
            } sb.append("\n");
        }
        System.out.print(sb);
    }

    static void moveBall(int x, int y, Pair end) {

        Pair e = dp[x][y];
        if (e != null) {
            answer[e.x][e.y]++;
            end.x = e.x;
            end.y = e.y;
            return;
        }

        int nx = -1, ny = -1;
        int min = arr[x][y];
        for (int i = 0; i < 8; i++) {
            int tmp_nx = x + dx[i];
            int tmp_ny = y + dy[i];
            if (tmp_nx >= 0 && tmp_ny >= 0 && tmp_nx < r && tmp_ny < c) {
                if (min > arr[tmp_nx][tmp_ny]) {
                    min = arr[tmp_nx][tmp_ny];
                    nx = tmp_nx;
                    ny = tmp_ny;
                }
            }
        }

        // 인접한 칸의 모든 정수가 현재 칸의 정수보다 큰 경우
        if (nx == -1) {
            dp[x][y] = new Pair(x, y);
            answer[x][y]++;
            end.x = x;
            end.y = y;
            return;
        }

        moveBall(nx, ny, end);
        dp[x][y] = new Pair(end.x, end.y);
    }

    static class Pair {
        private int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
