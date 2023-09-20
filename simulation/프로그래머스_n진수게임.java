class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<=30000;i++){
            sb.append(tx(n,i));
        }
        String str = sb.toString();
        // System.out.println(str.length()); // 이 값이 1000 x 100 보다 클 것을 보장해야 함
        sb = new StringBuilder();
        int i=p-1;
        int cnt=0;
        while(cnt<t){
            sb.append(str.charAt(i)+"");
            i+=m;
            cnt++;
        }
        return sb.toString();
    }
    private String tx(int n, int num) {
        StringBuilder sb = new StringBuilder();
        while(true) {
            int rem = num%n;
            sb.insert(0, rem > 9 ? (char)(rem - 10 + 'A') + "" : num%n + "");
            num/=n;
            if(num<n) {
                if(num>0) sb.insert(0,num > 9 ? (char)(num - 10 + 'A') + "" : num + "");
                break;
            }
        }
        return sb.toString();
    }
}
