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

        HashMap<Long, Integer> map1 = new HashMap<>();
        for (int i = 1; i < (1<<n1); i++) {
            long num = sum1[i];
            if (!map1.containsKey(num)) {
                map1.put(num, 1);
            } else {
                int cnt = map1.get(num);
                map1.put(num, cnt+1);
            }
        }

        HashMap<Long, Integer> map2 = new HashMap<>();
        for (int i = 1; i < (1<<n2); i++) {
            long num = sum2[i];
            if (!map2.containsKey(num)) {
                map2.put(num, 1);
            } else {
                int cnt = map2.get(num);
                map2.put(num, cnt+1);
            }
        }

        long answer = 0;
        for (int i = 1; i < (1<<n2); i++) {
            if (map1.containsKey(s-sum2[i])) {
                long cnt = map1.get(s-sum2[i]);
                answer += cnt;
            }
        }
        for (int i = 1; i < (1<<n1); i++) {
            if (map2.containsKey(s-sum1[i])) {
                long cnt = map2.get(s-sum1[i]);
                answer += cnt;
            }
        }

        System.out.print(answer);
    }
}
