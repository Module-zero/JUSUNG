import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9613 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cCount = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        for (int i = 0; i < cCount; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int count = Integer.parseInt(st.nextToken());
            int [] arr = new int[count];
            long sum = 0;

            for (int j = 0; j < count; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < count; j++) {
                for (int k = j; k < count - 1; k++) {
                    sum += gcd(arr[j], arr[k+1]);
                }
            }

            System.out.println(sum);
        }
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }
}
