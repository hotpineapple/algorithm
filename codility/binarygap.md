# Binary Gap

- 유의할 점: N 의 범위
- 자바에서 10진수를 2진수 문자열로 변환하는 메서드
- 최대값 갱신, 길이 카운트 초기화 로직

```java
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N) {
        // Implement your solution here
        String str = Integer.toBinaryString(N);
        int len = str.length();
        int max = 0;
        int cnt = 0;
        for(int i=0;i<len;i++) {
            if(str.charAt(i)=='1') {
                max = Math.max(max,cnt);
                cnt = 0;
            }else {
                cnt++;
            }
        }
        return max;
    }
}
```
![image](https://user-images.githubusercontent.com/77835382/231318388-acb00260-794f-493c-ad95-792762f6cfd5.png)
