import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q3019 {
    static int c, p;
    static int[] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        c = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        arr = new int[c];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < c; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 블록의 밑면의 높낮이를 0, 1, 2 로 구분
        switch (p)  {
            case 1 :
                solve("0");
                solve("0000");
                break;
            case 2 :
                solve("00");
                break;
            case 3 :
                solve("001");
                solve("10");
                break;
            case 4 :
                solve("100");
                solve("01");
                break;
            case 5 :
                solve("000");
                solve("01");
                solve("101");
                solve("10");
                break;
            case 6 :
                solve("000");
                solve("00");
                solve("011");
                solve("20");
                break;
            case 7 :
                solve("000");
                solve("00");
                solve("110");
                solve("02");
                break;
        }

        System.out.print(answer);
    }

    static void solve(String block) {
        int len = block.length();
        int cnt = arr.length - len + 1;
        for (int i = 0; i < cnt; i++) {
            boolean flag = false;
            for (int j = 0; j < len-1; j++) {
                int blockDiff = block.charAt(j) - block.charAt(j+1);
                int fieldDiff = arr[i+j] - arr[i+j+1];
                // System.out.println(blockDiff+","+fieldDiff);
                if (blockDiff != fieldDiff) {
                    flag = true;
                    break;
                }
            }
            if (!flag) { answer++; }
        }
    }
}
