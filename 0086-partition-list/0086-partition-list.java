/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(-1);
        ListNode right = new ListNode(-1);
        ListNode leftDummy= left;
        ListNode rightDummy= right;
        while(head!=null){
            if (head.val< x){
                leftDummy.next =new ListNode(head.val);
                leftDummy= leftDummy.next;
                head = head.next;
            }else{
                rightDummy.next = new ListNode(head.val);
                rightDummy = rightDummy.next;
                head = head.next;
            }
        }
        leftDummy.next= right.next;
        return left.next;
    }
}