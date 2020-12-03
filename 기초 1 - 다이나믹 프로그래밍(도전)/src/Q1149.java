import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// 일반적인 DP 문제와 동일

// 개선사항 :
// 최소값, 최대값 구할 때 Math 클래스 활용
public class Q1149 {

    static long[][] D;
    static long[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        D = new long[n+1][3];
        A = new long[n+1][3];

        StringTokenizer st = null;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= 2; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= 2; i++) {
            D[n][i] = A[n][i];
        }

        for (int i = n-1; i >= 1; i--) {
            D[i][0] = ((D[i+1][1] < D[i+1][2]) ? D[i+1][1] : D[i+1][2]) + A[i][0];
            D[i][1] = ((D[i+1][0] < D[i+1][2]) ? D[i+1][0] : D[i+1][2]) + A[i][1];
            D[i][2] = ((D[i+1][0] < D[i+1][1]) ? D[i+1][0] : D[i+1][1]) + A[i][2];
        }

        // long answer = ((answer = (D[1][0] < D[1][1]) ? D[1][0] : D[1][1]) < D[1][2]) ? answer : D[1][2];
        long answer = Math.min(D[1][2], Math.min(D[1][0], D[1][1]));
        System.out.print(answer);
    }
}
