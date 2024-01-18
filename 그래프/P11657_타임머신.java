import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11657_타임머신 {
    // 엣지 리스트
    static Edge[] list;
    // 거리 배열
    static long[] dist;
    // 최대값 변수
    static int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 버퍼 입력 및 토큰 입력 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시의 개수, 버스의 개수 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 엣지 리스트, 거리 배열 초기화, 거리 배열은 MAX값이 초기 값
        list = new Edge[M + 1];
        dist = new long[N + 1];
        Arrays.fill(dist, MAX);

        // M번 노선의 정보 입력
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[i] = new Edge(A, B, C);
        }

        // 문제 풀이를 위한 함수 호출
        solv(1, N, M);
    }

    // 벨만-포드 탐색 구현 함수
    static void solv(int s, int N, int M) throws IOException{
        // 출발 노드의 거리 값을 0으로 설정
        dist[s] = 0;

        // 엣지 리스트를 통해 거리 배열 계산, 도시 - 1번 반복
        for(int i = 1; i < N; i++){
            // 엣지 개수만큼 반복
            for(int j = 1; j <= M; j++){
                Edge tmp = list[j];
                // 거리 값이 밝혀진 경우(연결성이 있는)이며 거리 값이 기존보다 작을 경우 값 입력
                if(dist[tmp.u] != MAX && dist[tmp.v] > dist[tmp.u] + tmp.w){
                    dist[tmp.v] = dist[tmp.u] + tmp.w;
                }
            }
        }

        // 음수 사이클 여부 변수
        boolean mCycle = false;
        // 모든 엣지를 돌며 가중치 계산함
        for(int i = 1; i <= M; i++){
            Edge tmp = list[i];
            // 도달한 도시이며, 그 값이 더 작은 값으로 변경될 가능성이 있는 경우 음수 사이클이 존재
            if(dist[tmp.u] != MAX && dist[tmp.v] > dist[tmp.u] + tmp.w){
                mCycle = true;
            }
        }

        // 음수 사이클이 있는 경우 -1, 아닌 경우 거리 배열 출력, 한 번도 방문하지 않은 경우는 -1로 처리
        if(mCycle) System.out.println(-1);
        else{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for(int i = 2; i <= N; i++){
                long val = dist[i];
                bw.write(((val == MAX) ? -1 : val) + "\n");
            }
            bw.flush();
            bw.close();
        }
    }
}

// Edge 정보 클래스
class Edge{
    public int u;
    public int v;
    public int w;

    Edge(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
