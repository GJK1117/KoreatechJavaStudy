import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1256_사전 {
    static int N, M, K;
    static int[][] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new int[201][201];

        //조합 경우의 수 초기화
        //list[i][j] 이면 i 개 중 j 개를 선택할때의 경우의 수로 초기화
        for(int i=0; i<=200; i++){
            for(int j=0; j<=i; j++){
                if(j==0 || j==i) list[i][j] = 1;    //j==1 이거나 i==j 일경우 iCj=1 이므로 1로 초기화
                else{
                    list[i][j] = list[i-1][j-1] + list[i-1][j]; //조합의 성질을 이용한 경우의 수 계산
                    if (list[i][j] > 1000000000) list[i][j] = 1000000001;   //K 범위 벗어났을 때 큰 수 저장
                }
            }
        }

        if(list[N+M][M] < K) System.out.println(-1);    //최대의 경우가 K보다 작으면 -1 출력
        else{
            while(!(N==0 && M==0)){
                //N-1개의 a와 M개의 z로 만들 수 있는 경우의 수가 K보다 크다면 현재 자리에 a를 넣음
                //a를 제외한 나머지 글자들로 만들 수 있는 경우의 수가 K보다 크므로 그 경우의 수 안에는 K번째의 경우가 존재
                if(list[N-1+M][M] >= K){
                    System.out.print('a');
                    N--;    //a를 하나 빼줌
                }
                //반대의 경우엔 경우의 수 안에는 K번째의 경우가 존재하지 않으므로 z를 넣어줌
                else {
                    System.out.print('z');
                    K -= list[N-1+M][M];    //앞이 a인 경우를 K에서 빼줌
                    M--;    //z를 하나 빼줌
                }
            }
        }
    }
}

