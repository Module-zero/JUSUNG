import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4902 {
    static int n;
    static int[][] arr;
    static int[][] dp;
    static int max;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            arr = new int[401][801];
            dp = new int[401][801];
            max = Integer.MIN_VALUE;

            for (int i = 0; i < 401; i++) {
                for (int j = 0; j < 801; j++) {
                    arr[i][j] = 9999;
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= 2*i-1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    // dp[i][j] : arr[i][0] + ... + arr[i][j];
                    // 삼각형의 값 계산을 빠르게 하기위해 dp 를 활용
                    dp[i][j] = dp[i][j-1] + arr[i][j];
                }
            }

            // 삼각형 하나하나를 좌표로 생각한다.
            for (int i = 1; i <= n; i++) {
               for (int j = 1; j <= 2*i-1; j++) {
                   // 삼각형 (i, j) 를 가장 위 꼭지점으로 하는 모든 삼각형들의 각각의 값을 재귀로 구한다.
                   // 이 때, j 가 홀수이면 정삼각형을, 짝수이면 역삼각형을 만들 수 있다.
                   go(i, j, j, 0);
               }
            }
            sb.append(cnt++).append(". ").append(max).append("\n");
        }
        System.out.print(sb);
    }

    static void go(int col, int left, int right, int sum) {

        if (col > n || col < 1) {
            return;
        }

        if (left < 1 || arr[col][right] == 9999) {
            return;
        }

        sum += dp[col][right] - dp[col][left-1];
        max = Math.max(max, sum);

        if (left % 2 != 0) {
            // 정삼각형
            go(col+1, left, right+2, sum);
        } else {
            /// 역삼각형
            go(col-1, left-2, right, sum);
        }
    }
}
