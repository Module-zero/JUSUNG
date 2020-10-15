import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q1935 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder expTmp = new StringBuilder(br.readLine());
        List<String> exp = new ArrayList<>();

        StringBuilder answer = new StringBuilder();
        int[] mappingNums = new int[27];

        for (int i = 0; i < expTmp.length(); i++) {
            exp.add(String.valueOf(expTmp.charAt(i)));
        }

        for (int i = 0; i < n; i++) {
            mappingNums[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < exp.size(); i++) {
            char ch = exp.get(i).charAt(0);
            if (ch < 'A' || ch > 'Z') {
                continue;
            }
            int idx = ch - 'A';
            exp.set(i, String.valueOf(mappingNums[idx]));
        }

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < exp.size(); i++) {
            String oneExp = exp.get(i);
            String s1;
            String s2;
            double num1;
            double num2;
            switch (oneExp) {
                case "*" :
                    s1 = stack.pop();
                    s2 = stack.pop();
                    num1 = isInteger(s1) ? Integer.parseInt(s1) : Double.parseDouble(s1);
                    num2 = isInteger(s2) ? Integer.parseInt(s2) : Double.parseDouble(s2);
                    stack.push(String.valueOf(num2 * num1));
                    break;
                case "/" :
                    s1 = stack.pop();
                    s2 = stack.pop();
                    num1 = isInteger(s1) ? Integer.parseInt(s1) : Double.parseDouble(s1);
                    num2 = isInteger(s2) ? Integer.parseInt(s2) : Double.parseDouble(s2);
                    stack.push(String.valueOf(num2 / num1));
                    break;
                case "+" :
                    s1 = stack.pop();
                    s2 = stack.pop();
                    num1 = isInteger(s1) ? Integer.parseInt(s1) : Double.parseDouble(s1);
                    num2 = isInteger(s2) ? Integer.parseInt(s2) : Double.parseDouble(s2);
                    stack.push(String.valueOf(num2 + num1));
                    break;
                case "-" :
                    s1 = stack.pop();
                    s2 = stack.pop();
                    num1 = isInteger(s1) ? Integer.parseInt(s1) : Double.parseDouble(s1);
                    num2 = isInteger(s2) ? Integer.parseInt(s2) : Double.parseDouble(s2);
                    stack.push(String.valueOf(num2 - num1));
                    break;
                default:
                    stack.push(oneExp);
            }
        }

        System.out.println(String.format("%.2f", Double.parseDouble(stack.pop())));
    }

    public static boolean isInteger(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
