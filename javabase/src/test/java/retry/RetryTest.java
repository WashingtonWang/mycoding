package retry;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/10 16:56
 *
 * 这retry就是一个标记，标记对一个循环方法的操作（continue和break）处理点，功能类似于goto，所以retry一般都是伴随着for循环出现，
 * retry:标记的下一行就是for循环，在for循环里面调用continue（或者break）再紧接着retry标记时，
 * 就表示从这个地方开始执行continue（或者break）操作
 *
 * 如果你现在积累到了这个retry标记的用法，这个地方就可以更加灵活的处理了，可以不用写那么多的辅助代码，
 * 还有一点需要提一下，其实这个retry标识符不是指定的，只要任意符合Java变量命名的标识符都可以，只要后面接上英文冒号就行了。
 * 其实和goto有同样争议的是，在过于复杂的循环程序里面使用这个标记，可能会降低程序的可读性，所以在使用之前，还是需要自己权衡。
 */
public class RetryTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        retry:
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if(j == 3) {
                    continue retry;
                }
            }
        }
        System.out.print(" >>> OK");
        System.out.println();
    }

    public static void test2() {
        retry:
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if(j == 3) {
                    break retry;
                }
            }
        }
        System.out.print(" >>> OK");
        System.out.println();
    }

    public static void test3() {
        for(int i = 0; i < 3; i++) {
            retry:
            for(int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if(j == 3) {
                    continue retry;
                }
            }
        }
        System.out.print(" >>> OK");
        System.out.println();
    }
}
