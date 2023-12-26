package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1167_트리의지름 {
    static ArrayList<Node>[] A;     //ArrayList 배열
    static boolean[] visited;       //방문 여부
    static int[] distance;          //거리(가중치)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //노드의 개수
        A = new ArrayList[N+1];                     //배열 선언
        //배열 초기화
        for(int i=1; i<N+1; i++){
            A[i] = new ArrayList<Node>();
        }
        //그래프 정보 저장
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            while(true){
                int index = Integer.parseInt(st.nextToken());
                if(index == -1)
                    break;
                int dis = Integer.parseInt(st.nextToken());
                A[cur].add(new Node(index, dis));
            }
        }
        /*
        첫 BFS는 알맞은 시작점을 구하기 위한 BFS임
        트리의 지름이란 트리를 구성하는 두 노드의 거리가 가장 긴 것을 말하기 때문에
        가장 먼 정점(바깥에 있는 정점)을 찾아 시작점으로 설정해야 함
         */
        visited = new boolean[N+1];
        distance = new int[N+1];
        //BFS를 수행하면 visited와 distance에 정보가 기록됨
        BFS(1);
        int max = 1;
        //distance가 가장 큰 노드(가장 바깥쪽 노드)찾기
        for(int i=2; i<N+1; i++){
            if(distance[max]<distance[i])
                max = i;
        }
        /*
        가장 바깥쪽 노드를 찾았으니 거기부터 BFS를 수행하면
        트리의 지름을 구할 수 있음
        이하 동일
         */
        visited = new boolean[N+1];
        distance = new int[N+1];
        BFS(max);
        int result = 0;
        for(int i : distance){
            if(result<i)
                result = i;
        }
        System.out.println(result);
    }
    static void BFS(int index){
        //BFS(너비 우선 탐색)을 위한 큐 선언
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(index);
        visited[index] = true;

        while(!que.isEmpty()){
            //현재 탐색중인 값 반환 후 삭제
            int cur = que.poll();
            for(Node i : A[cur]){
                //i.node를 방문하고 distance[i.node]에 거리 기록
                if(!visited[i.node]){
                    visited[i.node] = true;
                    que.add(i.node);
                    distance[i.node] = distance[cur] + i.dis;
                }
            }
        }
    }
}
class Node{
    int node;
    int dis;

    public Node(int node, int dis) {
        this.node = node;
        this.dis = dis;
    }
}