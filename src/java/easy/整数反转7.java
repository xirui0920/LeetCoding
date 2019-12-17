package java.easy;

/**
 * author: ruix
 * date: 2019/12/17 21:31
 * project: LeetCode
 */
public class 整数反转7 {
    /*
    给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

    示例 1:

    输入: 123
    输出: 321
     示例 2:

    输入: -123
    输出: -321
    示例 3:

    输入: 120
    输出: 21
     */
    public int reverse(int x) {
        long rs = 0;
        while(x != 0){
            rs = rs*10+x%10;
            x /= 10;
        }
        return (rs < Integer.MIN_VALUE || rs > Integer.MAX_VALUE) ? 0:(int)rs;
    }
}
