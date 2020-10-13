import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// 스택
public class Q10828 {

    static int[] stack = new int[10000];
    static int top = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        for (int i = 0; i < count; i++) {

            String str = br.readLine();
            if (str.contains("push")) {
                StringTokenizer token = new StringTokenizer(str, " ");
                token.nextToken();
                int num = Integer.parseInt(token.nextToken());
                push(num);
            }
            if (str.contains("pop")) {
                System.out.println(pop());
            }
            if (str.contains("size")) {
                System.out.println(size());
            }
            if (str.contains("empty")) {
                System.out.println(empty());
            }
            if (str.contains("top")) {
                System.out.println(top());
            }
        }
    }

    public static void push(int n) {
        stack[top++] = n;
    }

    public static int pop() {
        if (top == 0) {
            return -1;
        }
        else {
            return stack[--top];
        }
    }

    public static int size() {
        return top;
    }

    public static int empty() {
        if (top == 0) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public static int top() {
        if (top == 0) {
            return -1;
        }
        else {
            return stack[top - 1];
        }
    }
}
