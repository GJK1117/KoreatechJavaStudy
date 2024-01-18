import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17387_선분교차 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int res = 0;        //결과 값입력
        //선분 1
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        //선분 2
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());

        //선분 1과 선분 2 좌표 각각 CCW 연산 수행
        int a = CCW(x1, y1, x2, y2, x3, y3);
        int b = CCW(x1, y1, x2, y2, x4, y4);
        //선분 2와 선분 1 좌표 각각 CCW 연산 수행
        int c = CCW(x3, y3, x4, y4, x1, y1);
        int d = CCW(x3, y3, x4, y4, x2, y2);
        //모두 0이면 두 선분 일직선 상에 존재
        //겹침 여부 판단
        if (a==0 && b==0 && c==0 && d==0){
            if(Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2) && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2)){
                res = 1;
            }
        }
        //모두 음수이면 교차됨
        else if(a*b<=0 && c*d<=0) res = 1;

        System.out.println(res);
    }
    //CCW 연산 수행
    static int CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
        long temp =(x1*y2 + x2*y3 + x3*y1)- (x2*y1 + x3*y2 + x1*y3);
        //0, 음수, 양수만 구별해주어 위치 판단
        if(temp >0) return 1;
        else if(temp < 0) return -1;
        return 0;
    }
}
