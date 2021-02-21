import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1890 {
    static int n;
    static int[][] a = new int[100][100];
    static long[][] d = new long[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        d[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                for (int nx = i - 1, k = 1; nx >= 0; nx--, k++) {
                    if (a[nx][j] == k && d[nx][j] != 0) {
                        d[i][j] += d[nx][j];
                    }
                }
                for (int ny = j - 1, k = 1; ny >= 0; ny--, k++) {
                    if (a[i][ny] == k && d[i][ny] != 0) {
                        d[i][j] += d[i][ny];
                    }
                }
            }
        }

        System.out.print(d[n-1][n-1]);
    }
}
