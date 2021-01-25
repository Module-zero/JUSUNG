import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q15989 {
    static int t;
    static int[][] d = new int[10001][4];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        d[1][1] = d[2][1] = d[2][2] = d[3][3]  = 1;
        d[3][1] = 2;

        for (int i = 4; i <= 10000; i++) {
            for (int j = 1; j <= 3 ; j++) {
                for (int k = j; k <= 3; k++) {
                    d[i][j] += d[i - j][k];
                }
            }
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int x = d[n][1] + d[n][2] + d[n][3];
            sb.append(x).append("\n");
        }

        System.out.print(sb);
    }
}
