import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14499 {
    static int[][] board = new int[20][20];
    static int[] dice = new int[7];
    static int[] dir = new int[1001];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int n, m, sx, sy, k;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            dir[i] = Integer.parseInt(st.nextToken());
        }

        int x = sx;
        int y = sy;

        for (int i = 0; i < k; i++) {

            // 좌표 이동
            x += dx[dir[i] - 1];
            y += dy[dir[i] - 1];

            // 예외 처리
            if (x < 0 || y < 0 || x >= n || y >= m) {
                x -= dx[dir[i] - 1];
                y -= dy[dir[i] - 1];
                continue;
            }

            // 원본 저장
            int d1 = dice[1];
            int d2 = dice[2];
            int d3 = dice[3];
            int d4 = dice[4];
            int d5 = dice[5];
            int d6 = dice[6];

            if (dir[i] == 1) {
                dice[3] = d1;
                dice[1] = d4;
                dice[4] = d6;
                dice[6] = d3;
            } else if (dir[i] == 2) {
                dice[3] = d6;
                dice[1] = d3;
                dice[4] = d1;
                dice[6] = d4;
            } else if (dir[i] == 3) {
                dice[1] = d5;
                dice[2] = d1;
                dice[6] = d2;
                dice[5] = d6;
            } else {
                dice[1] = d2;
                dice[2] = d6;
                dice[6] = d5;
                dice[5] = d1;
            }

            if (board[x][y] == 0) {
                board[x][y] = dice[6];
            } else {
                dice[6] = board[x][y];
                board[x][y] = 0;
            }

            sb.append(dice[1]).append("\n");
        }

        System.out.print(sb);
    }
}
