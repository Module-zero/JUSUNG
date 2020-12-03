import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q17141 {

    static int n, m;
    static int[][] map;
    static int[] order;
    static ArrayList<Pair> virusPair = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        order = new int[m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusPair.add(new Pair(i, j));
                }
            }
        }

        go(0, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.print(-1);
        }
        else {
            System.out.print(answer);
        }

    }

    static void go(int index, int start) {

        if (index == m) {
            LinkedList<Pair> q = new LinkedList<>();
            int[][] visited = new int[n][n];
            int max = Integer.MIN_VALUE;
            for (int i : order) {
                q.add(virusPair.get(i));
                visited[virusPair.get(i).getX()][virusPair.get(i).getY()] = 1;
            }
            while (!q.isEmpty()) {
                Pair p = q.poll();
                if (max < visited[p.getX()][p.getY()]) {
                    max = visited[p.getX()][p.getY()];
                }
                for (int i = 0; i < 4; i++) {
                    int nx = p.getX() + dx[i];
                    int ny = p.getY() + dy[i];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                        continue;
                    }
                    if (map[nx][ny] != 1 && visited[nx][ny] == 0) {
                        q.add(new Pair(nx, ny));
                        visited[nx][ny] = visited[p.getX()][p.getY()] + 1;
                    }
                }
            }

            // 모든 빈칸이 다 찼을 경우에만 정답 갱신
            if (isFull(visited)) {
                if (answer > max-1) {
                    answer = max-1;
                }
            }

            return;
        }

        for (int i = start; i < virusPair.size(); i++) {
            order[index] = i;
            go(index+1, i+1);
        }
    }

    static boolean isFull(int[][] visited) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && visited[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
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
