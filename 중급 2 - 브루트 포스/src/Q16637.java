import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16637 {
    static int n;
    static String exp;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        exp = br.readLine();

        go(0, 0);
        System.out.print(max);
    }

    static void go(int index, int sum) {

        if (index >= n) {
            max = Math.max(max, sum);
            return;
        }

        int cur_val = exp.charAt(index)-'0';
        char cur_op = index == 0 ? '+' : exp.charAt(index-1);
        // 괄호를 추가하지 않는 경우
        go(index+2, cal(cur_op, sum, cur_val));

        if (index <= n-2) {
            int next_val = exp.charAt(index + 2) - '0';
            char next_op = exp.charAt(index + 1);
            // 괄호를 추가하는 경우
            go(index + 4, cal(cur_op, sum, cal(next_op, cur_val, next_val)));
        }
    }

    static int cal(char op, int left, int right) {
        switch (op) {
            case '+' :
                return (left+right);
            case '-' :
                return (left-right);
            case '*' :
                return (left*right);
        }
        return -1;
    }
}
