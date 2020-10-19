import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// 대체적으로 일반적인 DP 문제들과 접근은 비슷했다
// 나는 해당 문제에서는 D 배열을 아래서부터 채워넣었다

// 개선사항 :
// 나는 D 배열을 위에서부터 채우는 것이 안될 것이라고 생각했는데
// 다른 코드를 보니까 위에서부터 채운 코드가 많았다
// 다시 생각해보니까 위에서부터도 아주 쉽게 채울 수 있다
public class Q1932 {

    static long[][] D;
    static long[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        D = new long[n+1][n+1];
        A = new long[n+1][n+1];

        StringTokenizer st = null;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= i; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            D[n][i] = A[n][i];
        }

        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                D[i][j] = ((D[i+1][j] > D[i+1][j+1]) ? D[i+1][j] : D[i+1][j+1]) + A[i][j];
            }
        }

        System.out.print(D[1][1]);
    }
}
