import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Q1655 {
    static int n;
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());
            int minSize = minHeap.size(), maxSize = maxHeap.size();

            if (minSize == 0 && maxSize == 0) {
                maxHeap.add(k);
            } else if (maxSize == minSize) {
                int minTop = minHeap.element();
                if (k <= minTop) {
                    maxHeap.add(k);
                } else {
                    maxHeap.add(minHeap.poll());
                    minHeap.add(k);
                }
            } else {
                int maxTop = maxHeap.element();
                if (k <= maxTop) {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(k);
                } else {
                    minHeap.add(k);
                }
            }

            sb.append(maxHeap.element()).append("\n");
        }

        System.out.print(sb);
    }
}
