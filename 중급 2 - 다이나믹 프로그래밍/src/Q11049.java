import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11049 {
    static int n;
    static int[][] a = new int[500][2];
    static int[][] d = new int[500][500];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n-len; i++) {
                d[i][i+len-1] = Integer.MAX_VALUE;
                for (int j = 1; j <= len-1; j++) {
                    int tmp = d[i][i+j-1] + d[i+j][i+len-1] + (a[i][0] * a[i+j-1][1] * a[i+len-1][1]);
                    d[i][i+len-1] = Math.min(d[i][i+len-1], tmp);
                }
            }
        }

        System.out.print(d[0][n-1]);
    }
}
