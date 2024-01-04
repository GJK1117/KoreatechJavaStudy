import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class P10986_나머지합 {
    public static void main(String[] args) throws IOException{
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 스페이스 단위로 입력하기 위해 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 입력할 숫자 개수 변수
        int suNo = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        // 순차 값을 저장할 배열
        long[] input = new long[suNo + 1];
        // 나머지 값을 저장할 배열
        long[] C = new long[M];

        // 토큰 초기화
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        long cnt = 0;

        // 배열 값 초기화
        for(int i = 1; i <= suNo; i++){
            // i번째 값에는 이전(<i) 값을 모두 더한 값에 입력한 값을 더한 값을 저장
            input[i] = input[i - 1] + Long.parseLong(stringTokenizer.nextToken());
        }

        // 구간 합의 배열 값으로 구간의 나머지를 저장
        for(int i = 1; i <= suNo; i++){
            // 나머지 계산
            int tmp = (int)(input[i] % M);
            // 나머지가 0이 되는 구간은 바로 count
            if(tmp==0) cnt++;
            // 구간의 나머지가 같은 부분을 count
            C[tmp]++;
        }

        // 나머지를 저장한 배열을 통해 결과 계산
        for(int i = 0; i < M; i++){
            // 나머지가 같은 구간이 2개 이상일 때 계산이 가능
            if(C[i]>1){
                // 각 위치를 2개를 뽑아 나머지가 0인 새로운 구간을 만들 수 있음, x Combination 2
                cnt += (C[i]*(C[i]-1)/2);
            }
        }
        // 결과 출력
        System.out.println(cnt);
    }
}
