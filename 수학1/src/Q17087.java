import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17087 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int []arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[i] = Math.abs(S-a);
        }



        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
           max = gcd(arr[i], arr[i + 1]);
           arr[i+1] = max;
        }

        System.out.println(arr.length == 1 ? arr[0] : max);
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
