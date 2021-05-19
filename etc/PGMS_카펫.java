class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int sum = brown + yellow;
        int w=0,h=0;

        for (int i = 1; i <= yellow; i++) {
          if (yellow % i == 0) {
            h = i;
            w = yellow / h;

            if((w+2)*(h+2) == sum) {
              answer[0]= w+2;
              answer[1]= h+2;
              break;
            }
          }
        }
        return answer;
        }
}
