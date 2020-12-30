import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q17406 {
    static int n, m, k;
    static int[][] arr;
    static ArrayList<int[]> rotation = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int[] tmp = new int[3];
            tmp[0] = Integer.parseInt(st.nextToken());
            tmp[1] = Integer.parseInt(st.nextToken());
            tmp[2] = Integer.parseInt(st.nextToken());
            rotation.add(tmp);
        }

        go(0);
        System.out.print(answer);
    }

    static int[] order = new int[6];
    static boolean[] visited = new boolean[6];
    static void go(int index) {

        if (index == rotation.size()) {
            int[][] tmp_arr = new int[n+1][m+1];
            arrayCopy(arr, tmp_arr);
            for (int i = 0; i < rotation.size(); i++) {
                int idx = order[i];
                tmp_arr = rotate(tmp_arr, rotation.get(idx));
            }
            int val = getVal(tmp_arr);
            answer = Math.min(answer, val);
            return;
        }

        for (int i = 0; i < rotation.size(); i++) {
            if (visited[i]) { continue; }
            visited[i] = true;
            order[index] = i;
            go(index+1);
            visited[i] = false;
        }
    }

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    static int[][] rotate(int[][] arr, int[] rotation) {
        int r = rotation[0];
        int c = rotation[1];
        int s = rotation[2];
        int sx = r-s, sy = c-s;
        int ex = r+s, ey = c+s;

        // System.out.println("s: "+sx+","+sy);
        // System.out.println("e: "+ex+","+ey);

        int[][] tmp = new int[n+1][m+1];
        arrayCopy(arr, tmp);
        func(arr, tmp, sx, sy, ex, ey);

        /*
        System.out.println("변환전 -> ");
        printArr(arr);
        System.out.println("변환후 -> ");
        printArr(tmp);
         */

        return tmp;
    }

    static void func(int[][] arr, int[][] dst, int sx, int sy, int ex, int ey) {

        if ((sx == ex && sy == ey) || (sx > ex && sy > ey)) {
            return;
        }

        for (int i = sy; i <= ey-1; i++) {
            dst[sx][i+1] = arr[sx][i];
        }

        for (int i = sx; i <= ex-1; i++) {
            dst[i+1][ey] = arr[i][ey];
        }

        for (int i = sy+1; i <= ey; i++) {
            dst[ex][i-1] = arr[ex][i];
        }

        for (int i = sx+1; i <= ex; i++) {
            dst[i-1][sy] = arr[i][sy];
        }

        func(arr, dst, sx+1, sy+1, ex-1, ey-1);
    }

    static int getVal(int[][] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= m; j++) {
                cnt += arr[i][j];
            }
            min = Math.min(min, cnt);
        }
        return min;
    }

    static void arrayCopy(int[][] src, int[][] dst) {
        for (int i = 1; i <= n; i++) {
            if (m >= 0) System.arraycopy(src[i], 1, dst[i], 1, m);
        }
    }

    static void printArr(int[][] arr) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(arr[i][j]+ " ");
            } System.out.println();
        } System.out.println();
    }
}
