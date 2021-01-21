import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3111 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String T = br.readLine();
        int n = T.length();
        int m = A.length();
        Stack<Character> lStack = new Stack<>();
        Stack<Character> rStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int left = 0, right = n-1;
        while (left <= right) {

            for (int i = left; i <= right; i++, left++) {
                lStack.push(T.charAt(i));
                if (lStack.size() >= m && lStack.peek() == A.charAt(m - 1)) {
                    boolean flag = true;
                    for (int j = 0; j < m; j++) {
                        if (lStack.get(lStack.size() - j - 1) != A.charAt(m - j - 1)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        for (int j = 0; j < m; j++) {
                            lStack.pop();
                        }
                        break;
                    }
                }
            }

            for (int i = right; i >= left; i--, right--) {
                rStack.push(T.charAt(i));
                if (rStack.size() >= m && rStack.peek() == A.charAt(0)) {
                    boolean flag = true;
                    for (int j = 0; j < m; j++) {
                        if (rStack.get(rStack.size() - j - 1) != A.charAt(j)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        for (int j = 0; j < m; j++) {
                            rStack.pop();
                        }
                        break;
                    }
                }
            }
        }

        while (!lStack.isEmpty()) {
            rStack.push(lStack.pop());
        }

        while (!rStack.isEmpty()) {
            sb.append(rStack.pop());
        }

        while (true) {
            int idx = sb.indexOf(A);
            if (idx == -1) {
                break;
            }
            sb.delete(idx, idx+m);
        }

        System.out.print(sb);
    }
}
