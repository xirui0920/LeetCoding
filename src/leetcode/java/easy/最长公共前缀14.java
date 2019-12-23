package leetcode.java.easy;

/**
 * author: ruix
 * date: 2019/12/23 22:10
 * project: LeetCode
 */
public class 最长公共前缀14 {

    /*
    编写一个函数来查找字符串数组中的最长公共前缀。
    如果不存在公共前缀，返回空字符串 ""。

    示例 1:

    输入: ["flower","flow","flight"]
    输出: "fl"
    示例 2:

    输入: ["dog","racecar","car"]
    输出: ""
    解释: 输入不存在公共前缀。
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder res = new StringBuilder();
        int n = 1;
        int min = Integer.MAX_VALUE;
        while(true){
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
                String sub = str.substring(n-1, n);
                if (t != null) {
                    if (t.equals(sub)) {
                        t = sub;
                    } else {
                        t = "";
                    }
                }else {
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

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void main(String[] args) {
        最长公共前缀14 test = new 最长公共前缀14();
        String[] ss = {"aca","cba",""};
        System.out.println(test.longestCommonPrefix(ss));
    }

}
