import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class P1260_DFS와BFS {
    // 인접행렬 배열
    static boolean[][] map;
    // 방문 여부 배열
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 스페이스 단위로 입력하기 위해 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 숫자 개수 변수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // 간선 개수 변수
        int M = Integer.parseInt(stringTokenizer.nextToken());
        // 시작 노드 변수
        int start = Integer.parseInt(stringTokenizer.nextToken());
        // 인접행렬 배열 및 방문 여부 배열 초기화
        map = new boolean[N+1][N+1];
        visited = new boolean[N+1];
        // 간선 입력
        for(int i = 0; i < M; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            map[a][b] = true;
            map[b][a] = true;
        }

        // DFS 결과 수행
        DFS(start);
        System.out.println();
        // 방문 여부 배열 초기화
        visited = new boolean[N+1];
        // BFS 결과 수행
        BFS(start);
    }

    // DFS 함수, Stack을 활용
    public static void DFS(int v){
        // Stack 선언
        Stack<Integer> s = new Stack<>();
        // 시작 노드 삽입
        s.push(v);
        // Stack이 빌 때까지 반복
        while(!s.isEmpty()){
            // Stack에서 값 추출, 방문할 노드
            int tmp = s.pop();
            // 방문 여부 확인, 방문하지 않은 노드일 경우
            if(!visited[tmp]) {
                // 방문했다고 표시
                visited[tmp] = true;
                // 결과에 출력
                System.out.print(tmp+" ");
                // 인접행렬을 통해 연결된 노드를 Stack에 삽입
                for(int i = map.length - 1; i > 0; i--){
                    if(map[tmp][i] && !visited[i]){
                        s.push(i);
                    }
                }
            }

        }
    }

    // BFS 함수, Queue를 활용
    public static void BFS(int v){
        // Queue 선언
        Queue<Integer> q = new LinkedList<>();
        // 시작 노드 삽입
        q.add(v);
        // 시작 노드를 방문 처리
        visited[v] = true;
        // Queue가 빌 때까지 반복
        while(!q.isEmpty()){
            // Queue에서 값 추출, 방문할 노드
            int tmp = q.poll();
            // 결과 출력
            System.out.print(tmp+" ");
            // 인접행렬을 통해 연결된 노드를 Queue에 삽입
            for(int i = 1; i < map.length; i++){
                if(map[tmp][i] && !visited[i]){
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
