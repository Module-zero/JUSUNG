import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12904 {
    static StringBuilder s, t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = new StringBuilder(br.readLine());
        t = new StringBuilder(br.readLine());

        while (true) {
            if (t.length() < s.length()) {
                System.out.print(0);
                break;
            }
            char last = t.charAt(t.length()-1);
            t.deleteCharAt(t.length()-1);
            if (last == 'B') {
                t.reverse();
            }

            if (t.toString().equals(s.toString())) {
                System.out.print(1);
                break;
            }
        }
    }
}
