class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right-left+1)];
        
        int idx = 0;
        for(long i=left;i<=right;i++){
            // left 가 몇번째 그룹인지 확인. ex) 1이면 1이하를 2로 대체, 2면 2이하를3으로 대체 ..
            long q = i/n;
            // 그리고 그 그룹의 몇번째 요소인지 확인. ex) 1부터 몇번째
            long r = i%n; 
            long num = r+1;
            if(r<=q) num = q+1;
            // System.out.println(q+","+r+","+num);
            answer[idx++] = (int)num;
        }
        return answer;
    }
}
