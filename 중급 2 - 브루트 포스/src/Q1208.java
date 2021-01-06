import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q1208 {
    static int n, s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        int n1 = n/2, n2 = n-n1;
        int[] arr1 = new int[n1], arr2 = new int[n2];
        long[] sum1 = new long[1<<n1], sum2 = new long[1<<n2];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n1; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n2; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < (1<<n1); i++) {
            for (int j = 0; j < n1; j++) {
                if ((i & (1<<j)) != 0) {
                    sum1[i] += arr1[j];
                }
            }
        }

        for (int i = 0; i < (1<<n2); i++) {
            for (int j = 0; j < n2; j++) {
                if ((i & (1<<j)) != 0) {
                    sum2[i] += arr2[j];
                }
            }
        }

        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 1; i < (1<<n1); i++) {
            long num = sum1[i];
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                int cnt = map.get(num);
                map.put(num, cnt+1);
            }
        }

        long answer = 0;
        for (int i = 1; i < (1<<n1); i++) {
            if (sum1[i] == s) {
                answer++;
            }
        }
        for (int i = 1; i < (1<<n2); i++) {
            if (sum2[i] == s) {
                answer++;
            }
        }
        for (int i = 1; i < (1<<n2); i++) {
            if (map.containsKey(s-sum2[i])) {
                int cnt = map.get(s-sum2[i]);
                answer += cnt;
            }
        }

        System.out.print(answer);
    }
}
