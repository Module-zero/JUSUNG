import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1339 {

    static int N;
    static char[][] words;
    static int[] alpha = new int[26];
    static int[] alphaToNum;
    static boolean[] check = new boolean[10];
    static int nVaildAlpha = 0;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new char[N][8];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                words[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                if (words[i][j] != 0) {
                    int index = (int)words[i][j] - 65;
                    alpha[index] = 1;
                }
            }
        }

        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] == 1) {
                nVaildAlpha++;
            }
        }

        alphaToNum = new int[nVaildAlpha];
        go(0);
        System.out.print(max);
    }

    // 수열을 오름차순으로 뽑아내면 자동으로 중복은 없게됨
    static void go(int index) {

        if (index == nVaildAlpha) {

            int i, j, sum = 0;
            int[] alphaCopy = alpha.clone();
            for (i = 0, j = 0; i < alphaCopy.length; i++) {
                if (alphaCopy[i] == 1) {
                    alphaCopy[i] = alphaToNum[j++];
                }
            }

            for (i = 0; i < words.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (j = 0; j < words[i].length; j++) {
                    if (words[i][j] != 0) {
                        sb.append(alphaCopy[words[i][j] - 65]);
                    }
                }
                sum += Integer.parseInt(sb.toString());
            }

            if (max < sum) {
                max = sum;
            }

            return;
        }

        for (int i = 0; i < 10; i++) {
            if (check[i] == true) { continue; }
            check[i] = true;
            alphaToNum[index] = i;
            go(index+1);
            check[i] = false;
        }
    }
}
