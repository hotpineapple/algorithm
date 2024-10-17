class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0,j=0;
        int[] res = new int[nums1.length];
        for (int k=0;k<m+n;k++) {
            if(i<m && j<n ){
                if(nums1[i]<nums2[j]) {
                    res[k] = nums1[i]; 
                    i++;
                }else {
                   res[k] = nums2[j]; 
                    j++;
                }
            }else if(j<n){
                while(j<n){ 
                    res[k++]=nums2[j++];
                }
                break;
            }else if(i<m){
                while(i<m){
                    res[k++]=nums1[i++];
                }
                break;
            }
        }
        for(int k=0;k<nums1.length;k++){
            nums1[k] = res[k];
        }
    }
}