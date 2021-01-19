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

            Stack<Pair> stack = new Stack<>();
            int[] rec = new int[n];
            int[] left = new int[n];
            int[] right = new int[n];

            for (int i = 0; i < n; i++) {
                rec[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                boolean flag = false;
                while (!stack.isEmpty()) {
                    Pair top = stack.peek();
                    if (top.h >= rec[i]) {
                        Pair tmp = stack.pop();
                        left[i] = left[tmp.x];
                        flag = true;
                    } else {
                        break;
                    }
                }

                if (!flag) {
                    left[i] = i;
                }
                stack.push(new Pair(i, rec[i]));
            }

            stack.clear();

            for (int i = n-1; i >= 0; i--) {
                boolean flag = false;
                while (!stack.isEmpty()) {
                    Pair top = stack.peek();
                    if (top.h >= rec[i]) {
                        Pair tmp = stack.pop();
                        right[i] = right[tmp.x];
                        flag = true;
                    } else {
                        break;
                    }
                }

                if (!flag) {
                    right[i] = i;
                }
                stack.push(new Pair(i, rec[i]));
            }

            long max = Long.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int x = right[i] - left[i] + 1;
                max = Math.max(max, (long)x * (long)rec[i]);
            }

            System.out.println(max);
        }
    }

    static class Pair {
        private final int x, h;
        public Pair(int x, int h) {
            this.x = x;
            this.h = h;
        }
    }
}
