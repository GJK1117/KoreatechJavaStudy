import java.io.*;
import java.util.*;

public class P17298_오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] s = new int[n];               //입력 원본 배열
        int[] res = new int[n];             //결과값 저장할 배열
        Stack<Integer> nge = new Stack<>(); //오큰수를 찾기 위한 스택 -> 스택에 인덱스를 저장하여 인덱스 값으로 사용

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            s[i] = Integer.parseInt(st.nextToken());    //원본 배열 입력
        }
        //오큰수 찾기
        for(int i=0; i<n; i++){
            //스택이 비어있지 않고 top 의 값이 현재 원본배열의 값보다 작은경우 즉 오큰수를 찾은 경우이므로
            //스택에서 하나씩 pop해주고 결과값 배열에 현재 원본배열의 값을 넣어준다.
            while(!nge.isEmpty() && s[nge.peek()] < s[i]){
                res[nge.pop()] = s[i];
            }
            nge.push(i);    //스택이 비어있거나 오큰수가 아닐경우 push
        }
        //System.out.println()보다 빠른 BufferedWriter 사용
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<n;i++){
            //오큰수가 없는 경우 -1 출력
            if(res[i]!=0) bw.write(res[i]+" ");
            else bw.write(-1+" ");
        }
        bw.write("\n");
        bw.flush();
    }
}
