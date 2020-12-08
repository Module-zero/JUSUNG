import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1202 {
    static int n, k;
    static Pair[] list;
    static TreeMap<Integer, Integer> map = new TreeMap<>();
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        list = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            list[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < k; i++) {
            int c = Integer.parseInt(br.readLine());
            if (map.containsKey(c)) {
                int val = map.get(c);
                map.put(c, val+1);
            } else {
                map.put(c, 1);
            }
        }

        Arrays.sort(list, Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            int m = list[i].getM();
            int v = list[i].getV();

            // 무게가 m 인 보석을 넣을 가방이 있다면
            Integer bag;
            if ((bag = map.ceilingKey(m)) != null) {
                answer += v;
                int val = map.get(bag)-1;
                if (val == 0) {
                    map.remove(bag);
                } else {
                    map.put(bag, val);
                }
            }
        }
        System.out.print(answer);
    }

    static class Pair implements Comparable<Pair> {
        private int m, v;
        public Pair(int m, int v) {
            this.m = m;
            this.v = v;
        }
        public int getM() { return m; }
        public int getV() { return v; }

        @Override
        public int compareTo(Pair o) {
            return this.v - o.v;
        }
    }
}
