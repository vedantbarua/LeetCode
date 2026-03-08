public class ReverseLinkedList {
    //Question : Given the head of a singly linked list, reverse the list, and return the reversed list.

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next; // Store next node
            current.next = prev; // Reverse the link
            prev = current; // Move prev to current
            current = nextTemp; // Move to next node
        }

        return prev; // At the end, prev will be the new head
    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();
        
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Reverse the linked list
        ListNode reversedHead = solution.reverseList(head);

        // Print the reversed linked list: 5 -> 4 -> 3 -> 2 -> 1
        ListNode current = reversedHead;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}