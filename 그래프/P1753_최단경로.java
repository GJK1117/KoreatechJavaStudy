import java.io.*;
import java.util.*;

public class P1753_최단경로 {
    // 인접 리스트
    static ArrayList<Edge>[] A;
    // 방문 여부 배열
    static boolean[] visited;
    // 거리 정보 배열(결과 출력 배열)
    static int[] dist;
    // 최대 값 설정 변수
    static int MAX = Integer.MAX_VALUE;
    // 다익스트라 탐색 구현을 위한 우선순위 큐
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        // 버퍼 입출력 및 토큰 입력 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드의 수, 간선의 수 입력
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        // 인접 리스트, 방문 배열, 거리 배열 초기화
        A = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) A[i] = new ArrayList<>();
        visited = new boolean[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, MAX);

        // 시작 노드 K 입력 및 K 위치 거리 값 0 초기화
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        dist[K] = 0;

        // E번 반복하여 인접리스트의 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // u -> v 의 가중치 정보 입력
            A[u].add(new Edge(v, w));
        }
        // 문제 풀이를 위한 함수 호출
        func(new Edge(K, 0));

        // 결과 출력, 방문을 한 노드의 거리를 출력, 아닌 경우는 INF로 출력
        for (int i = 1; i <= V; i++) {
            if (visited[i]) bw.write(dist[i] + "\n");
            else bw.write("INF\n");
        }
        bw.flush();
        bw.close();
    }

    // 문제 해결 함수, 다익스트라 탐색
    public static void func(Edge s) {
        // 시작 노드 우선순위 큐에 삽입
        pq.add(s);
        // 큐가 빌 떄까지 반복
        while (!pq.isEmpty()) {
            // 현재 엣지 정보를 받아옴
            Edge curr = pq.poll();
            int c_v = curr.v;

            // 방문 해 본 위치면 아래 과정을 생략
            if (visited[c_v]) continue;
            // 방문하지 않은 노드의 방문 처리
            visited[c_v] = true;

            // 해당 노드와 연결된 엣지를 통해 다음 노드의 거리 계산
            for (Edge tmp : A[c_v]) {
                int next = tmp.v;
                int weights = dist[c_v] + tmp.w;
                // 기존 거리 값보다 현재 계산한 값이 더 가까울 경우(작을 경우) 갱신
                if (dist[next] > weights) {
                    dist[next] = weights;
                    pq.add(new Edge(next, dist[next]));
                }
            }
        }
    }

    // Edge 정보 클래스, 클래스 간 우선순위를 결정하기 위해 Comparable<T> 구현
    // Comparable<T>를 구현하기 위해 클래스 내부에 compareTo 구현
    static class Edge implements Comparable<Edge>{
        // 도착 노드
        public int v;
        // 간선의 가중치
        public int w;

        // 생성자
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }

        // compareTo 함수, 비교하려는 값이 매개 변수로 주어진 값보다 클 경우 후 순위가 되며 오름차순을 구현함
        public int compareTo(Edge n){
            return (this.w > n.w) ? 1 : -1;
        }
    }
}
