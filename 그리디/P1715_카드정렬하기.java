import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1715_카드정렬하기 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 정렬된 상태로 저장하기 위한 우선순위 큐 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 입력 개수 변수
        int N = Integer.parseInt(bufferedReader.readLine());
        // 결과 값 변수, 입력 값 변수
        int result = 0, input;

        // 값 입력, 우선순위 큐에 값 추가
        for(int i = 0; i < N; i++){
            input = Integer.parseInt(bufferedReader.readLine());
            pq.add(input);
        }

        // 우선순위 큐의 크기가 1이 될 때까지 반복, 모든 연산 후 결국 1개의 값이 남음
        while(pq.size() != 1){
            // 우선순위 큐에서 2개의 값을 빼고 그 값을 더한 값 저장
            int tmp = pq.poll() + pq.poll();
            // 결과 값에 더한 값 저장
            result += tmp;
            // 다시 더한 값을 우선순위 큐에 추가
            pq.add(tmp);
        }

        // 결과 출력
        System.out.println(result);
    }
}
