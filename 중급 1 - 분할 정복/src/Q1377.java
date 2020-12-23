import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1377 {
    static int n;
    static Pair[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(br.readLine());
            arr[i] = new Pair(val, i);
        }

        sort(0, n-1);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i].getIdx()-i > max) {
                max = arr[i].getIdx()-i;
            }
        }
        System.out.print(max+1);
    }

    static class Pair {
        private final int val, idx;
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
        public int getVal() { return val; }
        public int getIdx() { return idx; }
    }

    static void sort(int start, int end) {
        if (start == end ) {
            return;
        }
        int mid = (start + end) / 2;
        sort(start, mid);
        sort(mid + 1, end);
        merge(start, end);
    }

    static void merge(int start, int end) {
        Pair[] tmp = new Pair[end-start+1];
        int mid = (start + end) / 2;
        int a = start, b = mid+1, k = 0;

        while (a <= mid && b <= end) {
            if (arr[a].getVal() > arr[b].getVal()) {
                tmp[k++] = arr[b++];
            } else {
                tmp[k++] = arr[a++];
            }
        }

        while (a <= mid) { tmp[k++] = arr[a++]; }
        while (b <= end) { tmp[k++] = arr[b++]; }

        for (int i = start; i <= end; i++) {
            arr[i] = tmp[i-start];
        }
    }
}
