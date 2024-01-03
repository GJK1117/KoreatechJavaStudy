package 정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1747_소수펠린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int A[] = new int[2000001];
        //배열에 숫자 넣기
        for(int i=2; i<A.length; i++){
            A[i] = i;
        }
        //소수 판별
        for(int i=2; i<Math.sqrt(A.length); i++){
            if(A[i] == 0)
                continue;
            for(int j=i+i; j<A.length; j+=i){
                A[j] = 0;
            }
        }
        int index = N;
        //소수가 아닌 수중에 펠린드롬인지 체크
        while(true){
            if(A[index] != 0){
                if(palindrome(index)){
                    System.out.println(index);
                    break;
                }
            }
            index++;
        }
    }
    public static boolean palindrome(int index){
        //펠린드롬 숫자는 앞뒤가 똑같은 숫자이므로
        //char형 배열에 숫자를 넣고
        //앞과 뒤가 같은지 비교함
        char pal[] = String.valueOf(index).toCharArray();
        int s = 0;
        int e = pal.length - 1;
        while(s < e){
            if(pal[s]!=pal[e])
                return false;
            s++;
            e--;
        }
        return true;
    }
}
