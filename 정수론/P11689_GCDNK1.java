import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11689_GCDNK1 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 선언 및 입력
        long n = Long.parseLong(br.readLine());
        // 결과 값 선언 및 n으로 초기화
        long result = n;

        // 2부터 n의 제곱근까지 반복, n의 제곱근보다 큰 경우는 n이 나누어 떨어지지 않으므로 반복할 필요가 없음
        for(long p = 2; p <= Math.sqrt(n); p++){
            // p로 나누어 떨어지는 경우
            if (n % p == 0) {
                // p로 나누어 떨어지는 경우 p로 나누어 떨어지지 않을 때까지 나누어 떨어지는 경우를 제외한 n의 개수를 빼줌
                result -= result / p;
                // n이 나누어 떨어지지 않을 때까지 나누어 p를 계산
                while(n % p == 0) n /= p;
            }
        }

        // n의 약수에서 확인하지 못한 수가 존재하는 경우, 그 수까지 확인
        if(n > 1) result -= result / n;
        // 결과 출력
        System.out.println(result);
    }
}
