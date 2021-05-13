## 다리를 지나는 트럭

> 큐를 이용하여 주어진 순서대로 트럭의 무게와 다리가 버틸 수 있는 무게를 비교하며 최소시간 구하기

### 어려웠던 점
* 트럭의 위치를 조정하는 것을 구현하는 것이 어려웠다.

### 코드
```
public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        
        int i=1,sum=0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        sum += truck_weights[0];
        //System.out.println("0번 째 트럭 다리에 첫 딛음. 다리위 트럭 무게 : "+sum+",시간:"+answer);
        answer=2;
        OUTER:while(true){
            int size = q.size();
            while(--size >= 0){ //현재 큐에 들어있는 모든 원소에 대하여 반복 = 다리위의 모든 트럭에 대하여 반복
               int[] t = q.poll();
               
                if(t[1] < bridge_length-1){
                    q.offer(new int[]{t[0],t[1]+1});
                    //System.out.println(t[0]+"번 째 트럭 위치 조정. 다리위 트럭 무게 : "+sum+",시간:"+answer);
                
                }else{ //다리를 다 건넌 경우 총 무게에서 빼줌         
                   sum -= truck_weights[t[0]];
                   //System.out.println(t[0]+"번 째 트럭 다리 다 건넘. 다리위 트럭 무게 : "+sum+",시간:"+answer);
                    
                  if(t[0] == truck_weights.length-1) {
                        break OUTER; //마지막 트럭이 다리를 건넌 경우 종료.
                    }
                } 
            }
            
            if(i<truck_weights.length && truck_weights[i]+sum <= weight){
                q.offer(new int[]{i,0});
                sum += truck_weights[i];
                //System.out.println(i+"번 째 트럭 다리에 첫 딛음. 다리위 트럭 무게 : "+sum+",시간:"+answer);
                
                i++;
            }
            
            answer++;
        }
        
        return answer;
    }
