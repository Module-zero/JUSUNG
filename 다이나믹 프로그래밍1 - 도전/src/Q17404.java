import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17404 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] A = new int[n+1][3];
        int[][] D = new int[n+1][3];
        int answer = Integer.MAX_VALUE;

        StringTokenizer st = null;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= 2; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= 2; i++) {
            D[2][i] = Integer.MAX_VALUE;
            D[2][(i+1)%3] = A[1][i] + A[2][(i+1)%3];
            D[2][(i+2)%3] = A[1][i] + A[2][(i+2)%3];

            for (int j = 3; j <= n; j++) {
                D[j][0] = Integer.min(D[j-1][1], D[j-1][2]) + A[j][0];
                D[j][1] = Integer.min(D[j-1][0], D[j-1][2]) + A[j][1];
                D[j][2] = Integer.min(D[j-1][0], D[j-1][1]) + A[j][2];
            }

            int candidate = Integer.min(D[n][(i+1)%3], D[n][(i+2)%3]);
            if (answer > candidate) {
                answer = candidate;
            }
        }

        System.out.print(answer);
    }
}
