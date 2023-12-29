import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2023_신기한소수 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 자리수(1~8) 입력
        int N = Integer.parseInt(bufferedReader.readLine());
        // 첫 시작 숫자 배열, 소수로 시작
        int[] prime = {2, 3, 5, 7};
        // 다음 숫자 배열, 2의 배수는 되지 않고, 5의 배수는 0, 5로 끝나기 때문에 1, 3, 7, 9만 가능
        int[] nextPrime = {1, 3, 7, 9};

        // 2, 3, 5, 7 숫자로 시작하는 소수 찾기
        for(int i = 0; i < 4; i++){
            func(N, prime[i], nextPrime, 1);
        }
    }

    // 소수를 찾는 함수
    public static void func(int N, int num, int[] nextPrime, int count){
        // N자리수가 되면 출력
        if(N==count) System.out.println(num);
        // N자리수에 도달하지 않은 경우
        else {
            // 다음 숫자를 저장한 배열을 통해 다음 수 탐색
            for (int i = 0; i < 4; i++) {
                // 다음 수를 만들기 위해 (기존 숫자)*10 + (다음 숫자)를 계산
                int tmp = num * 10 + nextPrime[i];
                // 다음 수가 소수일 경우
                if (isPrime(tmp)) {
                    // 재귀 함수로 한 자리수가 더 많은 다음 수 탐색
                    func(N, tmp, nextPrime, count + 1);
                }
            }
        }
    }

    // 소수인지 판단하는 함수
    public static boolean isPrime(int num){
        // 2~root(num)까지 나누어지는지 확인, 가장 효과적인 소수 판단 방법
        for(int i = 2; i <= Math.sqrt(num); i++){
            // 나누어진다면 소수가 아니므로 false 반환
            if(num%i==0) return false;
        }
        // 끝까지 나누어지지 않는 소수이므로 true 반환
        return true;
    }
}
