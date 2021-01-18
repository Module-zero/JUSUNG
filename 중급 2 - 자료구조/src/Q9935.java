import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q9935 {
    static Stack<Pair> stack = new Stack<>();
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        if (t.length() == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(0)) {
                    answer.append(s.charAt(i));
                }
            }
        } else {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (stack.isEmpty()) {
                    if (ch == t.charAt(0)) {
                        stack.add(new Pair(i, 0));
                    } else {
                        answer.append(ch);
                    }
                } else {
                    int nextIndex = stack.peek().t_index + 1;
                    if (ch == t.charAt(nextIndex)) {
                        if (nextIndex == t.length() - 1) {
                            for (int j = 0; j < t.length() - 1; j++) {
                                stack.pop();
                            }
                        } else {
                            stack.add(new Pair(i, nextIndex));
                        }
                    } else {
                        if (ch == t.charAt(0)) {
                            stack.add(new Pair(i, 0));
                        } else {
                            Stack<Character> tmp = new Stack<>();
                            tmp.add(ch);
                            while (!stack.isEmpty()) {
                                Pair p = stack.pop();
                                tmp.add(s.charAt(p.s_index));
                            }
                            while (!tmp.isEmpty()) {
                                answer.append(tmp.pop());
                            }
                        }
                    }
                }
            }
        }

        Stack<Character> tmp = new Stack<>();
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            tmp.add(s.charAt(p.s_index));
        }
        while (!tmp.isEmpty()) {
            answer.append(tmp.pop());
        }

        if (answer.toString().equals("")) {
            System.out.print("FRULA");
        } else {
            System.out.print(answer);
        }
    }

    static class Pair {
        int s_index, t_index;
        public Pair(int s_index, int t_index) {
            this.s_index = s_index;
            this.t_index = t_index;
        }
    }
}
