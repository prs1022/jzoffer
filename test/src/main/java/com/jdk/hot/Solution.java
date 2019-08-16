package com.jdk.hot;
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
//

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root;
        int pre = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + pre;
            if (sum > 10) {
                //考虑进位
                //取10的余数
                root = new ListNode(sum % 10);
                pre = 1;
            } else {
                root = new ListNode(sum);
                pre = 0;
            }
            //向后遍历
            l1 = l1.next;
            l2 = l2.next;
            root.next = root;
        }
        if (l1 != null) {

        }

        return null;
    }
}