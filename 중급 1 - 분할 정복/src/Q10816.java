import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q10816 {
    static int n, m;
    static int[] arr1, arr2;
    // 숫자의 범위가 어느정도 제한적인 때는 배열을 잡고 체크해서 풀 수 있지만
    // 그렇지 않을 경우에는 lower bound, upper bound 를 사용해야함
    // static int[] positive = new int[10000001]; // 0 ~ 10000000
    // static int[] negative = new int[10000001]; // -1 ~ -10000000
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            /*
            if (arr1[i] >= 0) {
                positive[arr1[i]]++;
            } else {
                negative[Math.abs(arr1[i])]++;
            }
             */
        }
        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);
        for (int i = 0; i < m; i++) {
            int count = upperBound(arr2[i]) - lowerBound(arr2[i]);
            sb.append(count).append(" ");
        }

        /*
        for (int i = 0; i < m; i++) {
            binarySearch(arr2[i]);
        }
         */

        System.out.print(sb);
    }

    // lower bound : x 이상인 값이 가장 먼저 등장하는 위치
    static int lowerBound(int x) {
        int position = 0;
        int start = 0;
        int end = arr1.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr1[mid] == x) {
                position = mid;
                end = mid - 1;
            } else if (arr1[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return position;
    }

    // upper bound : x 를 초과하는 값이 가장 먼저 등장하는 위치
    static int upperBound(int x) {
        int position = 0;
        int start = 0;
        int end = arr1.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr1[mid] == x) {
                position = mid + 1;
                start = mid + 1;
            } else if (arr1[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return position;
    }

    /*
    // 이분탐색
    static void binarySearch(int x) {
        int start = 0;
        int end = arr1.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr1[mid] == x) {
                if (x >= 0) {
                    sb.append(positive[x]).append(" ");
                } else {
                    sb.append(negative[Math.abs(x)]).append(" ");
                }
                return;
                sb.append(map.get(x)).append(" ");
            } else if (arr1[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        sb.append(0).append(" ");
    }
     */
}
