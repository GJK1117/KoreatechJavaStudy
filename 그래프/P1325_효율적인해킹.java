package 그래프;

import java.io.*;
import java.util.*;

public class P1325_효율적인해킹 {
    static int N, M;
    static ArrayList<ArrayList<Integer>> A;
    static boolean visited[];
    static int count[];

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //출력하는 방법 중 하나
        StringBuilder sb = new StringBuilder();
        //노드의 개수
        N = Integer.parseInt(st.nextToken());
        //신뢰관계(간선)의 개수
        M = Integer.parseInt(st.nextToken());
        //N+1번 컴퓨터를 신뢰하는 컴퓨터의 개수
        count = new int[N+1];
        A = new ArrayList<>();
        for(int i=0; i<=N; ++i) A.add(new ArrayList<>());
        //그래프 정보 저장
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            A.get(parent).add(child);
        }
        //BFS 실행(모든 노드에 대해 실행하기 위해 방문 여부 계속 초기화
        for(int i=1; i<=N; i++) {
            visited = new boolean[N + 1];
            BFS(i);
        }

        for(int i=1; i<=N; i++){
            if(count[i] == max)
                sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
    static void BFS(int index){
        //BFS(너비 우선 탐색)을 위한 큐 선언
        ArrayDeque<Integer> que = new ArrayDeque<Integer>();
        que.add(index);
        visited[index] = true;

        while(!que.isEmpty()){
            //현재 탐색중인 값 반환 후 삭제
            int cur = que.poll();
            for(int i : A.get(cur)){
                if(!visited[i]){
                    visited[i] = true;
                    count[i]++;
                    if(max<count[i])
                        max = count[i];
                    que.add(i);
                }
            }
        }
    }
}
