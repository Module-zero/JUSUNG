import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q2143 {
    static int t, n, m;
    static int[] dp1 = new int[1001];
    static int[] dp2 = new int[1001];
    static long[] sum1 = new long[1000000];
    static long[] sum2 = new long[1000000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        t = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            dp1[i] = dp1[i-1] + Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= m; i++) {
            dp2[i] = dp2[i-1] + Integer.parseInt(st.nextToken());
        }

        // i : 부분배열의 길이
        int cnt1 = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j+i <= n; j++) {
                sum1[cnt1++] = dp1[j+i] - dp1[j];
            }
        }

        int cnt2 = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j+i <= m; j++) {
                sum2[cnt2++] = dp2[j+i] - dp2[j];
            }
        }

        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < cnt2; i++) {
           if (!map.containsKey(sum2[i])) {
               map.put(sum2[i], 1);
           } else {
               int cnt = map.get(sum2[i]);
               map.put(sum2[i], cnt+1);
           }
        }

        long answer = 0;
        for (int i = 0; i < cnt1; i++) {
            long num = t - sum1[i];
            if (map.containsKey(num)) {
                answer += map.get(num);
            }
        }

        System.out.print(answer);
    }
}
