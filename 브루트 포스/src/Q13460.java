import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13460 {

    static int N;
    static int M;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;

    /*
    static boolean[][] checkR;
    static boolean[][] checkB;
     */
    static boolean[][][][] check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        /*
        checkR = new boolean[N][M];
        checkB = new boolean[N][M];
         */
        check = new boolean[N][M][N][M];

        int rx=-1, ry=-1, bx=-1, by=-1;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
               board[i][j] = s.charAt(j);
               if (board[i][j] == 'R') {
                   rx = i;
                   ry = j;
               }
               if (board[i][j] == 'B') {
                   bx = i;
                   by = j;
               }
            }
        }

        check[rx][ry][bx][by] = true;
        go(rx, ry, bx, by, 0, -1);
        check[rx][ry][bx][by] = false;
        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    // direction : 0(위쪽), 1(아래쪽), 2(왼쪽), 3(오른쪽)
    static void go(int rx, int ry, int bx, int by, int depth, int backwards) {

        // System.out.println(rx+","+ry+","+bx+","+by);

        if (depth > 10) {
            return;
        }

        // 빨간구슬, 파란구슬이 모두 구멍에 들어가면 종료
        if (board[rx][ry] == 'O' && board[bx][by] == 'O') {
            return;
        }

        // 파란구슬만 구멍에 들어가면 종료
        if (board[bx][by] == 'O') {
            // System.out.println("파란 구슬만 들어갔으므로 종료");
            return;
        }

        // 빨간구슬만 구멍에 들어가면 최소값 갱신 후 종료
        if (board[rx][ry] == 'O') {
            // System.out.print("정답 후보 찾음!!");
            // System.out.print("-> " + depth + "\n");
            min = Math.min(min, depth);
            return;
        }

        for (int i = 0; i <= 3; i++) {

            /*
            System.out.print("기울이는 방향 : ");
            switch (i) {
                case 0 : System.out.println("위쪽"); break;
                case 1 : System.out.println("아래쪽"); break;
                case 2 : System.out.println("왼쪽"); break;
                case 3 : System.out.println("오른쪽"); break;
                default: break;
            }
             */

            int rnx = rx + dx[i];
            int rny = ry + dy[i];

            while (true) {

                // 빨간구슬이 벽을 만나면
                if (board[rnx][rny] == '#') {
                    // 구슬을 벽 바로 이전 위치로 옮긴 뒤 break
                    rnx += (-dx[i]);
                    rny += (-dy[i]);
                    break;
                }
                // 빨간구슬이 구멍을 만나면
                if (board[rnx][rny] == 'O') {
                    // 구슬 위치 이동없이 break
                    break;
                }
                // 벽도 구멍도 아니면 다음칸으로 이동
                rnx += dx[i];
                rny += dy[i];
            }

            // 파란구슬도 빨간구슬과 동일한 과정 진행
            int bnx = bx + dx[i];
            int bny = by + dy[i];

            while (true) {
                if (board[bnx][bny] == '#') {
                    bnx += (-dx[i]);
                    bny += (-dy[i]);
                    break;
                }
                if (board[bnx][bny] == 'O') {
                    break;
                }
                bnx += dx[i];
                bny += dy[i];
            }

            // 빨간구슬, 파란구슬이 구멍이 아닌 위치에서 같이 있는 경우
            // 이동하기 전의 두 구슬의 선후 관계에 따라 구슬위치를 재조정
            if (board[rnx][rny] != 'O' && (rnx == bnx && rny == bny)) {
                // System.out.println("같은 위치에 있음");
                // 아래쪽으로 기울인 경우
                if (dx[i] == 1) {
                    // 파란구슬이 빨간구슬보다 위에 있었다면
                    // 파란구슬을 한칸 위로 이동시킴
                    if (rx > bx) { bnx += (-dx[i]); }
                    else {rnx += (-dx[i]); }
                }
                // 위쪽으로 기울인 경우
                if (dx[i] == -1) {
                    // 파란구슬이 빨간구슬보다 아래에 있었다면
                    // 파란구슬을 한칸 아래로 이동시킴
                    if (rx < bx) { bnx += (-dx[i]); }
                    else {rnx += (-dx[i]); }
                }
                // 오른쪽으로 기울인 경우
                if (dy[i] == 1) {
                    if (ry > by) { bny += (-dy[i]); }
                    else {rny += (-dy[i]); }
                }
                // 왼쪽으로 기울인 경우
                if (dy[i] == -1) {
                    if (ry < by) { bny += (-dy[i]); }
                    else {rny += (-dy[i]); }
                }
            }

            // 왔던 방향으로는 다시 기울이지 않음
            if (backwards == i) {
                continue;
            }

            /*
            if (checkR[rnx][rny]) {
                // System.out.println("이미 방문함");
                continue;
            }
            checkR[rnx][rny] = true;
             */

            if (check[rnx][rny][bnx][bny] == true) {
                continue;
            }


            // checkB[bnx][bny] = true;
            /*
            System.out.println("방문으로 체크 : " + rnx+","+rny+","+bnx+","+bny);

            System.out.println("조정된 빨간 구슬의 위치 : " + rnx + "," + rny);
            System.out.println("조정된 파란 구슬의 위치 : " + bnx + "," + bny);
            System.out.println();

             */

            check[rnx][rny][bnx][bny] = true;
            go(rnx, rny, bnx, bny, depth+1, i%2==0?i+1:i-1);
            check[rnx][rny][bnx][bny] = false;

           /*
            checkR[rnx][rny] = false;
            checkB[bnx][bny] = false;
            */
        }
    }
}
