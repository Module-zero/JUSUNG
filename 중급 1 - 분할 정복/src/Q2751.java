import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2751 {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        sort(0, arr.length-1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void sort(int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start+end)/2;
        sort(start, mid);
        sort(mid+1, end);
        // start ~ mid, mid+1 ~ end 까지 각각 정렬이 되어있어야함
        merge(start, end);
    }

    static void merge(int start, int end) {
        int[] tmp = new int[end-start+1];
        int mid = (start+end)/2;
        int a = start, b = mid+1, k = 0;
        while (a <= mid && b <= end) {
            tmp[k++] = (arr[a] >= arr[b]) ? arr[b++] : arr[a++];
        }

        while (a <= mid) { tmp[k++] = arr[a++]; }
        while (b <= end) { tmp[k++] = arr[b++]; }

        for (int i = start; i <= end; i++) {
            arr[i] = tmp[i-start];
        }
    }
}
