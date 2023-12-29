import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class P1744_수묶기 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 정렬된 상태로 저장하기 위한 우선순위 큐 선언
        // pq1 : 양의 정수를 내림차순으로 정렬, pq2 : 음의 정수를 오름차순으로 정렬
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        // 입력 개수 변수
        int N = Integer.parseInt(bufferedReader.readLine());
        // 결과 값 변수, 입력 값 변수
        int result = 0, input;
        // 0, 1의 개수 변수
        int one = 0, zero = 0;

        // 값 입력, 우선순위 큐에 값 추가
        for(int i = 0; i < N; i++){
            input = Integer.parseInt(bufferedReader.readLine());
            // 양의 정수, 1, 0, 음의 정수를 구분하여 처리
            // 양의 정수는 pq1에 추가
            if(input > 1) pq1.add(input);
            // 1의 개수 증가
            else if(input == 1) one++;
            // 0의 개수 증가
            else if(input == 0) zero++;
            // 음의 정수는 pq2에 추가
            else pq2.add(input);
        }

        // 한 번 루프에 두 번 값을 제거하므로 pq1의 크기가 1이 될 떄까지 반복
        while(pq1.size() > 1){
            // 가장 큰 순서로 두 개의 양수를 뽑아 곱셈
            int tmp = pq1.poll() * pq1.poll();
            // tmp를 결과에 저장
            result += tmp;
        }
        // 남은 양의 정수를 결과에 그대로 더함
        // 이 경우 다른 경우의 수보다 그냥 더하는 상황이 결과가 더 크게 나옴
        if(pq1.size() == 1) result += pq1.poll();

        // 한 번 루프에 두 번 값을 제거하므로 pq1의 크기가 1이 될 떄까지 반복
        while(pq2.size() > 1){
            // 절대값이 가장 큰 순서로 두 개의 음수를 뽑아 곱셈
            int tmp = pq2.poll() * pq2.poll();
            // tmp를 결과에 저장
            result += tmp;
        }

        // 남은 음의 정수가 있을 경우
        if(!pq2.isEmpty()){
            // 입력 값 중에 0이 없을 경우 그대로 결과에 더함
            // 입력 값 중에 0이 있을 경우 둘이 곱해져서 더할 경우 0이 더해지는 것이므로 else문이 따로 없는 것임
            if(zero == 0) result += pq2.poll();
        }

        // 1은 따로 결과에 더하는 것이 곱해져서 더하는 것보다 큼
        result += one;
        // 결과 출력
        System.out.println(result);
    }
}
