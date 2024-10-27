class Solution {
    public String addBinary(String a, String b) {
        int i=a.length()-1,j=b.length()-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(true){
            if(i>=0&&j>=0){
                int sum = a.charAt(i)-'0'+b.charAt(j)-'0'+carry;
                sb.insert(0,sum%2);
                carry = sum/2;
            } else if (i<0) {
                while(j>=0) {
                    int sum = b.charAt(j)-'0'+carry;
                    sb.insert(0,sum%2);
                    carry = sum/2;
                    j--;
                }
                break;
            }else {
                while(i>=0) {
                    System.out.println(i+","+carry);
                    int sum = a.charAt(i)-'0'+carry;
                    sb.insert(0,sum%2);
                    carry = sum/2;
                    i--;
                }
                break;
            }
            i--;
            j--;
        }
        if(carry==1) sb.insert(0,1);
        return sb.toString();
    }
}