package 기하;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2162_선분그룹 {
    static int N;
    static int line[][];
    static int head[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        line = new int[N+1][4];
        head = new int[N+1];
        for(int i=1; i<N+1; i++){       //집합의 요소 개수를 대표 노드에 저장해주기 위해 -1로 매핑
            head[i] = -1;
        }
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
            line[i][2] = Integer.parseInt(st.nextToken());
            line[i][3] = Integer.parseInt(st.nextToken());
            for(int j=1; j<i; j++){     //선분이 서로 교차하면 union
                if(isCross(line[i][0], line[i][1], line[i][2], line[i][3],
                        line[j][0], line[j][1], line[j][2], line[j][3]))
                    union(i, j);
            }
        }
        int count = 0;
        int max = 0;
        for(int i=1; i<N+1; i++){
            if(head[i] < 0) {           //head가 음수인 경우 대표 노드이므로 집합 개수로 카운트해줌
                count++;
                //대표 노드의 head값은 집합 요소의 개수이므로 min을 사용해 가장 작은 값이 최대 개수를 가진 집합임
                max = Math.min(max, head[i]);
            }
        }

        System.out.println(count);
        System.out.println(-max);       //양수로 바꾸어 개수 출력
    }
    //3개의 점을 비교해 일직선인지, 특정 방향으로 이어져있는지 반환하는 함수
    static int CCW(int x1, int y1, int x2, int y2, int x3, int y3){
        int temp = (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3);
        if(temp < 0)
            return -1;
        else if(temp > 0)
            return 1;
        else
            return 0;
    }
    //선분이 일직선일 때 겹치는 부분이 있는지 떨어져 있는지 비교하는 함수
    static boolean isOverlab(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        if(Math.min(x1, x2)<=Math.max(x3, x4) && Math.min(x3, x4)<=Math.max(x1, x2)
                && Math.min(y1, y2)<=Math.max(y3, y4) && Math.min(y3, y4)<=Math.max(y1, y2))
            return true;
        else
            return false;
    }
    //선분이 교차되어 있는지 비교하는 함수
    static boolean isCross(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        int abc = CCW(x1, y1, x2, y2, x3, y3);
        int abd = CCW(x1, y1, x2, y2, x4, y4);
        int cda = CCW(x3, y3, x4, y4, x1, y1);
        int cdb = CCW(x3, y3, x4, y4, x2, y2);
        if(abc*abd==0 && cda*cdb==0)        //선분이 일직선이라면
            return isOverlab(x1, y1, x2, y2, x3, y3, x4, y4);   //겹치는지 떨어져 있는지 체크
        else if(abc*abd<=0 && cda*cdb<=0)   //일직선이 아니고 교차되어 있다면
            return true;
        else                                //선분이 교차되어 있지 않고 만나지도 않는다면
            return false;
    }
    static void union(int a, int b){
        int p = find(a);
        int q = find(b);
        if(p == q)          //루트 노드가 같다면
            return;
        //-1로 매핑된 값을 더해주는 과정을 통해 p의 대표노드에 p집합의 개수를 세줄 수 있음
        head[p] += head[q];
        //q의 대표노드에 p를 대입해 대표 노드를 기록함(head가 양수가 됨)(head가 양수이면 대표 노드가 아닌 것)
        head[q] = p;
    }
    static int find(int a){
        if(head[a] < 0)     //-1로 매핑해놨으므로 집합의 개수가 음수라면 대표 노드임
            return a;
        else
            return head[a] = find(head[a]); //재귀를 통한 find연산
    }
}