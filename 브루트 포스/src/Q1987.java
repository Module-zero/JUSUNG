import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 말이 지날 수 있는 최대의 칸수는?
// -> 출발점부터 dfs 를 해서 '가장 깊이 들어가는 경우' 가 최대의 칸수임
public class Q1987 {

    static int R;
    static int C;
    static boolean[] check;
    static char[][] board;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        check = new boolean[26];
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        check[board[0][0] - 65] = true;
        go(1, 0, 0);
        System.out.print(max);
    }

    static void go(int depth, int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                max = Math.max(max, depth);
                continue;
            }

            if (check[board[nx][ny] - 65]) {
                max = Math.max(max, depth);
                continue;
            }

            check[board[nx][ny] - 65] = true;
            go(depth + 1, nx, ny);
            check[board[nx][ny] - 65] = false;
        }
    }
}
