import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순열을 활용한 브루트 포스 사용

// n <= 10000 이고 시간복잡도는 O(n*n) 이므로 브루트 포스로 해결 가능

public class Q10972 {

    static int[] perm;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = Integer.parseInt(st.nextToken());
        }

        int a = 0;
        for (int i = 1; i < n; i++) {
            if (perm[i-1] < perm[i]) {
                a = i;
            }
        }

        if (a == 0) {
            System.out.print(-1);
            return;
        }

        int b = 0;
        for (int i = a+1; i < n; i++) {
            if (perm[a-1] < perm[i]) {
                b = i;
            }
        }

        if (b != 0) {
            swap(a-1, b);
        }
        else {
            swap(a-1, a);
        }

        int i = a;
        int j = n-1;
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }

        for (i = 0; i < perm.length; i++) {
            System.out.print(perm[i] + " ");
        } System.out.println();

    }

    static void swap(int n, int m) {
        int tmp = perm[n];
        perm[n] = perm[m];
        perm[m] = tmp;
    }
}
