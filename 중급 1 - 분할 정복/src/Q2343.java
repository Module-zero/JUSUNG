import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2343 {
    static int n, m;
    static int[] lessons = new int[100000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            sum += lessons[i];
            max = Math.max(max, lessons[i]);
        }

        int answer = Integer.MAX_VALUE;
        int left = max;
        int right = sum;
        while (left <= right) {
            int mid = (left+right)/2;
            if (possible(mid)) {
                answer = Math.min(answer, mid);
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        System.out.print(answer);
    }

    static boolean possible(int s) {
        int cnt = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + lessons[i] > s) {
                cnt++;
                sum = lessons[i];
            } else {
                sum += lessons[i];
            }
        }
        if (sum <= s) {
            cnt++;
        }
        return cnt <= m;
    }
}
