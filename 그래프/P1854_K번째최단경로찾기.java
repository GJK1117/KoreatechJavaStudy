package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1854_K번째최단경로찾기 {
    static int[][] A;       //그래프 정보
    static PriorityQueue<Integer>[] distance;   //정답 우선순위 큐
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        //우선순위 큐 초기화
        distance = new PriorityQueue[N+1];
        for(int i=1; i<N+1; i++){
            distance[i] = new PriorityQueue<Integer>();
        }
        //그래프 정보 저장
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            A[s][e] = dis;
        }
        dijkstra();
        for(int i=1; i<N+1; i++){
            if(distance[i].size() == K){
                //다익스트라 함수 내에서 내림차순 정렬을 위해 음수로 저장했으므로
                System.out.println(-distance[i].peek());
            }
            else
                System.out.println(-1);
        }
    }
    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(1, 0));
        distance[1].add(0);
        while(!pq.isEmpty()){
            int curN = pq.peek().node;
            int curD = pq.peek().dis;
            pq.poll();
            for(int i=1; i<N+1; i++){
                if(A[curN][i]!=0){
                    int d = A[curN][i] + curD;
                    //현재까지 저장된 거리가 K개 미만이면
                    if(distance[i].size() < K){
                        //내림차순 정렬을 위해 음수로 저장(K번째를 맨 앞에 두고 peek해야하기 때문)
                        distance[i].add(-d);
                        pq.add(new Node(i, d));
                    }
                    //현재까지 저장된 거리가 K개 이상이고 새로운 거리가 K보다 작으면
                    else if(-distance[i].peek() > d){
                        //원래 거리중에 제일 큰 값은 지우고
                        distance[i].poll();
                        //새로운 거리 입력
                        distance[i].add(-d);
                        pq.add(new Node(i, d));
                    }
                }
            }
        }
    }
    static class Node implements Comparable<Node>{
        int node;
        int dis;
        public Node(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }
        @Override
        public int compareTo(Node o){
            return this.dis < o.dis ? -1 : 1;
        }
    }
}