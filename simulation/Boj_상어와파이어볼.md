## 마법사 상어와 파이어볼
#### 어려웠던 점
* 이동 중 한 칸에 여러 개의 파이어볼 있을 수 있는 것
* 리스트 순회 중 삭제 시 발생한 예외
* 이동 후 현재 칸으로 돌아오는 경우 예외처리
* 변수 위치 혼동으로 인한 시간 소요

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
	static int N,M,K,ans;
	static Bomb[][] map;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static class Bomb {
		List<Fireball> list = new ArrayList<>();
		
		Bomb(List<Fireball> list){
			this.list = list;
		}
	}
	static class Fireball {
		int r;
		int c;
		int m;
		int s;
		int d;
		int nr;
		int nc;
		
		Fireball(int r,int c,int m,int s,int d){
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine()," ");
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	map = new Bomb[N][N];

    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Bomb(new ArrayList<>());
			}
		}
    	
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c].list.add(new Fireball(
					r,c,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		
		for (int i = 0; i < K; i++) {
			moveBalls();
			somthing();
		}
		
		ans = count();
		System.out.println(ans);
	}

	private static int count() {
		int sum=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				List<Fireball> temp = map[i][j].list;
				for (int k = 0; k < temp.size(); k++) {
					sum += temp.get(k).m;
				}
			}
		}
		return sum;
	}
	private static void somthing() {

		for (int i = 0; i< N; i++) {
			for (int j = 0; j< N; j++) {
				if(map[i][j].list.size()<=1) {
					continue;
				}
//				System.out.println("something happened at "+i+","+j);
			
				int mSum = 0;
				int sSum = 0;
				int ballCnt = 0;
				int evenCnt = 0;
				int oddCnt = 0;
				List<Fireball> temp = map[i][j].list;
				for (int k = 0; k < temp.size(); k++) {
					mSum += temp.get(k).m;
					sSum += temp.get(k).s;
					ballCnt++;
					if(temp.get(k).d%2==0) evenCnt++;
					else oddCnt++;
				}
				mSum /= 5;
				sSum /= ballCnt;
//				System.out.println("mSum,sSum:"+mSum+" "+sSum);
				map[i][j].list = new ArrayList<>();
				if(mSum==0) {
//					System.out.println("ball disapeared.");
//					map[i][j].list.add(new Fireball(i,j,0,0,0));
//					continue;
				}else {
//					System.out.println("ball created.");
					if(evenCnt==temp.size() || oddCnt==temp.size()) {
						map[i][j].list.add(new Fireball(i,j,mSum,sSum,0));
						map[i][j].list.add(new Fireball(i,j,mSum,sSum,2));
						map[i][j].list.add(new Fireball(i,j,mSum,sSum,4));
						map[i][j].list.add(new Fireball(i,j,mSum,sSum,6));
					
//					System.out.println("last added direction: "+map[i][j].list.get(3).d);
						
					}else {
						map[i][j].list.add(new Fireball(i,j,mSum,sSum,1));
						map[i][j].list.add(new Fireball(i,j,mSum,sSum,3));
						map[i][j].list.add(new Fireball(i,j,mSum,sSum,5));
						map[i][j].list.add(new Fireball(i,j,mSum,sSum,7));
					}
				}
			}
		}
	}
	private static void moveBalls() {
//		System.out.println("move balls");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < map[i][j].list.size(); k++) {

					Fireball fb = map[i][j].list.get(k);
					int d = fb.d;
					int s = fb.s;
					int nr = fb.r + dr[d]*s;
					int nc = fb.c + dc[d]*s;

					if(nr<0) while(nr<0) nr +=N;
					if(nc<0) while(nc<0) nc +=N;
					if(nr>=N) while(nr>=N) nr -=N;
					if(nc>=N) while(nc>=N) nc -=N;
					fb.nr = nr;
					fb.nc = nc;
//					System.out.println("["+i+","+j+"] fireball m,nr,nc:"+fb.m+" "+nr+" "+nc);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				List<Fireball> toRemove = new ArrayList<>();
				int size = map[i][j].list.size();
				for(int k=0;k<size;k++) {
					Fireball fb = map[i][j].list.get(k);
					int nr = fb.nr;
					int nc = fb.nc;
					
					if(nr!=-1 && nc!=-1) {
						if(nr==fb.r && nc==fb.c) continue;
						fb.r = nr;
						fb.c = nc;
						fb.nr = -1;
						fb.nc = -1;
						map[nr][nc].list.add(fb);
//						System.out.println(fb.m+" newly added to "+nr+" "+nc);
						toRemove.add(fb);
					}
				}
				for (int k = 0; k < toRemove.size(); k++) {
//					System.out.println(toRemove.get(k).m+" newly deleted from "+i+" "+j);
				}
				map[i][j].list.removeAll(toRemove);
			}
		}
	}
    
}
```
