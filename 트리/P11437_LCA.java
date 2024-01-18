package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11437_LCA {
    static ArrayList<Integer>[] tree;
    static int N, M;
    static int depth[];
    static int parent[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            tree[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }
        depth = new int[N+1];
        parent = new int[N+1];
        visited = new boolean[N+1];
        BFS(1);     //탐색하며 depth, parent 갱신
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(LCA(a, b));  //a와 b의 공통 조상 출력
        }
    }
    static void BFS(int node){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        visited[node] = true;
        int level = 1;      //깊이
        int size = 1;       //가로 길이
        int count = 0;      //가로로 몇번 탐사했는지 세기 위한 변수
        while (!queue.isEmpty()){
            int cur = queue.poll();
            for(int next: tree[cur]){
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    depth[next] = level;    //깊이 저장
                    parent[next] = cur;     //부모 저장
                }
            }
            count++;        //가로 탐색이 한 번 끝날 때 마다
            if(count == size){          //가로 탐색이 전부 끝났으면
                count = 0;              //횟수 초기화
                size = queue.size();    //큐의 크기가 다음 가로 길이
                level++;                //깊이 변수 증가
            }
        }
    }
    static int LCA(int a, int b){
        if(depth[a] < depth[b]){            //b가 a보다 깊게 있으면
            while(depth[a] != depth[b]){  //높이 맞을때까지
                b = parent[b];              //b의 높이를 올려줌
            }
        }
        else if(depth[a] > depth[b]){       //a가 b보다 깊게 있으면
            while(depth[a] != depth[b]){  //높이 맞을때까지
                a = parent[a];              //a의 높이를 올려줌
            }
        }
        //위 조건문을 거치고 a와 b의 깊이가 같아진 상태임
        while(a != b){      //a와 b가 같아질 때까지 부모 노드로 이동
            a = parent[a];
            b = parent[b];
        }
        //a와 b가 공통 조상이 되었으므로 둘 중 하나 리턴
        return a;
    }
}
