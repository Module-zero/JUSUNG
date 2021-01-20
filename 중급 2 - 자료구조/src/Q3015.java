import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3015 {
    static int n;
    static int[] arr = new int[500001];
    static Stack<int[]> lStack = new Stack<>();
    static Stack<Integer> rStack = new Stack<>();
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            int[] pair = {arr[i], 1};
            while (!lStack.isEmpty() && lStack.peek()[0] <= arr[i]) {
                int cnt = lStack.peek()[1];
                if (lStack.peek()[0] == arr[i]) {
                    pair[1] = cnt + 1;
                }
                answer += cnt;
                lStack.pop();
            }

            lStack.push(pair);
        }

        for (int i = n-1; i >= 0; i--) {
            while (!rStack.isEmpty() && arr[rStack.peek()] < arr[i]) {
                rStack.pop();
                answer++;
            }
            rStack.push(i);
        }

        System.out.print(answer);
    }
}

