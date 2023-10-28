# missing integer
- 배열에 없는 가장 작은 양의정수를 찾는 문제
- 정렬을 활용
```java
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {
        Arrays.sort(A);
        if(A[A.length-1]<=0) return 1;
        int idx = -1;
        for(int i=0;i<A.length;i++){
            if(A[i]>0){
                idx = i;
                break;
            }
        }
        int prev = 0;
        for(int i=idx;i<A.length;i++){
            if(A[i]-prev>1) return prev+1;
            else prev = A[i];
        }
        return prev+1;
    }
}
```
