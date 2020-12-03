import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2580 {

    // 빈칸의 좌표를 저장해두는 리스트
    static ArrayList<Point> blank = new ArrayList<>();
    static int[][] sudoku = new int[9][9];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if (sudoku[i][j] == 0) {
                    blank.add(new Point(i, j));
                }
            }
        }

        go(0);
    }

    static boolean go(int index) {

        // 모든 빈 칸을 채우면 스도쿠 출력 뒤 종료
        if (index == blank.size()) {
            printSudoku();
            return true;
        }

        // index 에 해당 하는 빈 칸 좌표를 추출한 뒤
        // 빈 칸 좌표에 들어갈 수 있는 모든 수를 possibleNums 에 저장
        int x = blank.get(index).getX();
        int y = blank.get(index).getY();
        int[] possibleNums = getPossibleNums(x, y);

        // getPossibleNums() 가 null 을 리턴할 경우
        // 해당 좌표에 들어갈 숫자가 없다는 뜻이므로 false 를 리턴
        if (possibleNums == null) {
            return false;
        }

        // 가능한 경우들을 모두 탐색
        for (int i = 0; i < possibleNums.length; i++) {
            sudoku[x][y] = possibleNums[i];
            if (go(index+1)) {
                return true;
            }
            sudoku[x][y] = 0;
        }
        return false;
    }

    static int[] getPossibleNums(int x, int y) {

        // (x, y) 의 가로, 세로줄에 있는 수들을 체크
        boolean[] check = new boolean[11];
        for (int i = 0; i < 9; i++) {
            check[sudoku[x][i]] = true;
            check[sudoku[i][y]] = true;
        }

        // 3 * 3 공간에 있는 수들을 체크
        Point p = getStartPoint(x, y);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                check[sudoku[i+p.getX()][j+p.getY()]] = true;
            }
        }

        int nPossible = 0;
        for (int i = 1; i <= 9; i++) {
            if (check[i] == false) { nPossible++; }
        }

        int[] res = null;
        if (nPossible != 0) {
            res = new int[nPossible];
            for (int i = 1, j = 0; i <= 9; i++) {
                if (check[i] == false) {
                    res[j++] = i;
                }
            }
        }

        return res;
    }

    static Point getStartPoint(int x, int y) {

        int startX = 0;
        int startY = 0;

        if (x >= 3 && x <= 5) { startX = 3; }
        else if (x >= 6 && x <= 8) { startX = 6; }

        if (y >= 3 && y <= 5) { startY = 3; }
        else if (y >= 6 && y <= 8) { startY = 6; }

        return new Point(startX, startY);
    }

    static class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }
        public int getY() { return y; }
    }

    static void printSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            } System.out.println();
        }
    }
}
