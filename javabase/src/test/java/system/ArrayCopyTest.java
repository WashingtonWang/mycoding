package system;

/**
 * native方法  arraycopy  使用例子
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/11/12 21:10
 */
public class ArrayCopyTest {
    private static Object[] elementData = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        arrayCopyTwo(elementData, 2, 10);
    }

    public static void arrayCopy(Object[] elementData, int index, int size) {
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null; // clear to let GC do its work

        for (Object elementDatum : elementData) {
            System.out.print(elementDatum + " ");
        }
    }

    public static void arrayCopyTwo(Object[] elementData, int index, int size) {
        Object[] ed = {12,13,14,15,16,17,18,19,20,21};
        System.arraycopy(elementData, index + 1, ed, index, size - index - 1);
        elementData[--size] = null; // clear to let GC do its work

        for (Object elementDatum : ed) {
            System.out.print(elementDatum + " ");
        }
    }
}
