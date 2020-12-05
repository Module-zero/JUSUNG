import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1931 {
    static int n;
    static Pair[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(start, end);
        }

        // 일찍 끝나는 회의 순으로 정렬
        // 같은 시간에 끝나는 회의라면 일찍 시작하는 회의 순으로 정렬
        Arrays.sort(arr);

        int count = 0, now = 0;
        for (int i = 0; i < n; i++) {
            if (now <= arr[i].getStart()) {
                now = arr[i].getEnd();
                count++;
            }
        }

        System.out.print(count);
    }

    static class Pair implements Comparable<Pair> {
        private int start, end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int getStart() { return start; }
        public int getEnd() { return end; }

        @Override
        public int compareTo(Pair o) {
            if (this.end != o.end) {
                return this.end - o.end;
            } else {
                return this.start - o.start;
            }
        }
    }
}
