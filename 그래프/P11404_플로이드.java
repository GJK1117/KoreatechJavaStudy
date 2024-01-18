import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11404_플로이드 {
    // 거리가 저징된 인접행렬
    static int[][] dist;
    // 버퍼 입출력, 토큰 입력 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // 최대값, 적당히 큰 값으로 선언
    static int MAX = 10000001;

    public static void main(String[] args) throws IOException{
        // 도시의 개수, 버스의 개수 입력
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        // 인접행렬 MAX로 초기화, 자기 자신을 가리키는 거리는 0으로 초기화
        dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) Arrays.fill(dist[i], MAX);
        for(int i = 1; i <= n; i++) dist[i][i] = 0;

        // 노선 입력으로 인접행렬 입력
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], c);
        }

        // 문제 해결을 위한 함수 호출
        solv(n, m);
        // 결과 출력
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++) {
                if(dist[i][j] == MAX) bw.write(0 + " ");
                else bw.write(dist[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    // 플로이드-워셜 탐색 구현 함수
    public static void solv(int n, int m){
        // s -> e 의 최단 거리를 고려할 때, 거쳐가는 노드 k가 있다고 가정하면
        // s -> e의 거리와 s -> k -> e의 거리를 비교하여 작은 값을 최단 거리로 인식
        for(int k = 1; k <= n; k++){
            for(int s = 1; s <= n; s++){
                for(int e = 1; e <= n; e++){
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }
    }
}
