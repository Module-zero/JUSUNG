import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q11651 {
    static int n;
    static ArrayList<Pair> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Pair(x, y));
        }

        Collections.sort(list);
        for (Pair p : list) {
            System.out.println(p);
        }
    }

    static class Pair implements Comparable<Pair> {
        private final int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Pair o) {
            if (this.y != o.y) {
                return this.y - o.y;
            } else {
                return this.x - o.x;
            }
        }
        @Override
        public String toString() {
            return x+" "+y;
        }
    }
}
