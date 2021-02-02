import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12865 {
    static int n, k;
    static int[] w = new int[110];
    static int[] v = new int[110];
    static int[][] d = new int[110][100010];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
        }
    }
}
