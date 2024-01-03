import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class P11659_구간합구하기 {
    public static void main(String[] args) throws IOException{
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 스페이스 단위로 입력하기 위해 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 입력할 숫자 개수 변수
        int suNo = Integer.parseInt(stringTokenizer.nextToken());
        // 구간을 적용할 횟수 변수
        int qNo = Integer.parseInt(stringTokenizer.nextToken());
        // 순차 값을 저장할 배열
        long[] input = new long[suNo + 1];
        // 토큰 초기화
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 배열 값 초기화
        for(int i = 1; i <= suNo; i++){
            // i번째 값에는 이전(<i) 값을 모두 더한 값에 입력한 값을 더한 값을 저장
            input[i] = input[i - 1] + Long.parseLong(stringTokenizer.nextToken());
        }

        // 구간 값 출력
        for(int i = 1; i <= qNo; i++){
            // 토큰 초기화
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            // 구간 설정, lo = 시작 위치, hi = 끝 위치
            int lo = Integer.parseInt(stringTokenizer.nextToken());
            int hi = Integer.parseInt(stringTokenizer.nextToken());
            // (1번째부터 hi번째 값의 합) - (1번째부터 lo-1번째 값의 합) = lo <= x <= hi
            System.out.println(input[hi] - input[lo - 1]);
        }
    }
}