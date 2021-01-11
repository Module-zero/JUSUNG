import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16971 {
    static int n, m;
    static int[][] arr = new int[1000][1000];
    static long[] dp_col = new long[1000];
    static long[] dp_row = new long[1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long val = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < m-1; j++) {
                val += arr[i][j] + arr[i+1][j] + arr[i+1][j+1] + arr[i][j+1];
            }
        }

        long max1 = val;
        if (m > 2) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n - 1; j++) {
                    dp_col[i] += arr[j][i] + arr[j + 1][i];
                }
            }
            long min = Long.MAX_VALUE;
            for (int i = 1; i < m - 1; i++) {
                min = Math.min(min, dp_col[i]);
            }
            max1 = val + Math.max(dp_col[0], dp_col[m - 1]) - min;
        }

        long max2 = val;
        if (n > 2) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m - 1; j++) {
                    dp_row[i] += arr[i][j] + arr[i][j + 1];
                }
            }
            long min = Long.MAX_VALUE;
            for (int i = 1; i < n - 1; i++) {
                min = Math.min(min, dp_row[i]);
            }
            max2 = val + Math.max(dp_row[0], dp_row[n - 1]) - min;
        }

        System.out.print(Math.max(Math.max(max1, max2), val));
    }
}
