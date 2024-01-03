package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1717_집합표현하기 {
    static int head[];      //대표 노드
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        head = new int[N+1];
        //대표 노드 자기 자신으로 초기화
        for(int i=0; i<=N; i++){
            head[i] = i;
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int opr = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //합집합(union) 연산
            if(opr == 0)
                union(a, b);
            //합집합 여부 확인(find) 연산
            else{
                if(find(a) == find(b))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }

        }
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
