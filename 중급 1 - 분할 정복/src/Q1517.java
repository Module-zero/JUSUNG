import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1517 {
    static int n;
    static int[] arr;
    static long nSwap = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sort(0, n-1);
        System.out.print(nSwap);
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
        int[] tmp = new int[end-start+1];
        int mid = (start + end) / 2;
        int a = start, b = mid+1, k = 0;

        while (a <= mid && b <= end) {
            if (arr[a] > arr[b]) {
                tmp[k++] = arr[b++];
                nSwap += mid + 1 - a;
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
