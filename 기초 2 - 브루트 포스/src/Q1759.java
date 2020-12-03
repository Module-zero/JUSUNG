import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1759 {

    static int L;
    static int C;
    static char[] alpha;
    static char[] answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alpha = new char[C];
        answer = new char[L];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alpha);
        go(0, 0);
        System.out.print(sb);
    }

    static void go(int index, int start) {
        if (index == L) {
            if (check(answer)) {
                for (int i = 0; i < L; i++) {
                    sb.append(answer[i]);
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            answer[index] = alpha[i];
            go(index+1, i+1);
        }
    }

    static boolean check(char[] arr) {
        int nVowel = 0;
        int nConsonant = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                if (arr[i] == 'a' || arr[i] == 'i' || arr[i] == 'u' || arr[i] == 'e' || arr[i] == 'o') {
                    nVowel++;
                } else {
                    nConsonant++;
                }
            }
        }
        if (nVowel >= 1 && nConsonant >= 2) {
            return true;
        }
        else {
            return false;
        }
    }
}
