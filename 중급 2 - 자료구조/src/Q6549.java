import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q6549 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            int[] h = new int[100001];
            int[] left = new int[100001];
            int[] right = new int[100001];
            Stack<Integer> lStack = new Stack<>();
            Stack<Integer> rStack = new Stack<>();

            for (int i = 0; i < n; i++) {
                h[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                left[i] = i;
                while (!lStack.isEmpty() && h[lStack.peek()] >= h[i]) {
                    left[i] = left[lStack.pop()];
                }
                lStack.push(i);
            }

            for (int i = n-1; i >= 0; i--) {
                right[i] = i;
                while (!rStack.isEmpty() && h[rStack.peek()] >= h[i]) {
                    right[i] = right[rStack.pop()];
                }
                rStack.push(i);
            }

            long max = Long.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int width = right[i] - left[i] + 1;
                max = Math.max(max, (long)width * (long)h[i]);
            }

            System.out.println(max);
        }
    }
}
