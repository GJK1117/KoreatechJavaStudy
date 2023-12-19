import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class P2018_연속된자연수의합 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 스페이스 단위로 입력하기 위해 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 입력할 숫자 개수 변수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // 구간의 왼쪽(lo), 오른쪽(hi), 구간의 개수(count), 구간의 합(sum)
        int lo=1, hi=1, count=1, sum=1;
        // 구간이 N을 포함하면 종료
        while(hi!=N){
            // 구간의 합이 N이 될 경우
            if(sum==N){
                // count 증가, 구간에서 lo를 뺴고 lo+1을 추가
                count++;
                sum-=lo;
                lo++;
            }
            // 구간의 합이 N보다 클 경우
            else if(sum>N){
                // 구간에서 lo를 뺴고 lo+1을 추가
                sum-=lo;
                lo++;
            }
            // 구간의 합이 N보다 작을 경우
            else {
                // 구간에 hi+1을 추가
                hi++;
                sum+=hi;
            }
        }
        System.out.println(count);
    }
}
