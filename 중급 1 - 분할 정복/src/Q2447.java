import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2447 {
    static int n;
    static char[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = ' ';
            }
        }

        printStar(n, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(answer[i][j]);
            } sb.append("\n");
        }
        System.out.print(sb);
    }

    static void printStar(int size, int startX, int startY) {
        if (size == 1) {
            answer[startX][startY] = '*';
            return;
        }
        int x = 0, y = 0;
        for (int i = 0; i < 3; i++) {
            x = i * size/3;
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) { continue; }
                y = j * size/3;
                printStar(size/3, startX+x, startY+y);
            }
        }
    }
}
