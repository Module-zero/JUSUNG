import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16938 {
    static int n, l, r, x;
    static int[] arr = new int[15];
    static int[] order;
    static boolean[] check;
    static int cnt = 0, len = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 문제를 i 개 고르는 경우의 수
        for (int i = 2; i <= n; i++) {
            len = i;
            order = new int[len];
            check = new boolean[n];
            go(0, 0);
        }

        System.out.print(cnt);
    }

    static void go(int index, int start) {
        if (index == len) {
            int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int idx : order) {
                max = Math.max(max, arr[idx]);
                min = Math.min(min, arr[idx]);
                sum += arr[idx];
            }
            if (sum >= l && sum <= r && (max-min) >= x) {
                cnt++;
            }
            return;
        }

        for (int i = start; i < n; i++) {
            if (check[i]) { continue; }
            order[index] = i;
            check[i] = true;
            go(index+1, i+1);
            check[i] = false;
        }
    }
}
