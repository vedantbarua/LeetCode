/**
 * Number of Islands
 * DS: grid char[][] with boolean visited.
 * Algo: DFS flood fill from each unseen land cell.
 * Thought: treat grid as implicit graph; every DFS marks one island.
 * Time: O(mn). Space: O(mn) visited + recursion stack.
 */
public class NumberOfIslands {
    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        boolean[][] seen = new boolean[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1' && !seen[r][c]) {
                    dfs(grid, seen, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] g, boolean[][] seen, int r, int c) {
        int m = g.length, n = g[0].length;
        if (r < 0 || c < 0 || r >= m || c >= n || g[r][c] != '1' || seen[r][c]) return;
        seen[r][c] = true;
        for (int[] d : DIRS) dfs(g, seen, r + d[0], c + d[1]);
    }

    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(solver.numIslands(grid)); // 3
    }
}
