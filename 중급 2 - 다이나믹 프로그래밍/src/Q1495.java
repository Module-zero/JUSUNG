import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1495 {
    static int n, s, m;
    static int[] v = new int[110];
    static boolean[][] d = new boolean[110][1010];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        d[0][s] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j >= v[i] && d[i-1][j-v[i]]) {
                    d[i][j] = true;
                }
                if (j+v[i] <= m && d[i-1][j+v[i]]) {
                    d[i][j] = true;
                }
            }
        }

        int answer = -1;
        for (int i = m; i >= 0; i--) {
            if (d[n][i]) {
                answer = i;
                break;
            }
        }

        System.out.print(answer);
    }
}
