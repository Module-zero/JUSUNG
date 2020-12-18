import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Q11652 {
    static int n;
    static HashMap<BigInteger, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            BigInteger b = new BigInteger(br.readLine());
            if (map.containsKey(b)) {
                int v = map.get(b);
                map.put(b, v+1);
            } else {
                map.put(b, 1);
            }
        }

        List<BigInteger> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> {
            int v1 = map.get(o1);
            int v2 = map.get(o2);
            if (v2 != v1) {
                return v2-v1;
            } else {
                return o1.compareTo(o2);
            }
        });

        /*
        list.sort(new Comparator<BigInteger>() {
            @Override
            public int compare(BigInteger o1, BigInteger o2) {
                int v1 = map.get(o1);
                int v2 = map.get(o2);
                if (v2 != v1) {
                    return v2-v1;
                } else {
                    return o1.compareTo(o2);
                }
            }
        });
         */

        System.out.print(list.get(0));
    }
}
