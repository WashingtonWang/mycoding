package arithmetic;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/28 17:50
 * <p>
 * 为了改进查找时间，人们发明了很多字符串查找算法，而今天的主角BM算法（Bob Boyer和J Strother Moore发明，简称BM算法）就是其中的一种。
 * <p>
 * 不同于暴力查找算法的逐个字符对比，BM算法充分使用预处理模式字符串的信息来尽可能跳过更多的字符。在暴力算法中，比较一个字符串都是从首字母开始，
 * 逐个比较下去。一旦发现有不同的字符，就需要从头开始进行下一次比较，就需要将字串中的所有字符一一比较。BM算法的核心思路在于，
 * 文本字符串从左到右检索，模式字符串从右到左检索，当模式字符串的一个字符pattern[j]和文本字符串的字符text[i+j]不匹配时，
 * 那么在模式字符串中查找字符text[i+j]是否存在索引k，使得pattern[k] == text[i+j]，k若存在，k应该为满足条件的最右索引。此时存在三种情景：
 * <p>
 * 情景1：若pattern不存在索引k(0 <= K < M)，使得pattern[k] == text[i+j]，那么text[i, i+j]不可能跟pattern匹配，那么i向右平移j+1。如图1所示。
 * 情景2：若pattern存在索引k(0 <= K < M)，使得pattern[k] == text[i+j]，此时又有两种子情景：
 * 情景2.1：k < j，把text[i+j]对齐pattern[k]，也即把i向右平移j-k。如图2所示。
 * 情景2.2：k > j，显然i不能减少，那么把i加1。如图3所示。
 * <p>
 * <p>
 * 由于不匹配的情况属于大多数，所以一般情况下，BM算法的时间复杂度为O(N/M)，是线性级别的！可以说是非常高效了。
 * 但它需要额外的空间字母表大小R，所以BM算法是以空间换时间的。
 * <p>
 * 作者：安卓大叔
 * 链接：<a href='https://www.jianshu.com/p/9884512c050a'>https://www.jianshu.com/p/9884512c050a</a>
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class BMSearchArith {

    public static void main(String[] args) {
        String text = "iiqooqcjpawancma,di121w23df0sadwang..wxxiaaawwwnagwalll";
        String pattern = "wang";
        int index = search(text, pattern);
        System.out.println("下标是：" + index);
    }

    public static int search(String text, String pattern) {
        int N = text.length();
        int M = pattern.length();

        // 构建right数组
        int R = 256; // 字母表大小
        int[] right = new int[R]; // 记录字母表中的每个字符在模式字符串中出现的最右的索引
        for (int i = 0; i < R; i++)
            right[i] = -1;
        for (int j = 0; j < M; j++)
            right[pattern.charAt(j)] = j;

        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) { // 不匹配的情况
                    skip = j - right[text.charAt(i + j)]; // 这里包括情景1和情景2.1，如果情景1，right[text.charAt(i + j)]为-1
                    if (skip < 1) skip = 1; // 情景2.2
                    break;
                }
            }
            if (skip == 0) return i; // 匹配成功
        }
        return -1; // 未找到匹配
    }

    public static int searchNormal(String text, String pattern) {
        int N = text.length();
        int M = pattern.length();
        for (int i = 0; i < N-M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (text.charAt(i+j) != pattern.charAt(j))
                    break;
            }
            if (j == M) return i;
        }

        return -1;
    }

    public static int sss(String text, String par){
        int n = text.length();
        int m = par.length();
        for (int i = 0; i < n-m; i++){
            int j;
            for (j = 0; j < m; j++){
                if (text.charAt(i+j) != par.charAt(j)){
                    break;
                }
            }
            if (j == m ) return i;
        }
        return -1;
    }
}
