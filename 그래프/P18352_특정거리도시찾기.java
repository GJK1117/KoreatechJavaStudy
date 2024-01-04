import java.io.*;
import java.util.*;

public class P18352_특정거리도시찾기 {
    // 인접리스트
    static ArrayList<Integer>[] A;
    // 방문 여부 배열
    static int[] visited;
    public static void main(String[] args) throws IOException {
        // 버퍼 입출력, 토큰 선언 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 문제 상황에서 주어진 변수 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 인접리스트, 방문 여부 배열 초기화, 인덱스와 실제 주어진 값과 맞추기 위해 배열의 크기를 N+1로 지정
        A = new ArrayList[N+1];
        visited = new int[N+1];
        // 기본적으로 방문하지 않은 도시를 -1로 초기화
        Arrays.fill(visited, -1);
        // 인접리스트 초기화
        for(int i = 0; i < N+1; i++) A[i] = new ArrayList<>();

        // M개의 도로 입력
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 단방향이기 때문에 a -> b의 관계만 저장
            A[a].add(b);
        }
        // BFS 수행
        BFS(X, K);

        // 방문 여부 배열을 확인하여 K와 같은 값이 있는지 확인
        boolean check = false;
        for(int i = 1; i < N+1; i++){
            if(visited[i] == K) {
                bw.write(i + "\n");
                // 한 번이라도 K와 같은 값이 있으면 true
                check = true;
            }
        }

        // 한 번이라도 K와 같은 값이 있으면 버퍼를 비우며 출력 아니면 -1 출력
        if(check) bw.flush();
        else System.out.println(-1);
    }

    // BFS 함수, 시작 도시로부터 거리를 측정하기 위해 BFS 사용
    public static void BFS(int s, int k){
        // Queue선언 및 초기화, 시작 도시 입력
        Queue<Integer> q = new LinkedList<>();
        // 거리 변수, 시작 도시는 0으로 초기화
        int dist = 0;
        q.add(s);
        visited[s] = dist;
        while(!q.isEmpty()){
            int tmp = q.poll();
            // 현재 노드의 거리를 dist에 저장
            dist = visited[tmp];
            // 현재 노드의 인접한 노드를 탐색
            for(int i : A[tmp]){
                // 한 번도 방문하지 않은 곳일 경우
                if(visited[i] == -1){
                    // 현재 노드의 거리 + 1의 값을 visited 배열에 저장
                    visited[i] = dist + 1;
                    // Queue에 추가
                    q.add(i);
                }
            }
        }
    }
}
