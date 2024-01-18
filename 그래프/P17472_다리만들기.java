import java.io.*;
import java.util.*;

public class P17472_다리만들기 {
    // 버퍼 입출력 및 토큰 입력 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // 2차원의 지도 배열, 땅과 바다 정보를 입력할 2차원 배열
    static int[][] map;
    // union-find을 구현할 parent배열, 부모 노드 저장
    static int[] parent;
    // 방문 여부 배열
    static boolean[][] visited;
    // 2차원 배열에서 상하좌우 1칸 이동을 구현할 배열
    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    // 각 섬의 위치 정보들을 저장할 배열
    static ArrayList<ArrayList<int[]>> sumlist;
    // 하나의 섬를 구성하는 위치 정볻들을 저장할 배열, sumlist에 add될 정보
    static ArrayList<int[]> mlist;
    // 문제 상황의 지도 크기(N*M), 섬의 개수 변수
    static int N, M, sNum = 1;
    // 최소 신장 트리를 구현할 우선순위 큐
    static PriorityQueue<Edge> edge;
    public static void main(String[] args) throws IOException {
        // 지도의 크기 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 지도 배열, 방문 여부 배열 초기화
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        // 땅과 바다의 정보 입력
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 각 섬들의 정보를 입력받을 sumlist 초기화
        sumlist = new ArrayList<>();
        // 지도를 탐색하며 섬들을 구분
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                // 섬 처리가 되지 않은 위치이며 지도 상의 땅이라고 표시된 위치
                if(map[i][j] != 0 && !visited[i][j]){
                    // BFS 탐색으로 인접한 땅 덩어리를 하나의 섬으로 인식
                    BFS(i, j);
                    // 섬의 개수 증가
                    sNum++;
                    // 섬을 구성하는 위치 정보들아 저장된 mlist를 sumlist에 추가
                    sumlist.add(mlist);
                }
            }
        }

        // 우선순위 큐 초기화
        edge = new PriorityQueue<>();
        // 각 섬들의 정보를 받아옴
        for(ArrayList<int[]> i : sumlist){
            // 각 섬들을 구성하는 위치 정보를 받아옴
            for(int[] j : i){
                // 위치 정보 설정 및 현재 위치의 섬 번호 받아옴
                int r = j[0];
                int c = j[1];
                int now_S = map[r][c];

                // 상하좌우 한 칸씩 이동
                for(int d = 0; d < 4; d++){
                    int dr = dx[d];
                    int dc = dy[d];

                    // 다리의 길이 변수
                    int blen = 0;
                    // 한 방향으로 계속 이어나가며 섬을 발견할 때까지 이동
                    while(r + dr > 0 && r + dr <= N && c + dc > 0 && c + dc <= M){
                        // 이동한 땅이 현재 섬인 경우 break
                        if(map[r + dr][c + dc] == now_S) break;
                        // 현재 섬이 아닌 땅인 경우
                        else if(map[r + dr][c + dc] != 0){
                            // 다리의 길이가 2 이상일 때만 적합한 경우임
                            if(blen > 1) edge.add(new Edge(now_S, map[r + dr][c + dc], blen));
                            break;
                        }
                        // 바다인 경우 다리의 길이를 연장
                        else blen++;

                        // 상하좌우 이동 방향에 따라서 방향은 유지한 채 한 칸씩 이동
                        if(dr < 0) dr--;
                        else if(dr > 0) dr++;
                        else if(dc < 0) dc--;
                        else if(dc > 0) dc++;
                    }
                }
            }
        }

        // 각 섬의 부모노드를 지정할 배열 초기화, 이를 통해 모두가 연결되어 있는지 판단
        parent = new int[sNum];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        // 사용한 다리의 개수, 사용한 다리의 총 길이 변수 초기화
        int useEdge = 0, result = 0;
        // 최소 신장 트리 탐색
        while(!edge.isEmpty()){
            Edge tmp = edge.poll();
            // 부모가 같지 않은 경우(각 섬과 연결되지 않은 상태)
            if(find(tmp.s) != find(tmp.e)) {
                // 부모를 같게 하며, 결과에 다리의 길이를 더하고 다리 개수 증가
                union(tmp.s, tmp.e);
                result += tmp.w;
                useEdge++;
            }
        }

        // 섬의 개수 - 1개의 다리 개수로 구성되어 있는 경우 결과 출력,
        // sNum은 존재하는 섬의 개수 보다 1 더 크게 정의 되어있으므로 sNum - 2를 함
        if(useEdge == sNum - 2) System.out.println(result);
        else System.out.println(-1);
    }

    // 각 땅들을 섬으로 정의하는 함수, BFS탐색 사용
    public static void BFS(int x, int y){
        // BFS탐색을 위한 큐 선언
        Queue<int[]> q = new LinkedList<>();
        // mlist 초기화
        mlist = new ArrayList<>();
        addNode(x, y, q);

        // 큐가 빌 때까지 반복
        while(!q.isEmpty()){
            // 현재 위치 반환 및 설정
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];

            for(int d = 0; d < 4; d++){
                // 상하좌우 1칸씩 이동을 정의하는 배열을 통해 인접한 땅 탐색
                int dr = dx[d];
                int dc = dy[d];
                // 섬을 구성하는 위치 정보를 mlist에 추가되는 작업
                while(r + dr > 0 && r + dr <= N && c + dc > 0 && c + dc <= M){
                    // 땅이면서 섬 처리를 하지않은 땅이면 큐와 mlist에 위치 정보 추가
                    if(map[r+dr][c+dc] != 0 && !visited[r+dr][c+dc]){
                        addNode(r + dr, c + dc, q);
                    }
                    else break;

                    // 상하좌우 이동 방향에 따라서 방향은 유지한 채 한 칸씩 이동
                    // 이 방법을 사용하지 않고도 코드는 원활히 동작, 효율성 부분에서도 차이 거의 없음
                    if(dr < 0) dr--;
                    else if(dr > 0) dr++;
                    else if(dc < 0) dc--;
                    else if(dc > 0) dc++;
                }
            }
        }
    }

    // BFS함수에서 위치 정보를 큐와 mlist에 추가하며 방문처리 및 섬라벨링을 하는 함수
    public static void addNode(int i, int j, Queue<int[]> q){
        visited[i][j] = true;
        map[i][j] = sNum;
        int[] tmp = {i, j};
        mlist.add(tmp);
        q.add(tmp);
    }

    // union, find 함수
    // 두 위치의 부모가 다른 경우 a쪽 부모로 통일
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b) parent[b] = a;
    }
    //a의 대표 노드와 b의 대표 노드가 같은지 재귀를 통해 탐색
    public static int find(int a){
        //a의 값과 a의 대표 노드의 값이 같아질 때(루트 노드)를 찾을 때까지 수행
        if(a == parent[a]) return a;
        //재귀함수를 빠져나오면서 그동안 거쳤던 노드의 대표 값을 전부 루트 노드로 변경
        else return parent[a] = find(parent[a]);
    }

    // Edge 정보 클래스, Comparable<T> 상속
    static class Edge implements Comparable<Edge>{
        public int s, e, w;
        public Edge(int s, int e, int w){
            this.s = s;
            this.e = e;
            this.w = w;
        }

        // implements로 상속받을 경우 오버라이딩 필요
        @Override
        public int compareTo(Edge e){
            return (this.w > e.w) ? 1 : -1;
        }
    }
}
