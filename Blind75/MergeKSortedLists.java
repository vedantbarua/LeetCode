public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        // Append any remaining nodes from either list
        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();
        ListNode list1 = solution.new ListNode(1);
        list1.next = solution.new ListNode(4);
        list1.next.next = solution.new ListNode(5);
        ListNode list2 = solution.new ListNode(1);
        list2.next = solution.new ListNode(3);
        list2.next.next = solution.new ListNode(4);
        ListNode list3 = solution.new ListNode(2);
        list3.next = solution.new ListNode(6);
        ListNode[] lists = new ListNode[]{list1, list2, list3};
        ListNode result = solution.mergeKLists(lists);
            // Print the merged list
            while (result != null) {
                System.out.print(result.val + " ");
                result = result.next;
            }
        }
    }
