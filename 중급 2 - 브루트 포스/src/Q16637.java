import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16637 {
    static int n;
    static String exp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        exp = br.readLine();

        go(0, 0, 0, '+');
    }

    // n = 9
    // 3+8*7-9*2

    static int max = Integer.MIN_VALUE;

    static void go(int index, int sum, int start, char op) {
        if (index == n) {
        }

        char opp = exp.charAt(start+1);
        int left = Integer.parseInt(String.valueOf(exp.charAt(start)));
        int right = Integer.parseInt(String.valueOf(exp.charAt(start+2)));
        int res = cal(opp, left, right);

        // 괄호를 추가하는 경우
        go(index+1, cal(op, res, sum), start+4, exp.charAt(start+3));

        // 괄호를 추가하지 않는 경우
        go(index+1, left, start+2, exp.charAt(start+1));
    }

    static int cal(char op, int left, int right) {
        return 0;
    }
}
