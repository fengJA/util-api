package com.fj.test.likou.caseinter;

/**
 * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。
 * 如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 * 即：是比x小的在比x大前面，不仅仅是在x的前面
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 */
public class Partion {
    public String a = "qwe";

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

    private ListNode partition(ListNode head, int x){
        // 用双指针也可以
        // 小于x的在链表左边，大于等于x的放在链表右边
        // 设置两个虚拟结点，一个连接小于x的结点，一个连接大于等于x的结点，最后将两个链表连起来即可
        ListNode small = new ListNode(); // 虚拟头结点 小于x
        ListNode big = new ListNode(); // 虚拟头结点 大于等于x
        ListNode cur = head;
        ListNode smallCur = small;
        ListNode bigCur = big;
        while(cur != null){
            if(cur.val < x){
                smallCur.next = cur;
                smallCur = smallCur.next;
            }else{
                bigCur.next = cur;
                bigCur = bigCur.next;
            }
            cur = cur.next;
        }
        // 拼接两个链表
        smallCur.next = big.next;
        bigCur.next = null;
        return small.next;
    }
}
