class Solution {
    public boolean validMountainArray(int[] arr) {
        if (arr.length<3) return false;
        
        int[] cmp = new int[arr.length-1];
        for (int i=0;i<arr.length-1;i++) {
            if (arr[i]==arr[i+1]) return false;
            else if (arr[i]<arr[i+1]) { // strictly increase
                cmp[i]=1;
            }else {  // strictly decrease
                cmp[i] = -1;
            }
        }
        
        int switchCnt=0;
        for(int i=0;i<cmp.length-1;i++) {
            if(cmp[i]==1 && cmp[i+1]==-1) switchCnt++;
            else if (cmp[i]==-1 && cmp[i+1]==1) return false;
        }
        
        if (switchCnt==1) return true;
        return false;
    }
}