import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();   //수식을 하나의 문자열로 입력
        String[] ss = s.split("-"); //"-"로 문자열 분리
        int res = 0;    //결과 값

        for(int i=0; i<ss.length; i++){
            int n = 0;  //"+"로 분리한 각각의 배열의 모든 값을 저장할 변수
            String[] sss = ss[i].split("\\+");  //"+"로 문자열 분리
            for (String num : sss) {
                n += Integer.parseInt(num);     //분리된 각각의 값을 모두 합함
            }
            //처음 숫자의 합을 제외하고 모두 - 로 묶여있기 때문에 첫 경우 제외하고 모두 결과 값에서 빼줌
            res += (i==0) ? n : (-n);
        }
        System.out.println(res);
    }
}
