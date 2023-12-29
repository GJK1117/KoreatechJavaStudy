import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023_친구관계파악하기 {
    // 문제 조건에 맞는 상황이면 true, 아니면 false를 가지는 변수
    static boolean arrive;
    // 방문 여부를 저장하는 배열
    static boolean[] visited;
    // 친구 관계를 저장하는 배열
    static ArrayList<Integer>[] frds;

    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 스페이스 단위로 입력하기 위해 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 친구 관계의 수, 친구 관계를 확인할 사람의 수 입력
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        // 친구 관계를 저장할 배열 초기화, 각 배열 위치에 ArrayList를 저장
        frds = new ArrayList[N];
        // 각 배열 위치에 ArrayList를 할당
        for(int i = 0; i < N; i++) frds[i] = new ArrayList<>();

        // 친구 관계 입력
        for(int i = 0; i < M; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            // 서로 연결되어 있다는 의미에서 a-b, b-a 모두 추가
            frds[a].add(b);
            frds[b].add(a);
        }

        // 방문 여부를 저장할 배열 초기화 및 문제 해결 여부 false로 초기화
        visited = new boolean[N];
        arrive = false;
        // 친구 관계를 확인할 사람의 수만큼 반복
        for(int i = 0; i < N; i++){
            // 친구 관계 확인
            func(i, 1);
            // 모든 친구들이 문제 조건에 맞게 연결되어 있는 경우
            if(arrive) {
                // 1을 반환하고 main함수 종료
                System.out.print(1);
                return;
            }
        }
        // 문제 조건에 맞는 상황이 나오지 않을 경우 0을 반환
        System.out.print(0);
    }

    // 친구 관계를 확인하는 함수, DFS를 활용
    public static void func(int idx, int depth){
        // 문제 조건에 맞는 상황, 한 친구에서 시작한 관계가 총 인원 수만큼 연결 되었을 때
        // DFS에서 말하는 깊이가 총 인원 수 만큼 될 때
        if(depth==5){
            // 문제 해결이 되었으므로 arrive를 true로 변경하고 func 종료
            arrive = true;
            return;
        }
        // idx를 방문했으므로 해당 위치를 true로 변경
        visited[idx] = true;

        // idx부터 연결된 친구 관계를 확인
        for(int i : frds[idx]){
            // 연결된 친구가 방문하지 않은 친구의 경우
            if(!visited[i]){
                // 그 친구로 이어서 탐색
                func(i, depth+1);
            }
        }
        // idx를 방문하고 다시 false로 변경, 다른 관계를 통해 방문할 수 있도록 변경
        visited[idx] = false;
    }
}
