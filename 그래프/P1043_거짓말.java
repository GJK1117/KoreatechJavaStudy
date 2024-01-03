import java.io.IOException;
import java.util.*;

public class P1043_거짓말 {
    public static int[] parent;
    public static int[] trueP;
    public static ArrayList<Integer>[] party;
    public static int res;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int T = scanner.nextInt();
        res = 0;
        trueP = new int[T];

        //진실 아는사람 저장
        for(int i=0; i<T; i++) {
            trueP[i] = scanner.nextInt();
        }
        party = new ArrayList[M];

        //파티 데이터 저장
        for(int i=0; i<M; i++){
            party[i] = new ArrayList<>();
            int k = Integer.parseInt(scanner.next());
            for(int j=0; j<k; j++){
                party[i].add(scanner.nextInt());
            }
        }
        parent = new int[N+1];
        for(int i=0; i<=N; i++){    //대표 노드 자기 자신으로 초기화
            parent[i] = i;
        }
        //각 파티에 참여한 사람들을 1개의 그룹으로 만들기
        //각 파티에 참여한 사람들의 value에 대표노드 저장
        for(int i=0; i<M; i++){
            int fPeople = party[i].get(0);
            for(int j=1; j<party[i].size(); j++){
                union(fPeople, party[i].get(j));
            }
        }
        //각 파티 대표 노드와 진실을 아는 사람들의 대표 노드가 같다면 과장할 수 없음
        for(int i=0; i<M; i++){
            boolean isPossible =  true;
            int cur = party[i].get(0);  //현재 파티
            for (int j=0; j<trueP.length; j++){
                //진실을 아는 사람들의 대표 노드와 현재 파티의 대표 노드가 같으면 과장할 수 없음
                if(find(cur) == find(trueP[j])){
                    isPossible = false;
                    break;
                }
            }
            if(isPossible) res++;   //모두 다르면 결과값 1 증가
        }
        System.out.println(res);
    }

    //union 연산
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b){
            parent[b] = a;  //대표노드 다르면 b에 a대표 노드 대입
        }
    }
    //find 연산
    public static int find(int a){
        if (a==parent[a]) return a;     //인덱스와 value 의 값이 같으면 -> 대표노드이므로 리턴
        else return parent[a] = find(parent[a]);    //a 위치의 인덱스와 value가 다르면 대표노드를 찾을때까지 재귀탐색
    }
}
