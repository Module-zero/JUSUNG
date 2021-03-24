import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2616 {
    static int n, m;
    static long[] sum = new long[50001];
    static long[][] d = new long[4][50001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 3; i++) {
            for (int j = m; j <= n; j++) {
                d[i][j] = Math.max(d[i - 1][j - m] + sum[j] - sum[j - m], d[i][j - 1]);
            }
        }

        System.out.print(d[3][n]);
    }
}
