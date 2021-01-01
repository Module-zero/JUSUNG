import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17069 {
    static int n;
    static long[][][] dp = new long[32][32][3];
    static int[][] arr = new int[32][32];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(dfs(0, 0, 0, 1));
    }

    static long dfs(int x1, int y1, int x2, int y2) {

        if (x2 == n-1 && y2 == n-1) {
            return 1;
        }

        int dir = (x1 == x2) ? 0 : ((y1 == y2) ? 1 : 2);
        long ans = dp[x1][y1][dir];
        if (ans != 0) {
            return ans;
        }

        if (x1 == x2) {             // 가로로 놓여있음
            ans += goRight(x2, y2, y2+1);
            ans += goDiag(x2, y2, x2+1, y2+1);
        } else if (y1 == y2) {      // 세로로 놓여있음
            ans += goDown(x2, y2, x2+1);
            ans += goDiag(x2, y2, x2+1, y2+1);
        } else {                    // 대각선으로 놓여있음
            ans += goRight(x2, y2, y2+1);
            ans += goDown(x2, y2, x2+1);
            ans += goDiag(x2, y2, x2+1, y2+1);
        }

        dp[x1][y1][dir] = ans;
        return ans;
    }

    // 우측으로 한칸 이동
    static long goRight(int x2, int y2, int ny) {
        if (x2 < n && ny < n && arr[x2][ny] != 1) {
            return dfs(x2, y2, x2, ny);
        }
        return 0;
    }

    // 아래쪽으로 한칸 이동
    static long goDown(int x2, int y2, int nx) {
        if (nx < n && y2 < n && arr[nx][y2] != 1) {
            return dfs(x2, y2, nx, y2);
        }
        return 0;
    }

    // 오른쪽 아래 대각선으로 이동
    static long goDiag(int x2, int y2, int nx, int ny) {
        if (nx < n && ny < n && arr[x2][ny] != 1 && arr[nx][ny] != 1 && arr[nx][y2] != 1) {
            return dfs(x2, y2, nx, ny);
        }
        return 0;
    }
}
