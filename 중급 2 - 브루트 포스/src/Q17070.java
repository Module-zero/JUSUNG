import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17070 {
    static int n;
    static int cnt = 0;
    static int[][] arr = new int[16][16];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 1);
        System.out.print(cnt);
    }

    static void dfs(int x1, int y1, int x2, int y2) {

        if (x2 == n-1 && y2 == n-1) {
            cnt++;
            return;
        }

        if (x1 == x2) {             // 가로로 놓여있음
            goRight(x2, y2, y2+1);
            goDiag(x2, y2, x2+1, y2+1);
        } else if (y1 == y2) {      // 세로로 놓여있음
            goDown(x2, y2, x2+1);
            goDiag(x2, y2, x2+1, y2+1);
        } else {                    // 대각선으로 놓여있음
            goRight(x2, y2, y2+1);
            goDown(x2, y2, x2+1);
            goDiag(x2, y2, x2+1, y2+1);
        }
    }

    // 우측으로 한칸 이동
    static void goRight(int x2, int y2, int ny) {
        if (x2 < n && ny < n && arr[x2][ny] != 1) {
            dfs(x2, y2, x2, ny);
        }
    }

    // 아래쪽으로 한칸 이동
    static void goDown(int x2, int y2, int nx) {
        if (nx < n && y2 < n && arr[nx][y2] != 1) {
            dfs(x2, y2, nx, y2);
        }
    }

    // 오른쪽 아래 대각선으로 이동
    static void goDiag(int x2, int y2, int nx, int ny) {
        if (nx < n && ny < n && arr[x2][ny] != 1 && arr[nx][ny] != 1 && arr[nx][y2] != 1) {
            dfs(x2, y2, nx, ny);
        }
    }
}
