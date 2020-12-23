package leetcode.java.easy;

import java.util.HashMap;

/**
 * @author: Cyrus
 * @date: 2020/12/21 4:55 下午
 * @description:
 * 给定一种规律 pattern和一个字符串str，判断 str 是否遵循相同的规律。
 *
 * 这里的遵循指完全匹配，例如，pattern里的每个字母和字符串str中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 */
public class WordPattern290 {

    public static void main(String[] args) {
        WordPattern290 main = new WordPattern290();
        System.out.println(main.wordPattern("abba", "dog dog dog dog"));
    }

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.getBytes().length != words.length) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>(100);
        char[] chars = pattern.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) != null && !map.get(chars[i]).equals(words[i])) {
                return false;
            }
            if (map.get(chars[i]) == null && map.containsValue(words[i])) {
                return false;
            }
            map.put(chars[i], words[i]);
        }
        return true;
    }

}
