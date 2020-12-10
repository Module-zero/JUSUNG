import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1541 {
    static ArrayList<String> list = new ArrayList<>();
    static Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "+|-", true);
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (str.equals("+") || str.equals("-")) {
                if (str.equals("-")) {
                    while (!stack.isEmpty()) {
                        list.add(stack.pop());
                    }
                }
                stack.push(str);
            } else {
                list.add(str);
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        for (int i = 0; i < list.size(); i++) {
            int n1, n2, n;
            String str = list.get(i);
            if (str.equals("+") || str.equals("-")) {
                n2 = Integer.parseInt(stack.pop());
                n1 = Integer.parseInt(stack.pop());
                n = str.equals("+") ? n1+n2 : n1-n2;
                stack.push(n+"");
            } else {
                stack.push(str);
            }
        }

        System.out.print(stack.pop());
    }
}
