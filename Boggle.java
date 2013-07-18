/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boggle;

import java.util.*;

/**
 *
 * @author zxa
 */
public class Boggle {

    /**
    http://en.wikipedia.org/wiki/Boggle
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Trie dict = new Trie();
        dict.insert("zheng");
        dict.insert("xu");
        dict.insert("abr");

        char[][] board = {{'a', 'b', 'z'}, {'c', 'h', 'e'}, {'u', 'x', 'n'}, {'a', 'k', 'g'}};
        ArrayList<String> result = boggle(board, dict);
        System.out.println(result.toString());
    }
    //find all words in the board

    public static ArrayList<String> boggle(char[][] board, Trie dict) {
        ArrayList<String> result = new ArrayList<String>();
        boolean[][] history = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boggle(history, board, result, i, j, "", dict);
            }
        }

        return result;
    }

    private static void boggle(boolean[][] history, char[][] board, ArrayList<String> result, int row, int col, String word, Trie dict) {

        history[row][col] = true;
        word = word + board[row][col];
        if (dict.prefixExist(word) == false) {
            history[row][col] = false;
            return;
        }

        if (dict.search(word)) {
            result.add(word);
        }
        if (row - 1 >= 0 && history[row - 1][col] == false) {
            boggle(history, board, result, row - 1, col, word, dict);
        }
        if (row + 1 < board.length && history[row + 1][col] == false) {
            boggle(history, board, result, row + 1, col, word, dict);
        }
        if (col - 1 >= 0 && history[row][col - 1] == false) {
            boggle(history, board, result, row, col - 1, word, dict);
        }
        if (col + 1 < board[0].length && history[row][col + 1] == false) {
            boggle(history, board, result, row, col + 1, word, dict);
        }


        if (row - 1 >= 0 && col - 1 >= 0 && history[row - 1][col - 1] == false) {
            boggle(history, board, result, row - 1, col - 1, word, dict);
        }
        if (row - 1 >= 0 && col + 1 < board[0].length && history[row - 1][col + 1] == false) {
            boggle(history, board, result, row - 1, col + 1, word, dict);
        }
        if (row + 1 < board.length && col - 1 >= 0 && history[row + 1][col - 1] == false) {
            boggle(history, board, result, row + 1, col - 1, word, dict);
        }
        if (row + 1 < board.length && col + 1 < board[0].length && history[row + 1][col + 1] == false) {
            boggle(history, board, result, row + 1, col + 1, word, dict);
        }
        history[row][col] = false;
    }
}
