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

            arr = new int[500][1000];
            dp = new int[500][1000];
            max = Integer.MIN_VALUE;

            for (int i = 0; i < 500; i++) {
                for (int j = 0; j < 1000; j++) {
                    arr[i][j] = 9999;
                }
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= 2*i-1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = dp[i][j-1] + arr[i][j];
                }
            }

            // k : 삼각형의 길이
            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n-k+1; i++) {
                    for (int j = 1; j <= 2*i -1; j+=2) {
                        // 정삼각형의 형태만 더한다.
                        plus(i, j, k);
                    }
                }
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 2; i <= n; i++) {
                    for (int j = 2; j <= 2*(i-1); j+=2) {
                        // 역삼각형의 형태만 더한다.
                        plusReverse(i, j, k);
                    }
                }
            }

            sb.append(cnt++).append(". ").append(max).append("\n");
        }

        System.out.print(sb);
    }

    static void plus(int x, int y, int k) {
        int sum = 0;
        for (int i = 0; i <= k-1; i++) {
            int end = 2*i+y;
            sum += dp[i+x][end] - dp[i+x][y-1];
        }
        max = Math.max(max, sum);
    }

    static void plusReverse(int x, int y, int k) {
        int sum = 0;
        for (int i = 0; i >= 1-k; i--) {
            int start = 2*i+y;
            if (i+x < 0) {
               return;
            }
            if (start-1 < 0) {
                return;
            }
            if (arr[i+x][y] == 9999) {
                return;
            }
            sum += dp[i+x][y] - dp[i+x][start-1];
        }
        max = Math.max(max, sum);
    }

    static void printArr(int[][] arr) {
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < 2*n; j++) {
                System.out.print(arr[i][j]+" ");
            } System.out.println();
        } System.out.println();
    }
}
