import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3015 {
    static int n;
    static int[] arr = new int[500001];
    static Stack<Integer> lStack = new Stack<>();
    static Stack<Integer> rStack = new Stack<>();
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            while (!lStack.isEmpty() && arr[lStack.peek()] < arr[i]) {
                lStack.pop();
                answer++;
            }
            lStack.push(i);
        }

        for (int i = n-1; i >= 0; i--) {
            while (!rStack.isEmpty() && arr[rStack.peek()] < arr[i]) {
                rStack.pop();
                answer++;
            }
            rStack.push(i);
        }

        /*
        for (int i = 0; i < n; i++) {
            System.out.print(left[i]+ " ");
        } System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(right[i]+ " ");
        } System.out.println();
         */

        System.out.print(answer);
    }
}
