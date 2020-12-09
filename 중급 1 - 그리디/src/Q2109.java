import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2109 {
    static int n;
    static Pair[] list;
    static PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new Pair[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[i] = new Pair(p, d);
        }

        Arrays.sort(list);
        int sum = 0;
        int count = list.length-1;
        for (int i = 10000; i >= 1; i--) {
            while (count >= 0) {
                if (i == list[count].getD()) {
                    heap.add(list[count].getP());
                    count--;
                } else {
                  break;
                }
            }
            if (!heap.isEmpty()) {
                sum += heap.poll();
            }
        }
        System.out.print(sum);
    }

    static class Pair implements Comparable<Pair> {
        private int p, d;
        public Pair(int p, int d) {
            this.p = p;
            this.d = d;
        }
        public int getP() { return p; }
        public int getD() { return d; }
        @Override
        public int compareTo(Pair o) {
            if (this.d != o.d) {
                return this.d - o.d;
            } else {
                return o.p - this.p;
            }
        }
    }
}
