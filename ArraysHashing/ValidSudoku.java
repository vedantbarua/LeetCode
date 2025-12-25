/**
 * LeetCode 36 - Valid Sudoku
 * Approach: Track seen digits for each row, column, and 3x3 box using boolean arrays.
 * Time: O(1) fixed 9x9 grid
 * Space: O(1) fixed-size tracking
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char cell = board[r][c];
                if (cell == '.') {
                    continue;
                }
                int val = cell - '1';
                int box = (r / 3) * 3 + (c / 3);

                if (rows[r][val] || cols[c][val] || boxes[box][val]) {
                    return false;
                }
                rows[r][val] = cols[c][val] = boxes[box][val] = true;
            }
        }
        return true;
    }
}
