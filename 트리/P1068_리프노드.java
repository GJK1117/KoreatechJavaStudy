import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class P1068_리프노드 {
    // 버퍼 입력 및 토큰 입력 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // 트리를 구현할 인접 리스트
    static ArrayList<Integer>[] tree;
    // 방문 여부 배열
    static boolean[] visited;
    // 삭제할 노드, 리프 노드 개수, 루트 노드 변수
    static int remove, count = 0, root = 0;
    public static void main(String[] args) throws IOException {
        // 노드 개수 입력
        int N = Integer.parseInt(br.readLine());
        // 방문 여부 배열 및 인접 리스트 초기화
        visited = new boolean[N];
        tree = new ArrayList[N];
        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }

        // 트리 입력(인접 리스트 입력)
        st = new StringTokenizer(br.readLine());
        for(int s = 0; s < N; s++){
            int e = Integer.parseInt(st.nextToken());
            // 무방향 가중치 없는 간선의 인접 리스트
            if(e != -1) {
                tree[s].add(e);
                tree[e].add(s);
            }
            // 부모 노드가 -1 일 경우 루트 노드이므로 루트 노드로 설정
            else root = s;
        }
        // 삭제할 노드 입력
        remove = Integer.parseInt(br.readLine());
        // 삭제할 노드가 루트 노드가 아니라면 DFS 수행
        if(remove != 0) DFS(0);
        //결과 출력
        System.out.println(count);
    }

    // DFS 탐색을 수행할 함수
    public static void DFS(int a){
        // 시작 노드를 스택에 입력
        Stack<Integer> s = new Stack<>();
        s.add(a);
        // 방문 처리
        visited[a] = true;
        // Stack이 빌 때까지 반복
        while(!s.isEmpty()){
            // 현재 노드 반환
            int now = s.pop();
            // 자식 노드 개수 변수
            int cnt = 0;
            // 인접 리스트를 통해 자식 노드 탐색
            for(int i : tree[now]){
                // 삭제할 노드이면 확인 X
                if(i == remove) continue;
                // 방문하지 않은 노드일 경우(자식 노드)
                if(!visited[i]){
                    // 방문 처리 및 자식 노드 수 추가
                    visited[i] = true;
                    cnt++;
                    // Stack에 추가
                    s.add(i);
                }
            }
            // 반복 후 계산한 자식 노드 수가 0이라면(자식 노드가 없다면) 결과 값 추가
            if(cnt == 0) count++;
        }
    }
}
