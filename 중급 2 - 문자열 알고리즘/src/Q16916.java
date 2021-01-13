import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16916 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();
        if (kmp(s, p)) {
            System.out.print(1);
        } else {
            System.out.print(0);
        }
    }

    static boolean kmp(String s, String p) {
        int[] pi = getPi(p);
        int n = s.length(), m = p.length(), j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = pi[j-1];
            }

            if (s.charAt(i) == p.charAt(j)) {
                if (j == m-1) {
                    return true;
                } else {
                    j++;
                }
            }
        }
        return false;
    }

    static int[] getPi(String p) {
        int len = p.length(), j = 0;
        int[] pi = new int[len];

        for (int i = 1; i < len; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j-1];
            }
            if (p.charAt(i) == p.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
