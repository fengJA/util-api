package com.fj.test.likou.caseinter;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * 编写函数对这两个整数求和，并用链表形式返回结果
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 */
public class TwoNumbers{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1 || null == l2){
            if (null == null && null != l2){
                return l2;
            }
            if (null != null && null == l2){
                return l1;
            }
            if (null == null && null == l2){
                return l2;
            }
        }
        ListNode node = new ListNode();
        ListNode bas = node;
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        while (l1 != null || l2 != null){
            s1 = s1.append(l1 != null ? l1.val : "");
            s2 = s2.append(l2 != null ? l2.val : "");
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        System.out.println(s1);
        System.out.println(s2);
        s1.reverse();
        s2.reverse();
        int add = Integer.valueOf(s1.toString()) + Integer.valueOf(s2.toString());
        BigDecimal add1 = new BigDecimal(s1.toString()).add(new BigDecimal(s2.toString()));
        System.out.println(add);
        char[] chars = String.valueOf(add).toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            bas.next = new ListNode(Integer.valueOf(chars[i]));
            bas = bas.next;
        }
        return node.next;
    }


    static class ListNode<E>{
        private int num;
        private ListNode(int num){
            this.num = num;
        }
        private ListNode(){
        }

        private ListNode next;
        private Integer val;
    }


    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("123");
        System.out.println(builder);
        builder.reverse();
        System.out.println(builder);
        StringBuilder bu = new StringBuilder();
        bu = bu.append("0239");
        System.out.println(bu);
        BigDecimal add = new BigDecimal(builder.toString()).add(new BigDecimal(bu.toString()));
        System.out.println("========");
        char[] chars = String.valueOf(add).toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            System.out.print(Integer.parseInt(chars[i] + ""));
        }
        System.out.println();
        System.out.println("woshi :" +add);
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        // 291   123   192  321  513  414
        int carry = 0;
        ListNode ans = new ListNode(0);
        ListNode cur = ans;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;

            int num = num1 + num2 + carry;
            carry = num / 10;

            cur.next = new ListNode(num % 10);

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            cur = cur.next;
        }
        if (carry == 1)
            cur.next = new ListNode(carry);
        return ans.next;
    }
}
























