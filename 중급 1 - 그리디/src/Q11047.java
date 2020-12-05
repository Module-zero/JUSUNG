import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11047 {
    static int n, k;
    static int[] arr = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        while (k != 0) {
            int target = 0;
            for (int i = 0; arr[i] <= k; i++) {
                target = i;
                if (i == n-1) {
                    break;
                }
            }
            count += k / arr[target];
            k %= arr[target];
        }
        System.out.print(count);
    }
}
