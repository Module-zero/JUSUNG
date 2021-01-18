import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q6549 {
    static int n;
    static int[] rec, arr1, arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            rec = new int[n];
            for (int i = 0; i < n; i++) {
                rec[i] = Integer.parseInt(st.nextToken());
            }
            arr1 = new int[n];
            arr2 = new int[n];

        }
    }
}
