package arithmetic;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/28 17:55
 *
 * 为了改进查找时间，人们发明了很多字符串查找算法，而今天的主角KMP算法（D.E.Knuth，J.H.Morris和V.R.Pratt发明，简称KMP算法）就是其中的一种。
 *
 * 暴力查找之所以慢是因为它每次的匹配都是从头开始，并且抛弃了之前已经算好的结果，KMP算法就是从这入手：匹配失败后，
 * 已知晓的一部分文本字符串的内容信息，再利用这些内容信息避免指针回退到所有这些已知的字符之前，也即减少模式字符串与文本字符串的匹配次数以达到快速匹配的目的。
 *
 * 作者：安卓大叔
 * 链接：<a href='https://www.jianshu.com/p/f65cae7e00ef'>https://www.jianshu.com/p/f65cae7e00ef</a>
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 * 写在后面
 * KMP算法要求模式字符串具有重复度高的字符才能发挥强大的功力，但实际应用中这种场景并不多，学了似乎没什么用？其实我在学习了一些算法后发现，
 * 算法总是有利也有弊，牺牲时间换空间，或者牺牲空间换时间，总也不能十全十美，我们需要的是厚积薄发，在实际应用中遇到类似的场景，
 * 能想到有这么一种思路可以解决问题。其次，学算法最重要的是学思路而不是实现，如快排，你懂它的核心思路就行了，没必要死记硬背，
 * 实际工作中也不需要你自己写一遍，因为系统早已集成好了。而快排的分而治之的思路非常有用，这在工作中经常遇到，当你遇到这情景时，
 * 你能回忆起快排的分而治之的思路；“哦！好像这里可以用快排的分而治之的思路来解决喔！”，然后再去重看快排的具体实现，看下能不能借鉴下，
 * 我想，学习快排这算法就是没白费了
 *
 * 作者：安卓大叔
 * 链接：<a href='https://www.jianshu.com/p/f65cae7e00ef'>https://www.jianshu.com/p/f65cae7e00ef</a>
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class KMPSearchArith {

    public static void main(String[] args) {
        String text = "iiqooqcjpacma,di12123df0sadwang..wxxiaaawwwnagwalll";
        String parttern = "wang";
        int index = search(text, parttern);
        System.out.println("下标是：" + index);
    }

    public static int search(final String text, final String pattern) {
        int[] next = calNext(pattern);
        int k = -1;
        int N = text.length();
        int M = pattern.length();
        for (int i = 0; i < N; i++) {
            while (k > -1 && pattern.charAt(k + 1) != text.charAt(i)) {
                // 不匹配回溯找最大相同前后缀子字符串
                k = next[k];

                // 回溯方式讲解：
                // 假设：
                // text:      ...abac...
                // pattern:      abad...
                // i指向c时，k指向第二个a，此时k+1指向d不等于c，那么需要回溯
                // 此时我们需要找的是 text[i-1-k, i-1] 子串中最大相同前后缀子字符串，
                // 因为 pattern[0, k] == text[i-1-k, i-1]
                // 而 pattern[0, k] 的最大相同前后子字符串之前已经算过了，是next[k]
            }
            if (pattern.charAt(k + 1) == text.charAt(i))
                k++;

            if (k == M - 1)
                return i - M + 1; // 已找到匹配字符
        }

        return -1; // 未找到匹配字符
    }

    /**
     * 计算模式字符串的next数组
     *
     * @param pattern 模式字符串
     * @return next数组，next数组的值对应模式字符串中从0到m各个子字符串中的相同的无重叠的最大前缀子字符串和最大后缀子字符串
     */
    private static int[] calNext(final String pattern) {
        int M = pattern.length();
        int[] next = new int[M];
        next[0] = -1; // 第一个子字符串只有一个字符，肯定不存在相同前后缀子字符串
        int k = -1; // k代表是相同的无重叠的最大前缀子字符串和最大后缀子字符串的长度减1，为-1表示不存在相同子串
        for (int i = 1; i < M; i++) {
            // 这里k也充当了低位索引，i是高位索引
            while (k > -1 && pattern.charAt(k + 1) != pattern.charAt(i)) {
                k = next[k]; // 字符不相等，k需要回溯
            }
            if (pattern.charAt(k + 1) == pattern.charAt(i)) {
                k++;
            }
            next[i] = k;
        }

        return next;
    }
}
