# 앱
- 냅색 응용
- DP 테이블 행/열과 데이터를 설정하는 데 제한 조건을 고려해야 한다.
- 코드1(메모리초과)
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] m = new int[N+1];
        int[] c = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) m[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int maxCost=0;
        for(int i=1;i<=N;i++) {
            int num = Integer.parseInt(st.nextToken());
            c[i] = num;
            maxCost+=num;
        }

        // DP
        int[][] D = new int[N+1][10000001];
        for (int[] row : D) {
            Arrays.fill(row, -1);
        }
        for(int i=1;i<=N;i++){
            D[i][m[i]]=c[i];
            for(int j=1;j<=M;j++) {
                if(D[i-1][j]>-1) {
                    D[i][j]=D[i-1][j];
                    if(D[i-1][m[i]+j]>-1) {
                        D[i][j+m[i]] = Math.min(D[i-1][j]+c[i],+D[i-1][m[i]+j]);
                    } else {
                        D[i][j+m[i]] = D[i-1][j]+c[i];
                    }
                }
            }
        }

        int ans=maxCost;
        for(int i=M;i<=10000000;i++) {
            if(D[N][i]==-1) continue;
            ans= Math.min(ans,D[N][i]);
        }
        System.out.println(ans);
    }
}
```
- 코드2
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] m = new int[N+1];
        int[] c = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) m[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) c[i] = Integer.parseInt(st.nextToken());


        // DP
        long[][] D = new long[N+1][10001];
        for(int i=1;i<=N;i++){
            D[i][c[i]]=Math.max(D[i-1][c[i]], m[i]); // max 처리 안하면 덮어씌워짐

            for(int j=0;j<=10000;j++) {
                if(D[i-1][j]>0) {
                    D[i][j] = Math.max(D[i-1][j], D[i][j]);
                    D[i][j+c[i]] = Math.max(D[i-1][j] + m[i], D[i-1][j+c[i]]);
                }
            }
        }

        int ans=10000;
        for(int i=0;i<=10000;i++) {
            if(D[N][i]>=M) ans= Math.min(ans,i);
        }

        System.out.println(ans);
    }
}


```
