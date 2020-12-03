import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Q2667 {

    static int N;
    static int[][] map;
    static int[][] check;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int count = 0;
    static LinkedList<Pair> queue = new LinkedList<>();
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        check = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j)-'0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && check[i][j] == 0) {
                    count++;
                    list.add(bfs(i, j));
                }
            }
        }

        System.out.println(count);
        Collections.sort(list);
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    static int bfs(int x, int y) {
        // 각 단지에 속하는 집의 수
        int nEstate = 0;
        check[x][y] = count;
        queue.add(new Pair(x, y));
        nEstate++;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (map[nx][ny] == 1 && check[nx][ny] == 0) {
                    check[nx][ny] = count;
                    queue.add(new Pair(nx, ny));
                    nEstate++;
                }
            }
        }
        return nEstate;
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
