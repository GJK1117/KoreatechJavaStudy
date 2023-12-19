import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P1874_스택수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    //n 입력
        int[] arr = new int[n+1];                   //수열 배열
        int[] stack = new int[n+1];                 //0으로 초기화된 배열 -> 스택으로 사용
        List<String> res = new ArrayList<>();       //+,- 를 저장할 결과값 리스트
        int idx = 1, top = 1;                       //수열배열의 인덱스, 스택의 top 인덱스

        //수열 배열 입력 -> 문제에서 주어진 값
        for (int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            stack[i] = 0;
        }
        
        while(idx <= n) {
            //수열 배열의 값이 스택의 top 값보다 큰 경우
            if(arr[idx] > top) {
                if (stack[top] == 0) {
                    res.add("+");       //+ 를 결과값 리스트에 추가
                    stack[top] = top;   //스택에 값 push
                    top++;              //top 증가
                } else {
                    top++;              //스택 자료구조를 배열로 구현하였기 때문에 이미 pop된 경우를 구분해주기 위해 추가
                }
            } else if(arr[idx] == top){ //수열 배열의 값이 스택의 top 값과 같은 경우 -> 원하는 값
                if(stack[top]==0){      //스택에 push 된 값이 없는경우 push 해준다.
                    res.add("+");
                    stack[top] = top;   //스택에 값 push
                } else {
                    res.add("-");       //push 했으므로 다시 pop 해준다.
                    stack[top] = -1;    //이미 pop 된 경우를 구분하기 위해 -1로 표시
                    if(top==1) top = 1; //인덱스 0으로 넘어가지 않기 위해 예외처리
                    else top--;         //pop 해주었으므로 top 감소
                    idx++;              //다음 값 비교위해 수열 배열의 인덱스 증가
                }
            } else{                //수열 배열의 값이 스택의 top 값보다 작은 경우
                if(stack[top]==-1){
                    top--;          //이미 pop 된 경우이므로 top 감소
                } else{
                    //만약 원하는 값 전에 pop 되지 않은 값이 있는 경우 NO 출력 후 종료 -> 이경우에는 원하는 수열을 만들 수 없다.
                    System.out.println("NO");
                    return;
                }
            }
        }
        for (String s : res) {
            System.out.println(s);  //결과값 출력
        }
    }
}
