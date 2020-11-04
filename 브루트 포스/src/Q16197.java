import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q16197 {

    static int N;
    static int M;
    static char[][] board;
    static ArrayList<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int x1 = -1;
        int y1 = -1;
        int x2 = -1;
        int y2 = -1;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'o') {
                    if (x1 == -1) {
                        x1 = i;
                        y1 = j;
                    }
                    else {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
        }

        go(x1, y1, x2, y2, 0, 0, 0);

        for (Integer i : answers) {
            System.out.print(i + " ");
        }
    }

    static void go(int x1, int y1, int x2, int y2, int backX, int backY, int count) {

        if (!check(x1, y1) && !check(x2, y2)) {

            System.out.print(x1 + "," + y1 + " / ");
            System.out.println(x2 + "," + y2);

            if (board[x1][y1] == '#' && board[x2][y2] == '#') {
                System.out.println("blocked");
                return;
            }

            if (board[x1][y1] == '#') {
                x1 += backX;
                y1 += backY;
            }

            if (board[x2][y2] == '#') {
                x2 += backX;
                y2 += backY;
            }
        }

        if (check(x1, y1) && check(x2, y2)) {
            System.out.println("둘 다 떨어짐");
            return;
        }

        if (check(x1, y1) && !check(x2, y2)) {
            answers.add(count);
            return;
        }

        if (!check(x1, y1) && check(x2, y2)) {
            answers.add(count);
            return;
        }

        // 왔던 곳으로 되돌아가지 않도록 함
        if (backX != -1) {
            go(x1 - 1, y1, x2 - 1, y2, 1, 0, count + 1);
        }

        if (backX != 1) {
            go(x1 + 1, y1, x2 + 1, y2, -1, 0, count + 1);
        }

        System.out.println("check");

        if (backY != -1) {
            go(x1, y1 - 1, x2, y2 - 1, 0, 1, count + 1);
        }

        if (backY != 1) {
            go(x1, y1 + 1, x2, y2 + 1, 0, -1, count + 1);
        }

        return;
    }

    static boolean check(int x, int y) {
        if (x < 0 || x >= N) {
            return true;
        }
        if (y < 0 || y >= M) {
            return true;
        }
        return false;
    }
}
