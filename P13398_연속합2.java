import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P13398_연속합2 {
    // 버퍼 입력 및 토큰 입력 정의
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        // N 입력
        int N = Integer.parseInt(br.readLine());
        // 입력, 왼쪽 합, 오른쪽 합 배열 초기화
        int[] input = new int[N];
        int[] l = new int[N], r = new int[N];

        // 수열 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        // 자기자신을 포함한 왼쪽 연속된 수 최대 합 정의
        l[0] = input[0];
        // 연속된 수의 최대 합 정의, 초기 값은 l[0](input[0])
        // 이것이 l배열에서 정의되나, r배열에서 정의되더라도 결과는 같음
        int max = l[0];
        for(int i = 1; i < N; i++) {
            l[i] = Math.max(input[i], l[i - 1] + input[i]);
            // 연속된 수의 최대 합을 갱신
            max = Math.max(l[i], max);
        }

        // 자시자신을 포함한 오른쪽 연속된 수 최대 합 정의
        r[N-1] = input[N-1];
        for(int i = N - 2; i >= 0; i--){
            r[i] = Math.max(input[i], r[i + 1] + input[i]);
        }

        for(int i = 1; i < N - 1; i++){
            // 어느 한 수를 뺀 연속된 수의 합 정의, i번째 수가 빠진 것
            int tmp = l[i - 1] + r[i + 1];
            // 기존 연속된 수의 최대 값과 비교
            max = Math.max(tmp, max);
        }

        // 최대 값 출력
        System.out.println(max);
    }
}
