import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2448 {
    static int n, k;
    static char[][] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = (int)(Math.log((double)n/3)/Math.log(2));
        a = new char[n][2*n-1];
        for (char[] value : a) {
            Arrays.fill(value, ' ');
        }

        // n : 삼각형의 높이
        // x, y : 삼각형의 가장 꼭대기점의 좌표
        printStar(n, 0, n-1);

        StringBuilder sb = new StringBuilder();
        for (char[] chars : a) {
            for (char aChar : chars) {
                sb.append(aChar);
            } sb.append("\n");
        }
        System.out.print(sb);
    }

    static void printStar(int h, int x, int y) {
        if (h == 3) {
            a[x][y] = '*';
            a[x+1][y-1] = a[x+1][y+1] = '*';
            a[x+2][y-2] = a[x+2][y-1] = a[x+2][y] = a[x+2][y+1] = a[x+2][y+2] = '*';
            return;
        }

        // 삼각형의 밑변의 길이
        printStar(h/2, x, y);
        printStar(h/2, x+h/2, y-h/2);
        printStar(h/2, x+h/2, y+h/2);
    }
}
