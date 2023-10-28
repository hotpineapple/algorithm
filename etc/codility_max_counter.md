# max counter
- 변수를 적절히 활용하여 시간복잡도 줄이기
```java
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N, int[] A) { 
        int[] ans = new int[N];
        int tempMax = 0;
        int realMax = 0;
        for (int i=0;i<A.length;i++){
            if(A[i] == N+1){
                realMax = tempMax; // 모든 값을 이때의 tempMax로 갱신하면 O(N^2)로 시간효율성 떨어지므로 일단 realMax값만 갱신하고 넘어감
            }else{
                int idx = A[i]-1;
                if (ans[idx] < realMax) { // 이때 이전에 안한 갱신 처리를 같이함, 단 이미 realMax와 같은 값이면 +1만 함
                    ans[idx] = realMax + 1;
                }else{
                    ans[idx]++;
                }
                // tempMax 갱신
                tempMax = Math.max(tempMax,ans[idx]); 
            }
        }

        // 최종적으로 max보다 작은 걊을 처리
        for (int i = 0; i < N; i++){
            if (ans[i] < realMax){
                ans[i] = realMax;
            }
        }
        return ans;
    }
}
```
