package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11724_연결요소의개수 {
    static ArrayList<Integer>[] A;  //ArrayList 배열
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   //노드의 개수
        int m = Integer.parseInt(st.nextToken());   //에지의 개수
        A = new ArrayList[n+1];
        visited = new boolean[n+1];
        //ArrayList 초기화
        for(int i=1; i<n+1; i++){
            A[i] = new ArrayList<Integer>();
        }
        //그래프 저장
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            //양방향 그래프이므로 양쪽에 저장
            A[s].add(e);
            A[e].add(s);
        }
        //연결 요소란 한 번의 DFS가 끝날 때까지 탐색한 모든 노드의 집합(한 덩어리)
        int count = 0;      //연결 요소
        //DFS 실행하기(방문하지 않은 노드가 없을 때까지)
        for(int i=1; i<n+1; i++){
            if(!visited[i]){    //i번 노드를 한 번도 방문하지 않았을 때
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }
    static void DFS(int a){
        //이미 방문한 노드면 return
        if(visited[a])
            return;
        //방문 여부 기록
        visited[a] = true;
        //A(인접 리스트)의 크기만큼 방문
        for(int i : A[a]){
            //방문하지 않은 노드일 경우 깊이 우선 탐색(재귀)
            if(!visited[i])
                DFS(i);
        }
    }
}
