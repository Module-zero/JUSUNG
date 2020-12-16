import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1891 {
    static int d;
    static int[] a;
    static long r, c;
    static long startX = 0, startY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        d = Integer.parseInt(st.nextToken());
        a = new int[d];
        String s = st.nextToken();
        for (int i = 0; i < d; i++) {
            a[i] = s.charAt(i) - '0';
        }

        st = new StringTokenizer(br.readLine(), " ");
        r = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        long size = (long)Math.pow(2, d);
        go(0, size, 0, 0);
        startX -= c;
        startY += r;

        if (startX >= 0 && startX < size && startY >= 0 && startY < size) {
            System.out.print(foo(size, 0, 0));
        } else {
            System.out.print(-1);
        }
   }

   static String foo(long size, long sx, long sy) {
        if (size == 1) {
            return "";
        }
        if (startX < sx+size/2 && startY >= sy+size/2) {
            return "1" + foo(size/2, sx, sy+size/2);
        } else if (startX < sx+size/2 && startY < sy+size/2) {
            return "2" + foo(size/2, sx, sy);
        } else if (startX >= sx+size/2 && startY < sy+size/2) {
            return "3" + foo(size/2, sx+size/2, sy);
        } else {
            return "4" + foo(size/2, sx+size/2, sy+size/2);
        }
   }

    static void go(int index, long size, long x, long y) {
        if (index == d) {
            startX = x;
            startY = y;
            return;
        }
        switch (a[index]) {
            case 1 : go(index+1, size/2, x, y+size/2); break;
            case 2 : go(index+1, size/2, x, y); break;
            case 3 : go(index+1, size/2, x+size/2, y); break;
            case 4 : go(index+1, size/2, x+size/2, y+size/2); break;
            default: break;
        }
    }
}
