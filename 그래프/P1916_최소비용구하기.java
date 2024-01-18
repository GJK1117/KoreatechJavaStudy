import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1916_최소비용구하기 {
    // 방문 여부 배열
    static boolean[] visited;
    // 거리 배열
    static int[] dist;
    // 인접 리스트
    static ArrayList<Edge>[] list;
    // 다익스트라 구현을 위한 우선순위 큐
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    // 최대값 변수
    static int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        // 버퍼 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시 개수, 버스 개수 입력
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        // 인접리스트, 방문 여부 배열, 거리 배열 초기화
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            list[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        // 처음은 최대 값을 설정
        Arrays.fill(dist, MAX);

        // 버스의 정보 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Edge(v, w));
            st = new StringTokenizer(br.readLine());
        }

        // 도시 A -> B로 가는 최단 거리 계산
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        solv(A, B);
    }

    // 다익스트라 탐색 구현 함수, 기존 1753번(책으로 56번)과 동일한 구현
    public static void solv(int s, int e){
        pq.add(new Edge(s, 0));
        dist[s] = 0;
        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int c_v = curr.v;
            if(visited[c_v]) continue;
            visited[c_v] = true;

            for(Edge tmp : list[c_v]){
                 int next = tmp.v;
                 int weights = dist[c_v] + tmp.w;
                 if(dist[next] > weights){
                     dist[next] = weights;
                     pq.add(new Edge(next, dist[next]));
                 }
            }
        }

        // A -> B 이동 거리 출력
        System.out.println(dist[e]);;
    }

    static class Edge implements Comparable<Edge>{
        int v, w;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge e){
            return (this.w > e.w) ? 1 : -1;
        }
    }
}
