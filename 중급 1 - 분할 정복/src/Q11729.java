import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11729 {
    static int n;
    static int count = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        go(n, 1, 3);
        System.out.println(count);
        System.out.print(sb);
    }

    static void go(int n, int x, int y) {
        if (n == 0) { return; }
        go(n-1, x, 6-x-y);
        String s = x+" "+y+"\n";
        sb.append(s);
        count++;
        go(n-1, 6-x-y, y);
    }
}
