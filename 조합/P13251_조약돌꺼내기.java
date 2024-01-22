package 조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13251_조약돌꺼내기 {
    static int N, M, K;
    static int color[];         //색깔 별 조약돌의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        color = new int[M];
        for(int i=0; i<M; i++){
            color[i] = Integer.parseInt(st.nextToken());    //색깔 별 개수 입력받기
            N += color[i];                                  //총 개수
        }
        K = Integer.parseInt(br.readLine());
        double p = 0;               //총 확률
        for(int i=0; i<M; i++){     //색깔 종류만큼 반복
            if(color[i]>=K) {        //뽑으려는 숫자가 같은 색을 가진 돌맹이의 개수보다 작으면
                double temp = 1;    //색깔 별 확률 계산을 위한 변수
                double temp2 = N;   //조약돌을 뽑을 때마다 총 개수가 줄어드는 것을 계산하기 위한 변수
                for(int j=0; j<K; j++){
                    temp *= color[i] / temp2;
                    //돌을 뽑았으므로 뽑은 돌과 같은 색 개수와 전체 돌 개수를 줄여줌
                    color[i]--;
                    temp2--;
                }
                p += temp;
            }
        }
        System.out.println(p);
    }
}