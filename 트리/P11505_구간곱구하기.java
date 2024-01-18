package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11505_구간곱구하기 {
    static int N, M, K;
    static long[] tree;
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int height = 0;     //트리의 높이
        //트리의 높이는 2^x >= N일 때 x
        for(int i=N; i>0; i/=2){
            height++;
        }
        int size = (int)Math.pow(2, height+1);      //트리의 길이
        int startIndex = size / 2;                  //리프 노드의 시작 인덱스
        tree = new long[size];
        //곱을 구해야 하므로 0이 아닌 1로 초기화해줌
        for(int i=0; i<tree.length; i++){
            tree[i] = 1;
        }
        //리프 노드에 트리 입력받기
        for(int i=startIndex; i<startIndex+N; i++){
            tree[i] = Long.parseLong(br.readLine());
        }
        setTree(size-1);        //부모 노드들의 값 설정
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int opr = Integer.parseInt(st.nextToken()); //1일 경우 값 갱신, 2일 경우 곱 출력
            //opr이 1일 경우 s번째 숫자를 e로 갱신
            //opr이 2일 경우 s번째 숫자부터 e번째 숫자의 구간곱 출력
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(opr == 1){
                changeVal(startIndex-1 + s, e);   //갱신
            }
            else if(opr == 2){
                s += startIndex - 1;
                e += startIndex - 1;
                System.out.println(getMul(s, e));       //구간곱 출력
            }
        }
    }
    /*
        i/2는 부모 노드이므로 i/2번 노드에 자식을 곱해줌
        i가 줄어들면서 계속 각각의 부모 노드에 자식을 곱해줌
        구하고자 하는 값은 구간곱을 MOD로 나눈 나머지이기 때문에
        연산 중간에 MOD연산을 계속 수행해줌
    */
    static void setTree(int i){
        while(i != 1){
            tree[i/2] = tree[i/2] * tree[i] % MOD;
            i--;
        }
    }
    static void changeVal(int index, long val){
        tree[index] = val;      //리프 노드의 값 갱신 후
        while(index > 1){       //부모 노드의 값들 차례로 타고 올라가며 갱신
            index /= 2;
            tree[index] = (tree[index*2] * tree[index*2+1]) % MOD;
        }
    }
    static long getMul(int s, int e){
        long result = 1;        //결과 저장할 변수
        while(s <= e){          //s가 e와 같아지기 전까지 연산 수행
            //s가 오른쪽 자식이면 s 선택 후 옆으로 이동
            if(s % 2 == 1){
                result = result * tree[s] % MOD;
                s++;
            }
            //e가 왼쪽 자식이면 e 선택 후 옆으로 이동
            if(e % 2 == 0){
                result = result * tree[e] % MOD;
                e--;
            }
            s /= 2;     //s가 왼쪽 자식이면 부모 노드로 이동
            e /= 2;     //e가 오른쪽 자식이면 부모 노드로 이동
        }
        return result;
    }
}