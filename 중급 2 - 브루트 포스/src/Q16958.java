import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q16958 {
    static int n, t, m;
    static ArrayList<City> list = new ArrayList<>();
    static int[][] map = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new City(s, x, y));
        }

        for (int i = 0; i < n; i++) {
            City c1 = list.get(i);
            for (int j = 0; j < n; j++) {
                City c2 = list.get(j);
                int d = getDirectDist(c1.getX(), c1.getY(), c2.getX(), c2.getY());
                if (c1.getS() == 1 && c2.getS() == 1) {
                    d = Math.min(d, t);
                }
                map[i][j] = d;
            }
        }

        // 플로이드-워셜 알고리즘
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
                }
            }
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(map[a-1][b-1]);
        }
    }

    static int getDirectDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    static class City {
        private final int s, x, y;
        public City(int s, int x, int y) {
            this.s = s;
            this.x = x;
            this.y = y;
        }
        public int getS() { return s; }
        public int getX() { return x; }
        public int getY() { return y; }
        @Override
        public String toString() { return s+","+x+","+y; }
    }
}
