import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1806 {
    static int n, s;
    static int[] dp = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1] + Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = (left+right)/2;
            if (possible(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.print(answer);
    }

    // 길이가 x 인 연속된 수들의 부분합 중 , 그 합이 s 이상인 것이 있는가?
    static boolean possible(int x) {
        for (int i = 0; i <= n-x; i++) {
            int sum = dp[i+x] - dp[i];
            if (sum >= s) {
                return true;
            }
        }
        return false;
    }
}
