import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1074 {
    static int n, r, c;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        go(0, 0, (int)Math.pow(2, n));
        System.out.print(count);
    }

    static boolean go(int x, int y, int len) {
        if (r >= x && r <= x+len-1 && c >= y && c <= y+len-1) {
            if (len == 1) {
                return true;
            }
            if (go(x, y, len/2)) { return true; }
            if (go(x, y+len/2, len/2)) { return true; }
            if (go(x+len/2, y, len/2)) { return true; }
            return go(x + len / 2, y + len / 2, len / 2);
        } else {
            count += len * len;
            return false;
        }
    }
}
