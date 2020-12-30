import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q15686 {
    static int n, m;
    static int[][] map = new int[50][50];
    static ArrayList<Pair> chicken = new ArrayList<Pair>();
    static boolean[] check = new boolean[13];
    static int[] order;
    static int delNum;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chicken.add(new Pair(i, j));
                }
            }
        }

        delNum = chicken.size() - m;
        order = new int[delNum];
        go(0, 0);
        System.out.print(min);
    }

    static void go(int index, int start) {

        if (index == delNum) {
            int[][] tmp_map = new int[n][n];
            arrayCopy(map, tmp_map);

            // 남아있는 치킨집들의 인덱스를 저장
            Set<Integer> remains = new HashSet<>();
            for (int i = 0; i < chicken.size(); i++) {
                remains.add(i);
            }

            for (int idx : order) {
                remains.remove(idx);
                Pair p = chicken.get(idx);
                tmp_map[p.getX()][p.getY()] = 0;
            }
            int dist = getDist(tmp_map, remains);
            min = Math.min(min, dist);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (check[i]) { continue; }
            check[i] = true;
            order[index] = i;
            go(index+1, i+1);
            check[i] = false;
        }
    }

    // 도시의 치킨거리를 리턴
    static int getDist(int[][] arr, Set<Integer> remains) {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                if (arr[i][j] == 1) {
                    for (Integer idx : remains) {
                        Pair p = chicken.get(idx);
                        int tmp = Math.abs(i-p.getX()) + Math.abs(j-p.getY());
                        min = Math.min(min, tmp);
                    }
                    dist += min;
                }
            }
        }
        return dist;
    }

    static void arrayCopy(int[][] src, int[][] dst) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, n);
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
