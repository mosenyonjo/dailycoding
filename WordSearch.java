package com.ms.dailycoding;

/*
Given a 2D board of characters and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.

The same letter cell may not be used more than once.

For example, given the following board:

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
exists(board, "ABCCED") returns true, exists(board, "SEE") returns true, exists(board, "ABCB") returns false.
 */

 class WordSearch {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        // Test cases
        System.out.println(exist(board, "ABCCED"));  // true
        System.out.println(exist(board, "SEE"));     // true
        System.out.println(exist(board, "ABCB"));    // false
        System.out.println(exist(board, "ASF"));     // true
        System.out.println(exist(board, "BFCE"));    // true
        System.out.println(exist(board, "ABF"));     // false
        System.out.println(exist(board, "ABCCEF"));  // true
        System.out.println(exist(board, "ABCESEED"));  // true
        System.out.println(exist(board, "ABCCESEE"));  // false
    }

     /*
         Checks if the word exists in the given 2D board.

         Parameters:
             board: 2D array representing the board of characters
             word:  the word to search for

         Returns:
             true if the word exists in the board, false otherwise
         */
       public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return false;

        int rows = board.length;
        int cols = board[0].length;

        // Traverse each cell in the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (search(board, i, j, word, 0))
                    return true;
            }
        }

        return false;
    }

     /*
     Recursively searches for the remaining characters in the word starting from the given cell.

     Parameters:
         board: 2D array representing the board of characters
         row:   row index of the current cell
         col:   column index of the current cell
         word:  the word to search for
         index: current index of the character being searched for in the word

     Returns:
         true if the remaining characters of the word exist in the board, false otherwise
     */
    private static boolean search(char[][] board, int row, int col, String word, int index) {
        // Check if all characters in the word have been found
        if (index == word.length())
            return true;

        int rows = board.length;
        int cols = board[0].length;

        // Check if the current cell is out of bounds or the character doesn't match
        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] != word.charAt(index))
            return false;

        // Mark the current cell as visited by modifying its value
        char temp = board[row][col];
        board[row][col] = '#';

        // Recursively search for the remaining characters in the word
        boolean found = search(board, row + 1, col, word, index + 1) || // Search down
                search(board, row - 1, col, word, index + 1) || // Search up
                search(board, row, col + 1, word, index + 1) || // Search right
                search(board, row, col - 1, word, index + 1);   // Search left

        // Restore the original value of the current cell
        board[row][col] = temp;

        return found;
    }
}