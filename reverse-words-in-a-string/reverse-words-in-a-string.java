class Solution {
    public String reverseWords(String s) {
        String[] strArr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=strArr.length-1;i>=0;i--) {
            String str = strArr[i];
            str = str.replaceAll(" ","");
            if(str.equals("")) continue;
            sb.append(str);
            sb.append(" ");
        }
        String str = sb.toString();
        if(str.charAt(str.length()-1)==' ') return str.substring(0,str.length()-1);
        return str;
    }
}