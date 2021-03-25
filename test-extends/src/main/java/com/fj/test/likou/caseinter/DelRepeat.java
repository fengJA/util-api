package com.fj.test.likou.caseinter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;


/**
 * 移除未排序链表中的重复节点。保留最开始出现的节点。
 * 输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 */
public class DelRepeat {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(5);
        HashMap<Object, Object> map = new HashMap<>();
    }

    static class ListNode<E>{
        private int num;
        private ListNode(int num){
            this.num = num;
        }
        private ListNode next;
        private Integer val;
    }

    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode pre = head;
        ListNode node = pre.next;
        while (node != null){
            if (set.contains(node.val)){
                node = node.next;
                pre.next = node;
            }else {
                set.add(node.val);
                pre = node;
                node = node.next;
            }
        }
        return head;
    }
}
