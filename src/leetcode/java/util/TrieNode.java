package leetcode.java.util;

/**
 * @author cyrus
 */
public class TrieNode {

    /**
     * 子节点的链接数组
     */
    private TrieNode[] links;

    private final int R = 26;

    private boolean isEnd;

    /**
     * 非空子节点的数量
     */
    private int size;

    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
        size++;
    }

    public int getLinks() {
        return size;
    }

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch -'a'];
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}