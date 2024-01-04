import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1033_칵테일 {
    // 인접리스트, 각 재료간의 비율을 관계로 저장하는 리스트
    static ArrayList<cNode>[] A;
    // 최소 공배수의 총 곱을 저장하는 볍ㄴ수
    static long lcm;
    // 방문 여부 배열
    static boolean[] visited;
    // 결과 배열
    static long[] D;

    public static void main(String[] args) throws IOException {
        // 버퍼입력, 출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 재료의 수 입력
        int N = Integer.parseInt(st.nextToken());
        // 인접리스트, 방문 여부 배열, 결과 배열 초기화
        A = new ArrayList[N];
        visited = new boolean[N];
        D = new long[N];
        // lcm의 초기 값은 1, 곱을 구하기 위해 1로 초기화
        lcm = 1;
        // 인접리스트 ArrayList 초기화
        for(int i = 0; i < N; i++){
            A[i] = new ArrayList<>();
        }

        // 재료의 수 - 1만큼 반복
        for(int i = 0; i < N - 1; i++){
            // a, b, p, q 입력
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            // a, b의 관계를 인접리스트에 저장
            // a -> b 에는 p : q의 관계 저장, b -> a 에는 q : p의 관계 저장
            A[a].add(new cNode(b, p, q));
            A[b].add(new cNode(a, q, p));
            // p, q의 최소 공배수를 lcm에 곱함
            lcm *= (p * q / gcd(p, q));
        }
        // D[0]의 초기 값은 계산된 lcm
        D[0] = lcm;
        // 0번 재료부터 DFS 수행
        DFS(0);
        // DFS 계산 결과들의 최대공약수를 구함, 초기 값은 D[0]
        long mgcd = D[0];
        // 최대공약수를 구함
        for(int i = 1; i < N; i++){
            mgcd = gcd(mgcd, D[i]);
        }
        // DFS 결과에 mgcd를 나눈 결과를 버퍼에 입력
        for(int i = 0; i < N; i++){
            bw.write(D[i] / mgcd + " ");
        }
        // 버퍼의 데이터 출력
        bw.flush();
    }

    // 최대공약수를 구하는 함수, 유클리드 호제법 사용
    public static long gcd(long a, long b){
        if(b == 0) return a;
        else return gcd(b, a % b);
    }

    // DFS 함수
    public static void DFS(int Node){
        visited[Node] = true;
        for(cNode i : A[Node]){
            int next = i.getB();
            if(!visited[next]){
                // 다음 위치의 값을 현재 위치의 값에 비율을 곱한 값으로 저장
                D[next] = D[Node] * i.getQ() / i.getP();
                DFS(next);
            }
        }
    }
}

// 인접리스트에 저장할 클래스
class cNode{
    // 현재 위치와 연결된 다음 위치, 비율을 저장하는 변수, a : b = p : q
    int b;
    int p;
    int q;
    // 생성자
    public cNode(int b, int p, int q){
        super();
        this.b = b;
        this.p = p;
        this.q = q;
    }

    // 각 변수의 getter
    public int getB() {
        return b;
    }
    public int getP() {
        return p;
    }
    public int getQ() {
        return q;
    }
}
