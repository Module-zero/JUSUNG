import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1201 {
    static int n, m, k;
    static int[] arr;
    static int[] group;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n < m+k-1 || n > m*k) {
            System.out.print(-1);
            return;
        }

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }

        // 그룹 나누기
        group = new int[m];
        group[0] = k;
        n -= k;
        m -= 1;
        int v = m != 0 ? n / m : 0;
        int r = m != 0 ? n % m : 0;
        for (int i = 1; i < group.length; i++) {
            group[i] = v;
            if (r > 0) {
                group[i] += 1;
                r--;
            }
        }

        int idx = 0;
        for (int i = 0; i < group.length; i++) {
            int start = idx;
            int end = idx + group[i] - 1;
            for (int j = end; j >= start; j--) {
                answer.append(arr[j] + " ");
            }
            idx += group[i];
        }

        System.out.print(answer);
    }
}
