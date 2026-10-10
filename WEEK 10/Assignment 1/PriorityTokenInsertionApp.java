public class PriorityTokenInsertionApp {

    // Custom singly linked list node
    static class TokenNode {
        int data;
        TokenNode next;

        TokenNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class TokenLinkedList {
        private TokenNode head;
        private int size;

        public TokenLinkedList() {
            this.head = null;
            this.size = 0;
        }

        // Add to front (priority) -> O(1) time
        public void addFirst(int token) {
            TokenNode newNode = new TokenNode(token);
            newNode.next = head;
            head = newNode;
            size++;
        }

        // Add to end (normal) -> O(N) without tail pointer, or O(1) with tail
        public void addLast(int token) {
            TokenNode newNode = new TokenNode(token);
            if (head == null) {
                head = newNode;
            } else {
                TokenNode curr = head;
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = newNode;
            }
            size++;
        }

        // Insert at 0-based index -> O(k) time where k is the index
        public void insertAt(int index, int token) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }

            if (index == 0) {
                addFirst(token);
                return;
            }

            TokenNode newNode = new TokenNode(token);
            TokenNode prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            // Set new node's next first, then link previous node to it
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }

        public void printList() {
            TokenNode curr = head;
            StringBuilder sb = new StringBuilder();
            while (curr != null) {
                sb.append(curr.data).append(" -> ");
                curr = curr.next;
            }
            sb.append("null");
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        TokenLinkedList list = new TokenLinkedList();

        // Initial list: 101 -> 102 -> 103[cite: 57]
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        // Sequence of operations[cite: 57]
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        // Expected Output: 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null[cite: 57]
        System.out.print("Output: ");
        list.printList();
    }
}