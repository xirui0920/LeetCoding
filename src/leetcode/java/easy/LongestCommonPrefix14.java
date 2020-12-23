package leetcode.java.easy;

import leetcode.java.util.Trie;

/**
 * author: ruix
 * date: 2019/12/23 22:10
 * project: LeetCode
 *     编写一个函数来查找字符串数组中的最长公共前缀。
 *     如果不存在公共前缀，返回空字符串""。
 *
 *     示例:
 *
 *     输入: ["flower","flow","flight"]
 *     输出: "fl"
 *     示例:
 *
 *     输入: ["dog","racecar","car"]
 *     输出: ""
 *     解释: 输入不存在公共前缀。
 */
public class LongestCommonPrefix14 {

    /**
     * 纵向扫描
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder res = new StringBuilder();
        int n = 1;
        int min = Integer.MAX_VALUE;
        while (true) {
            String t = null;
            for (String str : strs) {
                if (str.length() == 0) {
                    t = null;
                    min = 1;
                    break;
                }
                if (str.length() < min) {
                    min = str.length();
                }
                String sub = str.substring(n - 1, n);
                if (t != null) {
                    if (t.equals(sub)) {
                        t = sub;
                    } else {
                        t = "";
                    }
                } else {
                    t = sub;
                }
            }
            if (t == null || t.length() == 0) {
                break;
            }
            res.append(t);
            if (res.length() != n) {
                res = new StringBuilder();
                break;
            }
            if (n >= min) {
                break;
            }
            n++;
        }
        return res.toString();
    }

    /**
     * 水平扫描
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     * 分治
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String lcpLeft = longestCommonPrefix(strs, l, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }

    /**
     * 二分
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 前缀树
     */
    public String longestCommonPrefix5(String q, String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        Trie trie = new Trie();
        for (int i = 1; i < strs.length; i++) {
            trie.insert(strs[i]);
        }
        return trie.searchLongestPrefix(q);
    }

    public static void main(String[] args) {
        LongestCommonPrefix14 test = new LongestCommonPrefix14();
        String[] ss = {"aca", "cba", ""};
        System.out.println(test.longestCommonPrefix(ss));
    }

}
