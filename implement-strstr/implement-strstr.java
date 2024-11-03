class Solution {
    public int strStr(String haystack, String needle) {
        char ch = needle.charAt(0);
        for(int i=0;i<haystack.length();i++) {
            int j=0;
            for(;j<needle.length();j++) {
                if (i+j>=haystack.length() || haystack.charAt(i+j) != needle.charAt(j)) break;
            }
            if (j == needle.length()) return i;
        }
        return -1;
    }
}