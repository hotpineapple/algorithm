# NUMBER SOLITAIRE
- SCORE 100% 
```java
public int solution(int[] A) {
    int N = A.length;
    int[] dp = new int[N];
    for (int i = 0; i < N; i++) dp[i] = Integer.MIN_A
    dp[0] = A[0];
    
    for (int i = 0; i < N; i++) {
        for (int k = 1; k <= 6; k++) {
            if (i + k >= N) {
                break;
            }
            dp[i + k] = Math.max(dp[i + k], dp[i] + A[i + k]);
        }
    }

    return dp[N - 1];
}

```
