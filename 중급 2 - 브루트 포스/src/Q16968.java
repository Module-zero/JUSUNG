import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16968 {
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        go(0, 1, 'n');
    }

    static void go(int index, int sum, char prev) {

        if (index == arr.length) {
            System.out.print(sum);
            return;
        }

        int num = (arr[index] == 'd' ? 10 : 26);
        if (arr[index] == prev) {
            num--;
        }

        go(index+1, sum*num, arr[index]);
    }
}
