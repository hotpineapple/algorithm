# MinMaxDivision
- 유형: 이분탐색 + 파라메트릭 서치
- 어려운 점: 유형 찾기
- 유의할 점: 히든 + 엣지 케이스
```java
// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int K, int M, int[] A) {
        long l = 0; long r = Integer.MAX_VALUE; long ans = 0;
        while(l<=r) {
            long mid = (l+r)/2;
            if(isMinLargeSumPossible(mid, K, A)) { // 더 작을 수도 있음 
                ans = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return (int)ans;
    }
    private boolean isMinLargeSumPossible(long num, int K, int[] A) {
        int idx=0; // 블록 개수
        int tmp=0; // 블록 내 합
        for(int i=0;i<A.length;i++){   
            if(A[i]>num) return false; // 유의   
            tmp += A[i];
            // if(num==7) System.out.println("tmp:"+tmp);
            if(tmp > num) {
                idx++;
                tmp = A[i];
            } else if(tmp == num){ 
                idx++;
                tmp = 0;
            }
        }
        if(tmp>0)idx++; // 유의

        if(idx>K) return false; // 이 숫자보다 커야함
        else return true;
    }
}

```
