import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15683 {
    static int n, m;
    static int[][] map = new int[8][8];
    static ArrayList<Pair> cctvs = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new Pair(i, j));
                }
            }
        }

        int[][] tmp_map = new int[n][m];
        arrayCopy(map, tmp_map);
        go(0, tmp_map);
        System.out.println(min);
    }

    static void go(int index, int[][] arr) {

        if (index == cctvs.size()) {
            min = Math.min(min, getBlind(arr));
            return;
        }

        // 원본 저장
        int[][] src = new int[n][m];
        arrayCopy(arr, src);

        Pair p = cctvs.get(index);
        int x = p.getX(), y = p.getY();
        int num = map[x][y];

        switch (num) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    setEyes(x+dx[i], y+dy[i], i, arr);
                    go(index+1, arr);
                    arrayCopy(src, arr);
                }
                break;
            case 2 :
                for (int i = 0; i < 2; i++) {
                    setEyes(x+dx[i], y+dy[i], i, arr);
                    setEyes(x+dx[i+2], y+dy[i+2], i+2, arr);
                    go(index+1, arr);
                    arrayCopy(src, arr);
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    setEyes(x+dx[i], y+dy[i], i, arr);
                    setEyes(x+dx[(i+1)%4], y+dy[(i+1)%4], (i+1)%4, arr);
                    go(index+1, arr);
                    arrayCopy(src, arr);
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    setEyes(x+dx[i], y+dy[i], i, arr);
                    setEyes(x+dx[(i+1)%4], y+dy[(i+1)%4], (i+1)%4, arr);
                    setEyes(x+dx[(i+2)%4], y+dy[(i+2)%4], (i+2)%4, arr);
                    go(index+1, arr);
                    arrayCopy(src, arr);
                }
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    setEyes(x+dx[i], y+dy[i], i, arr);
                }
                go(index+1, arr);
                break;
        }
    }

    static void setEyes(int x, int y, int dir, int[][] arr) {
        while (x >= 0 && y >= 0 && x < n && y < m && map[x][y] != 6) {
            arr[x][y] = -1;
            x += dx[dir];
            y += dy[dir];
        }
    }

    static int getBlind(int[][] arr) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void arrayCopy(int[][] src, int[][] dst) {
        for (int i = 0; i < n; i++) {
            if (m >= 0) System.arraycopy(src[i], 0, dst[i], 0, m);
        }
    }

    static class Pair {
        private final int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}
