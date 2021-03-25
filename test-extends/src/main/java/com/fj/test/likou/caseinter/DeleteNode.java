package com.fj.test.likou.caseinter;

/**
 * 删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
 */
public class DeleteNode {
    @SuppressWarnings("unchecked")
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }

    static class ListNode<E>{
        private int num;
        private ListNode(int num){
            this.num = num;
        }
        private ListNode next;
        private Integer val;
    }
}
