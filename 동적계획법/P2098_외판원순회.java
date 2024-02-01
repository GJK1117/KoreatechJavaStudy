package 동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2098_외판원순회 {
    static int N;
    static int W[][];
    static int D[][];
    static final int INF = 1000000*16+1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = new int[N][N];
        for(int i=0; i<N; i++){     //인접 행렬 입력받기
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //D[c][v]라고 할 때 v는 비트로 유지함
        //c는 출발 도시, v는 그동안 방문한 도시, D[c][v]는 남은 도시를 방문하기 위해 필요한 최소 비용임
        //예를 들면 4개 도시 중 1, 2, 4번만 방문했다면 v = 1011(2) = 9
        D = new int[N][1<<N];
        for(int i=0; i<N; i++){
            Arrays.fill(D[i], -1);     //방문 여부 판단을 위해 -1로 초기화
        }
        System.out.println(tsp(0, 1));      //DFS의 변형
    }
    static int tsp(int c, int v){   //c는 출발 도시, v는 도착 도시(비트)
        if(v == (1<<N) - 1){        //모든 도시를 탐색했다면(예를 들어 도시 개수가 4일 때 v는 1111(2), (1<<4)-1도 1111)
            //마지막 도시에서 원점으로 돌아가는데 길이 없다면(길이 없으면 0임) INF 반환, 아니면 비용 반환
            return W[c][0] == 0 ? INF : W[c][0];
        }
        if(D[c][v] != -1){         //이미 방문했다면 리턴
            return D[c][v];
        }
        D[c][v] = INF; //min 연산을 하기 위해 충분히 큰 수로 매핑
        for(int i=0; i<N; i++){     //도시 개수만큼 반복
            //방문한 적이 없고 갈 수 없는 도시가 아닐 때
            //v & (1<<i) == 0은 비트마스킹을 통해 방문 여부 판단(v와 1<<i에 &연산을 해서 0이 나온다면 방문하지 않은 것)
            if((v & (1<<i)) == 0 && W[c][i] != 0){
                //재귀를 통해 DFS를 수행하며 min으로 최선의 비용 비교
                D[c][v] = Math.min(D[c][v], tsp(i, (v | (1<<i))) + W[c][i]);
            }
        }
        return D[c][v];     //재귀가 전부 끝나면 최종 비용 반환
    }
}
