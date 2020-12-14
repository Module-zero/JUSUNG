import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q10816 {
    static int n, m;
    static int[] arr1, arr2;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            if (!map.containsKey(arr1[i])) {
                map.put(arr1[i], 1);
            } else {
                int value = map.get(arr1[i]);
                map.put(arr1[i], value + 1);
            }
        }
        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);
        for (int i = 0; i < m; i++) {
            binarySearch(arr2[i]);
        }
        System.out.print(sb);
    }

    // 이분탐색
    static void binarySearch(int x) {
        int start = 0;
        int end = arr1.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr1[mid] == x) {
                sb.append(map.get(x)).append(" ");
            } else if (arr1[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        sb.append(0).append(" ");
    }
}
