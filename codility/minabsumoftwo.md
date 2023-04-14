# minabsumoftwo
- 유형: 투포인터? 캐터필러 메서드
- 유의할점: 없음
- 
```java
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {
        Arrays.sort(A);
        int left=0; int right = A.length-1;int minabsum=Integer.MAX_VALUE;
        while(left<=right){
            int temp1 = A[left]+A[right];
            int temp2 =Math.abs(temp1);
            if(temp1<0){
                left++;
            }else if(temp1>0){
                right--;
            }else{
               return 0;
            }
            minabsum=Math.min(minabsum,temp2);
        }
        return minabsum==Integer.MAX_VALUE?0:minabsum;    
    }
}

```
