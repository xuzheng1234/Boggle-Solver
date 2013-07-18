/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boggle;

/**
 *
 * @author zxa
 */
public class Trie {

    private Node root;

    public Trie() {
        root = new Node();
    }

    public boolean prefixExist(String prefix) {
        return prefixExist(prefix, 0, root);
    }

    private boolean prefixExist(String prefix, int index, Node x) {
        if (x == null) {
            return false;
        }
        if (index == prefix.length()) {
            return true;
        }

        int c = prefix.charAt(index) - 'a';

        return prefixExist(prefix, index + 1, x.children[c]);
    }

    public boolean search(String key) {
        return search(key, 0, root);
    }

    private boolean search(String key, int index, Node x) {
        if (x == null) {
            return false;
        }
        if (index == key.length()) {
            return x.word;
        }
        int c = key.charAt(index) - 'a';
        return search(key, index + 1, x.children[c]);
    }

    public void insert(String key) {
        insert(key, 0, root);
    }

    private void insert(String key, int index, Node x) {
        if (index == key.length()) {
            x.word = true;
            return;
        }
        int c = key.charAt(index) - 'a';
        if (x.children[c] == null) {
            x.children[c] = new Node();
        }
        insert(key, index + 1, x.children[c]);
    }

    private class Node {

        boolean word = false;
        Node[] children;

        public Node() {
            children = new Node[26];
        }
    }
}
