import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1253_좋은수 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 스페이스 단위로 입력하기 위해 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 입력할 숫자 개수 변수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // 순차 값을 저장할 배열
        long[] input = new long[N];
        // 결과 값
        int result = 0;

        // 토큰 초기화
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 배열 값 초기화
        for(int i = 0; i < N; i++){
            input[i] = Long.parseLong(stringTokenizer.nextToken());
        }
        // 배열 정렬(내부적으로 퀵 정렬 사용)
        Arrays.sort(input);

        // i번째 값을 기준으로 lo, hi를 이용하여 2개의 값을 더해 i번째 값과 같은지 비교
        for(int i = 0; i < N; i++){
            // lo, hi 초기화, lo는 왼쪽끝부터 hi는 오른쪽 끝부터 시작
            int lo=0, hi=N-1;
            // hi가 lo와 같을 때까지 반복
            while(hi>lo){
                // 겹치지 않는 두 값을 더한 값
                long tmp = input[lo]+input[hi];
                // 두 값의 합이 i번째 값과 같을 경우
                if(tmp==input[i]){
                    // lo, hi 값은 i가 되면 안됨, 아닌 경우 result 증가
                    if(i!=lo && i!=hi){
                        result++;
                        break;
                    }
                    // lo, hi 값이 i일 경우 lo, hi를 서로 가까운 방향으로 한 칸씩 이동
                    else if(i==lo) lo++;
                    else if(i==hi) hi--;
                }
                // 두 값의 합이 i번째 값보다 작을 경우 lo를 오른쪽으로 이동
                else if(tmp<input[i]) lo++;
                // 두 값의 합이 i번째 값보다 클 경우 hi를 왼쪽으로 이동
                else hi--;
            }
        }

        // 결과 출력
        System.out.println(result);
    }
}
