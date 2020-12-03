import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q4991 {

    static int w, h;
    static char[][] map;
    static int[][] visited;
    static LinkedList<Pair> queue;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static Pair clean;  // 청소기의 시작위치
    static Pair[] dirty;// 더러운 칸의 위치
    static int idx;
    static int[] order;
    static boolean[] check;
    static int min;
    static int[] dp1;
    static int[][] dp2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                return;
            }

            map = new char[h][w];
            dirty = new Pair[10];
            idx = 0;
            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == 'o') {
                        clean = new Pair(i, j);
                    } else if (map[i][j] == '*') {
                        dirty[idx++] = new Pair(i, j);
                    }
                }
            }

            dp1 = new int[idx];
            dp2 = new int[idx][idx];
            order = new int[idx];
            check = new boolean[idx];
            min = Integer.MAX_VALUE;

            if (go(0)) {
                System.out.println(-1);
            }
            else {
                System.out.println(min);
            }
        }
    }

    static boolean go(int index) {

        if (index == idx) {
            int res = 0;
            int tmp = 0;

            // 청소기에서 맨 처음가는 더러운칸의 거리를 dp1에 저장
            if (dp1[order[0]] != 0) {
                res = dp1[order[0]];
            }
            else {
                tmp = bfs(clean, dirty[order[0]]);
                if (tmp != -1) {
                    dp1[order[0]] = tmp;
                    res = tmp;
                }
                else {
                    return true;
                }
            }

            for (int i = 0; i < idx-1; i++) {
                if (dp2[order[i]][order[i+1]] != 0) {
                    res += dp2[order[i]][order[i+1]];
                }
                else {
                    tmp = bfs(dirty[order[i]], dirty[order[i + 1]]);
                    if (tmp != -1) {
                        dp2[order[i]][order[i + 1]] = tmp;
                        res += tmp;
                    }
                    else {
                        return true;
                    }
                }
            }

            min = Math.min(min, res);
            return false;
        }

        for (int i = 0; i < idx; i++) {
            if (check[i] == true) { continue; }
            check[i] = true;
            order[index] = i;
            if (go(index+1)) {
                return true;
            }
            check[i] = false;
        }
        return false;
    }

    // p1 부터 p2 까지 최단거리를 리턴
    static int bfs(Pair p1, Pair p2) {
        visited = new int[h][w];
        queue = new LinkedList<>();
        int sx = p1.getX();
        int sy = p1.getY();
        int ex = p2.getX();
        int ey = p2.getY();

        visited[sx][sy] = 1;
        queue.add(p1);
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (p.getX() == ex && p.getY() == ey) {
                return (visited[ex][ey]-1);
            }
            for (int i = 0; i < 4; i++) {
                int px = p.getX() + dx[i];
                int py = p.getY() + dy[i];
                if (px < 0 || py < 0 || px >= h || py >= w) {
                    continue;
                }
                if (map[px][py] != 'x' && visited[px][py] == 0) {
                    visited[px][py] = visited[p.getX()][p.getY()] + 1;
                    queue.add(new Pair(px, py));
                }
            }
        }
        return -1;
    }

    static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}
