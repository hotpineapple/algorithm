class Solution {
    public boolean validMountainArray(int[] arr) {
        if (arr.length<3) return false;
        
        boolean flag= false;
        int status = 0; // -1 for decreasing, 1 for increasing, 0 for no difference
        for (int i=0;i<arr.length-1;i++) {
            if (arr[i]==arr[i+1]) return false;
            else if (arr[i]<arr[i+1]) {
                if (status==-1) return false;
                status = 1;
            }else {
                if(status==1) flag = true;
                status = -1;
            }
        }
        if ( flag && status==-1) return true;
        return false;
    }
}