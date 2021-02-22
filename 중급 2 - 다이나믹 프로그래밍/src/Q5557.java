import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5557 {
    static int n;
    static int[] a = new int[110];
    static long[][] d = new long[110][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        d[0][a[0]] = 1;
        for (int i = 1; i <= n-2; i++) {
            for (int j = 0; j <= 20; j++) {
                if (j-a[i] >= 0) {
                    d[i][j] += d[i - 1][j - a[i]];
                }
                if (j+a[i] <= 20) {
                    d[i][j] += d[i - 1][j + a[i]];
                }
            }
        }

        System.out.print(d[n-2][a[n-1]]);
    }
}
