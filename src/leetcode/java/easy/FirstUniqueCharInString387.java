package leetcode.java.easy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: Cyrus
 * @date: 2020/12/23 4:29 下午
 * @description: 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 *
 * <p>
 * 示例：
 * <p>
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 *
 * <p>
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class FirstUniqueCharInString387 {

    public static void main(String[] args) {
        FirstUniqueCharInString387 test = new FirstUniqueCharInString387();
        System.out.println(test.firstUniqChar1("leetcode"));
    }

    /**
     * 我的实现类似于2遍遍历,第二遍找第一个出现次数为1的索引
     * 遍历字符串，哈希表统计字符出现次数。再遍历字符串，找出现次数为1的字符
     */
    public int firstUniqChar(String s) {
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>(len);
        HashSet<Character> repeat = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char p = s.charAt(i);
            if (repeat.contains(p)) {
                continue;
            }
            if (map.containsKey(p)) {
                map.entrySet().removeIf(a -> a.getKey() == p);
                repeat.add(p);
            } else {
                map.put(p, i);
            }
        }
        if (map.isEmpty()) {
            return -1;
        }
       return map.entrySet().stream().min(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return 1;
                } else if (o1.getValue().equals(o2.getValue())) {
                    return 0;
                } else {
                    return -1;
                }
            }
        }).get().getValue();
    }

    /**
     * 贪心实现
     * 遍历26个字母，找出所有唯一字符：即 首次出现索引 = 最后出现索引 的字符
     * 贪心策略：找唯一字符最前的，即 索引最小的
     */
    public int firstUniqChar1(String s) {
        int min = Integer.MAX_VALUE;
        for (int i = 97; i < 97 + 26; i++) {
            char a = (char) i;
            int j = s.indexOf(a);
            if (j > -1 && j == s.lastIndexOf(a) && j < min) {
                min = j;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 1.遍历字符串，字符首次出现值设索引。再出现值设-1。遍历哈希表，找值!=-1字符
     * 2.可以通过回调函数指定条件，不断弹出符合条件的第0项。直到第0项不符合条件
     * 遍历字符
     * 未出现：放入懒队列
     * 出现过：清理懒队列
     * 结果只关心第0项。所以懒清理，只要清理到第0项不是重复的就收工
     * 是不是重复，判断结果通过回调函数传入懒队列的clear，重复返回true
     * class LazyQueen {
     *     constructor() {
     *         this.q = []
     *     }
     *     length() {
     *         return this.q.length
     *     }
     *     add(first, second) {// 以二维数组的形式，将第一参数，第二参数放入队列
     *         return this.q.push([first, second])
     *     }
     *     first() {
     *         return this.q[0][0]
     *     }
     *     second() {
     *         return this.q[0][1]
     *     }
     *     shift() {
     *         return this.q.shift()
     *     }
     *     clear(cb = new Function) {// 传入回调函数。调用时传入第一，第二参数。返回true，从队列弹出
     *         while(this.length() && cb(this.first(), this.second())) this.shift()
     *     }
     * }
     * var firstUniqChar = function(s) {
     *     let q = new LazyQueen, h = new Uint16Array(26), i = -1
     *     while(++i < s.length)
     *         h[s.charCodeAt(i) - 97]++ ? q.clear(first => h[first] > 1)
     *                                   : q.add(s.charCodeAt(i) - 97, i)
     *     return q.length() ? q.second() : -1
     * };
     */
}
