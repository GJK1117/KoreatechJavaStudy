package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1300_K번째수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int s = 1;      //시작점
        int e = K;      //끝점(K보다 큰 수는 탐색할 필요가 없기 때문)
        int result = 0; //B[K]의 값

        //result보다 작거나 같은 원소의 개수는 최소 K개
        /*
        작거나 같은 원소의 개수를 구하기 위해서는
        값들을 행이나 열 번호로 나누고 나머지를 버린 몫을 구하면 됨
        */
        //B[K]의 값을 찾기 위한 이진탐색
        //m을 B[K]라고 가정하고 이진 탐색을 통해 범위를 좁혀감
        while(s <= e){
            int m = (s + e) / 2;
            int count = 0;
            //i는 행 번호
            for(int i=1; i<=N; i++){
                //m보다 작거나 같은 원소의 개수는 N을 넘을 수 없으므로 min 사용
                count += Math.min(m/i, N);
            }
            //m보다 작거나 같은 원소의 개수들의 합이 K보다 작다면
            //범위가 잘못된 것이므로 범위 수정
            if(count < K){
                s = m + 1;
            }
            //m보다 작거나 같은 원소의 개수들의 합이 K보다 크거나 같다면
            //m이 정답(B[K])일 수 있으므로 result에 m을 저장하고
            //아닌 경우를 대비해서 범위를 좁힘
            else{
                result = m;
                e = m-1;
            }
            //위 과정들을 반복하면 s와 e가 만날 때의 m이 정답이 된다.
        }
        System.out.println(result);
    }
}
