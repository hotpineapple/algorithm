class Solution {
    static int[][] a;
    static int zero, one;
    public int[] solution(int[][] arr) {
        a=arr;
        go(0,0,arr.length);
        return new int[]{zero, one};
    }
    private void go(int r, int c, int size){
        if(size==1) {
            if(a[r][c]==1) one++;
            else zero++;
            return;
        }
        if(isDiff(r,c,size)){
            go(r,c,size/2);
            go(r+size/2,c,size/2);
            go(r,c+size/2,size/2);
            go(r+size/2,c+size/2,size/2);
        }else{
            if(a[r][c]==1) one++;
            else zero++;
        }
    }
    private boolean isDiff(int r, int c, int size){
        int prev=-1;
        for(int i=r;i<r+size;i++){
            for(int j=c;j<c+size;j++){
                if(prev==-1) {
                    prev = a[i][j];
                    continue;
                }
                if(a[i][j] != prev) {
                    return true;
                } else prev = a[i][j];
                
            }
        }
        return false;
    }
}
