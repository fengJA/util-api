package com.fj.test.likou.caseinter;

/**
 * 检查输入的链表是否是回文的。
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode newNode = null;
        while (fast.next != null && fast.next.next != null) {
            //慢指针前进的过程中还需要反转链表,反转前面部分的链表；1 2 3 4 5 6 则只翻转前两个：newNode = 2 1
            fast = fast.next.next;
            ListNode nextNode = slow.next;
            slow.next = newNode;
            newNode = slow;
            slow = nextNode;
        }
        if (fast.next == null) {
            //链表为奇数个
            ListNode newHead = slow.next;
            while (newHead != null && newNode != null) {
                if (newHead.val != newNode.val) {
                    return false;
                }
                newHead = newHead.next;
                newNode = newNode.next;
            }
        } else {
            //链表为偶数个
            ListNode newHead = slow.next;
            slow.next = newNode;
            while (slow != null && newHead != null) {
                if (slow.val != newHead.val) {
                    return false;
                }
                slow = slow.next;
                newHead = newHead.next;
            }
        }
        return true;
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
}
