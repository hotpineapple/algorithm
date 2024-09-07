class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int er = entrance[0], ec = entrance[1];
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vst = new boolean[maze.length][maze[0].length];
        q.offer(entrance);
        vst[entrance[0]][entrance[1]] = true;
        int len = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1];
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr >= maze.length || nr < 0 || nc >= maze[0].length || nc < 0 || vst[nr][nc]
                            || maze[nr][nc] == '+')
                        continue;
                    if (!(nr == er && nc == ec) && (nr == maze.length - 1 || nr == 0 || nc == maze[0].length - 1 || nc == 0))
                        return len+1;
                    q.offer(new int[] { nr, nc });
                    vst[nr][nc] = true;
                }
            }
            len++;
        }
        return -1;
    }

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, -1, 0, 1 };
}