import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q14238 {
    static String s;
    static int[] alpha = new int[3];
    static boolean[][][][][] d = new boolean[51][51][51][3][3];
    static char[] answer = new char[51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            alpha[s.charAt(i) - 'A']++;
        }

        if (go(0, 0, 0, 0, 0, 0)) {
            System.out.print(answer);
        } else {
            System.out.print(-1);
        }
    }

    static boolean go(int index, int a, int b, int c, int p1, int p2) {

        if (index == s.length()) {
            return (alpha[0] == a && alpha[1] == b && alpha[2] == c);
        }

        if (d[a][b][c][p1][p2]) {
            return false;
        }

        d[a][b][c][p1][p2] = true;

        answer[index] = 'A';
        if (go(index+1, a+1, b, c, 0, p1)) {
            return true;
        }

        if (p1 != 1) {
            answer[index] = 'B';
            if (go(index+1, a, b+1, c, 1, p1)) {
                return true;
            }
        }

        if (p1 != 2 && p2 != 2) {
            answer[index] = 'C';
            return go(index + 1, a, b, c + 1, 2, p1);
        }

        return false;
    }
}
