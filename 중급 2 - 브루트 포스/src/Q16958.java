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
                map[i][j] = getDirectDist(c1.getX(), c1.getY(), c2.getX(), c2.getY());
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

    static int getDist(int a, int b) {
        City c1 = list.get(a); City c2 = list.get(b); int s1 = c1.getS(), s2 = c2.getS();
        int x1 = c1.getX(), x2 = c2.getX();
        int y1 = c1.getY(), y2 = c2.getY();

        // c1-c2 까지의 다이렉트 길이
        int d1 = getDirectDist(x1, y1, x2, y2);
        if (s1 == 1 && s2 == 1) {
           d1 = Math.min(d1, t);
        }

        int d2 = Integer.MAX_VALUE;
        for (City c : list) {
            int tmp_d1 = 0, tmp_d2 = 0;
            if (s1 == 1 && c.getS() == 1) {
                tmp_d1 = t;
            } else {
                tmp_d1 = getDirectDist(x1, y1, c.getX(), c.getY());
            }
            if (s2 == 1 && c.getS() == 1) {
                tmp_d2 = t;
            } else {
                tmp_d2 = getDirectDist(x2, y2, c.getX(), c.getY());
            }
            d2 = Math.min(d2, tmp_d1+tmp_d2);
        }

        return Math.min(d1, d2);
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
