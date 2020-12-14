import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q10816 {
    static int n, m;
    static int[] arr1, arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (binarySearch(arr2[i])) {
                sb.append(1+" ");
            } else {
                sb.append(0+" ");
            }
        }
        System.out.print(sb);
    }

    // 이분탐색
    static boolean binarySearch(int x) {
        int start = 0;
        int end = arr1.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr1[mid] == x) {
                return true;
            } else if (arr1[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
