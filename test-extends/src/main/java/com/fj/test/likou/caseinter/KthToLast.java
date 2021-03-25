package com.fj.test.likou.caseinter;

/**
 * 找出单向链表中倒数第 k 个节点。返回该节点的值。
 * [1,2]
 * 1
 *
 * 1->2->3->4->5 和 k = 2
 * 输出： 4
 */
public class KthToLast {
    public int kthToLast(ListNode head, int k) {
        if (head.next == null){
            return head.val;
        }
        ListNode node = head;
        int num = 0;
        while(node != null){
            node = node.next;
            num ++;
        }
        System.out.println(num);// 2
        if (k <= num){
            k = num - k;
            num = 0;
            node = head;
            while (node != null){
                if (k == num){
                    return node.val;
                }else {
                    node = node.next;
                    num ++;
                }
            }
        }
        return 0;
    }

    static class ListNode<E>{
        private int num;
        private ListNode(int num){
            this.num = num;
        }
        private ListNode next;
        private Integer val;
    }

    public int kthToLast1(ListNode head, int k) {
        int count=0;
        ListNode p=head;
        //统计链表长度
        while(p!=null){
            p=p.next;
            count++;
        }
        for(int i=0;i<count-k;i++){
            head=head.next;
        }
        return head.val;
    }
}
