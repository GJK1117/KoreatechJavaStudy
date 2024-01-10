package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1414_불우이웃돕기 {
    static int N, total;
    static int head[];
    static PriorityQueue<Edge> A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new PriorityQueue<>();
        //랜선 정보 받아오기
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            //알파벳 char형 배열로 저장
            char[] c = st.nextToken().toCharArray();
            for(int j=0; j<N; j++){
                int num = 0;
                if(c[j]>='a' && c[j]<='z')
                    num = c[j] - 'a' + 1;
                else if(c[j]>='A' && c[j]<='Z')
                    num = c[j] - 'A' + 27;
                //총 랜선 길이
                total += num;
                //우선순위 큐에 랜선 정보 저장
                if(i!=j && num !=0)
                    A.add(new Edge(i, j, num));
            }
        }
        head = new int[N];
        for(int i=0; i<N; i++)
            head[i] = i;
        int useTotal = 0;
        int min = 0;
        while (!A.isEmpty()){
            //랜선 길이가 최소인 값이 저장됨
            Edge cur = A.poll();
            //부모가 다르다면(사이클이 없을 때)
            if(find(cur.s) != find(cur.e)) {
                union(cur.s, cur.e);
                min += cur.v;
                useTotal++;
            }
        }
        if(useTotal == N-1)
            System.out.println(total - min);
        else
            System.out.println(-1);
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
    static class Edge implements Comparable<Edge>{
        int s, e, v;
        public Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
        @Override
        public int compareTo(Edge o){
            return this.v - o.v;
        }
    }
}

