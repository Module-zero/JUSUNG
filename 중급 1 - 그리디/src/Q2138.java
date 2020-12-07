import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2138 {
    static int n;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        boolean[] state = new boolean[n];

        String s1 = br.readLine();
        String s2 = br.readLine();
        for (int i = 0; i < n; i++) {
            state[i] = s1.charAt(i) != s2.charAt(i);
        }

        go(state, 0);
        change(state, 0);
        go(state, 1);

        if (answer == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(answer);
        }
    }

    static void go(boolean[] state, int count) {
        boolean[] copy = state.clone();
        for (int i = 0; i < n-1; i++) {
            if (copy[i] == true) {
                change(copy, i+1);
                count++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (copy[i] == true) {
                return;
            }
        }

        if (answer > count) {
            answer = count;
        }
    }

    static void change(boolean[] state, int i) {
        state[i] = !state[i];
        if (i > 0) { state[i-1] = !state[i-1]; }
        if (i < n-1) { state[i+1] = !state[i+1]; }
    }
}
