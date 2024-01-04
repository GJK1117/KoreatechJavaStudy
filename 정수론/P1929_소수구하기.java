import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1929_소수구하기 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 구간[N, M] 입력
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        // N부터 M까지 반복
        for(int i = N; i <= M; i++){
            // 소수인지 판단하는 변수
            boolean isPrime = true;
            // 2 ~ i의 제곱근까지 반복하여 소수 판단
            for(int j = 2; j * j <= i; j++){
                // 나누어 질 경우 소수가 아님
                if(i % j == 0) {
                    // 소수가 아니므로 isPrime을 false로 변경하고 반복문 종료
                    isPrime = false;
                    break;
                }
            }
            // 예외인 1을 제외한 경우에서 소수만 출력
            if(isPrime && i != 1) System.out.println(i);
        }
    }
}
