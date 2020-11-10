import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4574 {

    static int[][] sudoku;
    static boolean[][] domino;
    static boolean[][] c1;
    static boolean[][] c2;
    static boolean[][] c3;
    static int M;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;
        while (true) {
            sudoku = new int[9][9];
            domino = new boolean[10][10];
            c1 = new boolean[9][10];
            c2 = new boolean[9][10];
            c3 = new boolean[9][10];
            M = Integer.parseInt(br.readLine());
            if (M == 0) { break; }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int U = Integer.parseInt(st.nextToken());
                String LU = st.nextToken();
                int V = Integer.parseInt(st.nextToken());
                String LV = st.nextToken();
                int ux = LU.charAt(0)-'A';
                int uy = LU.charAt(1)-'0'-1;
                int vx = LV.charAt(0)-'A';
                int vy = LV.charAt(1)-'0'-1;

                sudoku[ux][uy] = U;
                sudoku[vx][vy] = V;

                // ux 행에 U라는 수가 존재하고
                // uy 열에 U라는 수가 존재함을 표시
                c1[ux][U] = c2[uy][U] = true;
                c1[vx][V] = c2[vy][V] = true;

                // (ux, uy) 가 속해있는 3*3 박스번호를 구함
                int uBoxNum = (ux/3)*3 + uy/3;
                int vBoxNum = (vx/3)*3 + vy/3;

                // uBoxNum 번 박스에 U라는 수가 존재함을 표시
                c3[uBoxNum][U] = true;
                c3[vBoxNum][V] = true;

                // U, V 로 구성된 도미노가 존재함을 표시
                domino[U][V] = domino[V][U] = true;
            }

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= 9; i++) {
                String point = st.nextToken();
                int px = point.charAt(0)-'A';
                int py = point.charAt(1)-'0'-1;
                sudoku[px][py] = i;
                c1[px][i] = c2[py][i] = true;
                c3[(px/3)*3 + py/3][i] = true;
            }

            System.out.println("Puzzle " + count++);
            go(0);
        }
    }

    // index 는 (0, 0) ~ (80, 80) 까지의 좌표를 나타냄
    static boolean go(int index) {

        // (80, 80) 까지 도달하였으므로 스도쿠 출력
        if (index == 81) {
            printSudoku();

            // 답이 구해지면 true 를 리턴하여 모든 함수 종료
            return true;
        }

        // 숫자 하나를 좌표로 표현
        int x = index / 9;
        int y = index % 9;

        // 현재 위치가 비어있지 않을경우 다음 좌표로 이동
        if (sudoku[x][y] != 0) {
           if (go(index+1)) {
               return true;
           }
        }
        else {
            // 현재 위치가 비어있을 경우
            // for 문(i)을 통해 도미노가 놓일 수 있는 2가지 경우를 체크
            for (int i = 0; i < 2; i++) {

                // (nx, ny) 는 x, y 의 바로 오른쪽 혹은 바로 아래쪽 좌표가 됨
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 도미노가 놓일 수 있는 범위인지
                // 혹은 비어있는 칸이 아닌지를 체크
                if (nx >= 9 || ny >= 9 || sudoku[nx][ny] != 0) {
                    continue;
                }

                // 여기까지 도달하면 일단 도미노를 놓을 수는 있는 것이므로
                // 하나의 도미노의 두개의 수에 0~9 를 모두 넣어본다
                for (int j = 1; j <= 9; j++) {
                    for (int k = 1; k <= 9; k++) {

                        // 도미노는 2개의 서로 다른 숫자로 구성되어 있어야함
                        if (j == k) {
                            continue;
                        }

                        // 이미 존재하는 도미노임
                        if (domino[j][k] && domino[k][j]) {
                            continue;
                        }

                        // 스도쿠 체크 : (x,y) 에 j 가 들어갈 수 있는지 체크
                        if (c1[x][j] || c2[y][j] || c3[(x/3)*3 + y/3][j]) { continue; }

                        // 스도쿠 체크 : (nx, ny) 에 k 가 들어갈 수 있는지 체크
                        if (c1[nx][k] || c2[ny][k] || c3[(nx/3)*3 + ny/3][k]) { continue; }

                        c1[x][j] = c2[y][j] = c3[(x/3)*3 + y/3][j] = true;
                        c1[nx][k] = c2[ny][k] = c3[(nx/3)*3 + ny/3][k] = true;
                        sudoku[x][y] = j;
                        sudoku[nx][ny] = k;
                        domino[j][k] = domino[k][j] = true;
                        if (go(index+1)) {
                            return true;
                        }
                        c1[x][j] = c2[y][j] = c3[(x/3)*3 + y/3][j] = false;
                        c1[nx][k] = c2[ny][k] = c3[(nx/3)*3 + ny/3][k] = false;
                        sudoku[x][y] = 0;
                        sudoku[nx][ny] = 0;
                        domino[j][k] = domino[k][j] = false;
                    }
                }
            }
        }
        return false;
    }

    static void printSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            } System.out.println();
        }
    }
}
