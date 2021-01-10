import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16957 {
    static int r, c;
    static int[][] arr = new int[500][500];
    static int[][] answer = new int[500][500];
    static Pair[][] next = new Pair[500][500];
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
                denoteNext(i, j);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Pair e = new Pair(-1, -1);
                moveBall(i, j, e);
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

        int nx = next[x][y].x;
        int ny = next[x][y].y;
        moveBall(nx, ny, end);
        dp[x][y] = new Pair(end.x, end.y);
    }

    static void denoteNext(int x, int y) {

        int min = arr[x][y];
        Pair p = new Pair(-1, -1);

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
                if (min > arr[nx][ny]) {
                    min = arr[nx][ny];
                    p.x = nx;
                    p.y = ny;
                }
            }
        }
        if (p.x == -1 && p.y == -1) {
            dp[x][y] = new Pair(x, y);
        }
        next[x][y] = p;
    }

    static class Pair {
        private int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
