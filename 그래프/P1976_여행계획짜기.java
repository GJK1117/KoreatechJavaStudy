package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1976_여행계획짜기 {
    static int head[];      //대표 노드
    static int A[][];       //도시 정보
    static int route[];     //경로 정보
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    //도시의 수
        int M = Integer.parseInt(br.readLine());    //경로에 있는 도시 수
        //도시 정보 입력받기
        A = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //경로 정보 입력받기
        route = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            route[i] = Integer.parseInt(st.nextToken());
        }
        //대표 노드 초기화
        head = new int[N+1];
        for(int i=1; i<=N; i++){
            head[i] = i;
        }
        //도시 연결 정보 저장하기
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(A[i][j] == 1)
                    union(i, j);
            }
        }
        //경로상 여행이 한번에 가능한지(루트 노드가 같은지) 체크하기
        int check = find(route[0]);
        boolean answer = true;
        for(int i=1; i<route.length; i++){
            if(check != find(route[i])) {
                answer = false;
                break;
            }
        }
        if(answer)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
    //a의 대표 노드와 b의 대표 노드가 다르면 같게 만들어줌
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b)
            head[b] = a;
    }
    //a의 대표 노드와 b의 대표 노드가 같은지 재귀를 통해 탐색
    public static int find(int a){
        //a의 값과 a의 대표 노드의 값이 같아질 때(루트 노드)를 찾을 때까지 수행
        if(a == head[a])
            return a;
            //재귀함수를 빠져나오면서 그동안 거쳤던 노드의 대표 값을 전부 루트 노드로 변경
        else
            return head[a] = find(head[a]);
    }
}
