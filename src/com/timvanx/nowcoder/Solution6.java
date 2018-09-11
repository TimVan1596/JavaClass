package com.timvanx.nowcoder;


//题目描述
// 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
// 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
// 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
// NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

public class Solution6 {

    public static void main(String... args) {
        int[] arr = {34, 55, 67, 69, 85, 91, 133, 222, 822, 4965, 4965, 18, 20, 20};
        System.out.println(new Solution6().minNumberInRotateArray(arr));
    }

    public int minNumberInRotateArray(int[] array) {
        int last = 0, ret = 0;
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                int curr = array[i];
                if (curr < last) {
                    ret = curr;
                    break;
                }
                last = curr;
            }
        }

        return ret;
    }
}