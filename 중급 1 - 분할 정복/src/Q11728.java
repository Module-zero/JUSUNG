import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11728 {
    static int n, m;
    static long[] arr1, arr2, res;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n =  Integer.parseInt(st.nextToken());
        m =  Integer.parseInt(st.nextToken());
        arr1 = new long[n];
        arr2 = new long[m];
        res = new long[n+m];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬되어있는 두 배열을 이어붙임
        for (int i = 0; i < n+m; i++) {
            res[i] = i < n ? arr1[i] : arr2[i-n];
        }

        merge(n+m-1, n-1);
        System.out.print(sb);
    }

    // 중간을 기준으로 좌우는 정렬된 상태여야함
    // 즉 start ~ mid-1, mid ~ end 는 각각 정렬되어 있어야함
    static void merge(int end, int mid) {
        long[] tmp = new long[n+m];
        int i = 0, j = mid + 1, pos = 0;

        while (i <= mid && j <= end) {
            tmp[pos++] = res[i] >= res[j] ? res[j++] : res[i++];
        }

        while (i <= mid) { tmp[pos++] = res[i++]; }
        while (j <= end) { tmp[pos++] = res[j++]; }

        for (int k = 0; k < n+m; k++) {
            sb.append(tmp[k]).append(" ");
        }
    }
}
