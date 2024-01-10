package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1219_세일즈맨의고민 {
    static int N, M, sCity, eCity;
    static long distance[], cityMoney[];
    static Edge edges[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       //노드 개수
        sCity = Integer.parseInt(st.nextToken());   //시작 도시
        eCity = Integer.parseInt(st.nextToken());   //도착 도시
        M = Integer.parseInt(st.nextToken());       //간선 개수
        edges = new Edge[M];
        distance = new long[N];
        cityMoney = new long[N];
        Arrays.fill(distance, Long.MIN_VALUE);
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s, e, p);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            cityMoney[i] = Long.parseLong(st.nextToken());
        }

        //벨만-포드 수행
        distance[sCity] = cityMoney[sCity];
        //양수 사이클을 확인하기 위해 도시 개수의 최대치만큼 더 반복해줌
        for(int i=0; i<=N+50; i++){
            for(int j=0; j<M; j++){
                int s = edges[j].start;
                int e = edges[j].end;
                int p = edges[j].price;
                //출발 노드가 한 번도 방문하지 않은 노드이면
                if(distance[s] == Long.MIN_VALUE) continue;
                //출발 노드가 양수 사이클에 연결된 노드이면
                else if(distance[s] == Long.MAX_VALUE)
                    distance[e] = Long.MAX_VALUE;
                //돈을 더 벌 수 있는 경로 발견 시 업데이트
                else if(distance[e] < distance[s]+cityMoney[e]-p){
                    distance[e] = distance[s]+cityMoney[e]-p;
                    //N-1번 반복한 이후에도 돈을 더 벌 수 있는 경로를 발견했다면
                    //양수 사이클인 것이므로 MAX_VALUE로 업데이트
                    if(i >= N-1) distance[e] = Long.MAX_VALUE;
                }
            }
        }
        if(distance[eCity] == Long.MIN_VALUE)
            System.out.println("gg");
        else if(distance[eCity] == Long.MAX_VALUE)
            System.out.println("Gee");
        else
            System.out.println(distance[eCity]);
    }
    static class Edge{
        int start, end, price;

        public Edge(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }
    }
}
