import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12919 {
    static String s, t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        t = br.readLine();

        if (go(t)) {
            System.out.print(1);
        } else {
            System.out.print(0);
        }
    }

    static boolean go(String src) {

        if (src.length() == 0) {
            return false;
        }

        if (src.equals(s)) {
            return true;
        }

        StringBuilder copy = null;
        boolean res1 = false, res2 = false;

        if (src.charAt(src.length()-1) == 'A') {
            copy = new StringBuilder(src);
            copy.deleteCharAt(copy.length() - 1);
            res1 = go(copy.toString());
        }

        if (src.charAt(0) == 'B') {
            copy = new StringBuilder(src);
            copy.deleteCharAt(0);
            copy.reverse();
            res2 = go(copy.toString());
        }

        return res1 | res2;
    }
}
