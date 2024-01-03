package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1707_이분그래프 {
    static ArrayList<Integer> A[];  //그래프 정보
    static boolean visited[];       //방문 여부
    static boolean check[];         //이분 집합 구분
    static boolean answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A = new ArrayList[V+1];
            visited = new boolean[V+1];
            check = new boolean[V+1];
            answer = true;
            for(int j=1; j<=V; j++){
                A[j] = new ArrayList<Integer>();
            }
            //그래프 정보 저장
            for(int j=0; j<E; j++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                A[s].add(e);
                A[e].add(s);
            }
            //그래프가 하나의 덩어리로 되어 있다는 보장이 없으므로 모든 노드에 대해 수행
            for(int j=1; j<=V; j++){
                if(answer)
                    DFS(j);
                else break;
            }
            if(answer)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
    public static void DFS(int node){
        visited[node] = true;
        for(int i:A[node]){
            if(!visited[i]){
                //인접한 노드는 다른 집합에 저장되어야 하므로
                check[i] = check[node] ? false:true;
                DFS(i);
            }
            //방문한 노드가 인접한 노드인데 같은 집합이라면
            else if(check[node] == check[i]){
                answer = false;
            }
        }
    }
}
