import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2003 {
    static int n, m;
    static int[] arr = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (check(i)) {
               cnt++;
            }
        }
        System.out.print(cnt);
    }

    static boolean check(int idx) {
        int sum = 0;
        for (int i = idx; i < n; i++) {
            sum += arr[i];
            if (sum == m) {
                return true;
            }
            if (sum > m) {
                return false;
            }
        }
        return false;
    }
}
