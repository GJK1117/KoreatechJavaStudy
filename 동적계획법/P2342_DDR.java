package 동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2342_DDR {
    static int N;       //이동 횟수+1
    static int M[];     //이동 경로
    static int D[][][];    //총 이동 횟수, 왼발, 오른발
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.countTokens();
        M = new int[N];
        for(int i=1; i<N; i++){
            M[i] = Integer.parseInt(st.nextToken());
        }
        D = new int[N+1][5][5];
        for(int i=0; i<N+1; i++){           //최솟값 비교를 위해 충분히 큰 수로 초기화(100001*4)
            for(int j=0; j<5; j++){
                for(int k=0; k<5; k++){
                    D[i][j][k] = 400004;
                }
            }
        }
        D[0][0][0] = 0;
        for(int i=1; i<N; i++){     //이동 횟수만큼 반복
            for(int left=0; left<5; left++){
                for(int right=0; right<5; right++){
                    //현재 left에서 right가 M[i]로 이동했을 때 값을 전부 매핑해줌(min으로 대소 비교)
                    D[i][left][M[i]] = Math.min(D[i-1][left][right] + move(right, M[i]), D[i][left][M[i]]);
                }
            }
            for(int right=0; right<5; right++){
                for(int left=0; left<5; left++){
                    //현재 right에서 left가 M[i]로 이동했을 때 값을 전부 매핑해줌(min으로 대소 비교)
                    D[i][M[i]][right] = Math.min(D[i-1][left][right] + move(left, M[i]), D[i][M[i]][right]);
                }
            }
            //min을 통해 최솟값을 저장하면서 갱신됨에 따라 최솟값만 남게 됨
        }
        int min = Integer.MAX_VALUE;
        //이동이 끝난 후 값들의 비교를 통해 최솟값을 찾아줌
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                min = Math.min(min, D[N-1][i][j]);
            }
        }
        System.out.println(min);
    }
    static int move(int s, int e){
        if(s==0)                        //원점에서 이동
            return 2;
        else if(Math.abs(s-e) == 0)     //제자리
            return 1;
        else if(Math.abs(s-e) == 1 || Math.abs(s-e) == 3)   //옆으로 이동
            return 3;
        else                            //반대편으로 이동
            return 4;
    }
}
