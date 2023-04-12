# Count conforming bitmasks

- 유의할 점: 범위, 비트마스크 연산자, 로직
- 개선할 점: ???

```java
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    static Set<Integer> set = new HashSet<>();
    static boolean[] is0 = new boolean[30];
    public int solution(int A, int B, int C) {
    
        String str = Integer.toBinaryString(A);    
        for(int i=0;i<30;i++){
            if(str.charAt(i)=='0'){
                is0[i] = true;
            }
        }
        getVal(0,str);
        str = Integer.toBinaryString(B);  
         is0 = new boolean[30];  
        for(int i=0;i<30;i++){
            if(str.charAt(i)=='0'){
                is0[i] = true;
            }
        }
        getVal(0,str);
        str = Integer.toBinaryString(C);    
         is0 = new boolean[30];
        for(int i=0;i<30;i++){
            if(str.charAt(i)=='0'){
                is0[i] = true;
            }
        }
        getVal(0,str);
        return set.size();
    }
    private static void getVal(int index, String str){
        if(index==30){
            int num = Integer.parseInt(str,2);
            // System.out.println(num);
            set.add(num);
            return;
        }
        if(is0[index]){
            getVal(index+1,str); 
            String newstr = str.substring(0,index) + '1' + str.substring(index+1,30);
            getVal(index+1,newstr); 
        }else{
            getVal(index+1,str); 
        }
    }
}
```
![image](https://user-images.githubusercontent.com/77835382/231332297-bd079854-04d1-49bc-9ce0-a2d9419ae294.png)
