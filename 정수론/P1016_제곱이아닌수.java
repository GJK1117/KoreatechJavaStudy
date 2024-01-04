import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1016_제곱이아닌수 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        // [min, max] 구간 입력
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        // 제곱ㄴㄴ수의 개수를 저장하는 변수
        int count = 0;
        // 제곱ㄴㄴ수를 판별하기 위한 배열
        boolean[] check = new boolean[(int)(max - min + 1)];

        // 2부터 max의 제곱근까지 반복
        for(long i = 2; i * i <= max; i++){
            // 제곱수 계산
            long pow = i * i;
            // min보다 크거나 같은 제곱수의 시작 인덱스 계산
            long start_idx = min / pow;
            // 시작 수가 제곱수의 시작이 아닌 경우 1을 더함
            if(min % pow != 0) start_idx++;

            // 제곱수의 시작 인덱스부터 max까지 반복
            for(long j = start_idx; j * pow <= max; j++){
                // 제곱수를 표시
                check[(int)(j * pow - min)] = true;
            }
        }

        // 표시 되지 않은 위치 즉 제곱ㄴㄴ수만을 카운트
        for(int i = 0; i <= (int)(max - min); i++){
            if(!check[i]) count++;
        }
        // 결과 출력
        System.out.println(count);
    }
}
