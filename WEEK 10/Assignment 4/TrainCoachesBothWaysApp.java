public class TrainCoachesBothWaysApp {

    // Doubly linked list node with forward and backward references
    static class CoachNode {
        String name;
        CoachNode prev;
        CoachNode next;

        CoachNode(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    static class TrainDoublyLinkedList {
        private CoachNode head;
        private CoachNode tail;

        public TrainDoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        // Appends coach to the end using the tail reference in O(1) time
        public void addLast(String name) {
            CoachNode newNode = new CoachNode(name);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        // Locates and unlinks coach by name, adjusting head and tail if needed
        public boolean remove(String name) {
            CoachNode curr = head;
            while (curr != null) {
                if (curr.name.equals(name)) {
                    // Adjust forward link of previous neighbor or update head
                    if (curr == head) {
                        head = curr.next;
                        if (head != null) {
                            head.prev = null;
                        } else {
                            tail = null; // List became empty
                        }
                    } else {
                        curr.prev.next = curr.next;
                    }

                    // Adjust backward link of next neighbor or update tail[cite: 74]
                    if (curr == tail) {
                        tail = curr.prev;
                        if (tail != null) {
                            tail.next = null;
                        } else {
                            head = null; // List became empty
                        }
                    } else {
                        curr.next.prev = curr.prev;
                    }

                    return true;
                }
                curr = curr.next;
            }
            return false;
        }

        // Traverses forward from head (engine end)[cite: 74]
        public void printForward() {
            StringBuilder sb = new StringBuilder("Forward: ");
            CoachNode curr = head;
            while (curr != null) {
                sb.append(curr.name);
                if (curr.next != null) {
                    sb.append(" <-> ");
                }
                curr = curr.next;
            }
            System.out.println(sb.toString());
        }

        // Traverses backward from tail (guard end)[cite: 74]
        public void printBackward() {
            StringBuilder sb = new StringBuilder("Backward: ");
            CoachNode curr = tail;
            while (curr != null) {
                sb.append(curr.name);
                if (curr.prev != null) {
                    sb.append(" <-> ");
                }
                curr = curr.prev;
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        TrainDoublyLinkedList train = new TrainDoublyLinkedList();

        // Sample input: Engine, A1, B1, B2, Guard[cite: 74]
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        // Remove coach B1[cite: 74]
        train.remove("B1");

        // Expected output:
        // Forward: Engine <-> A1 <-> B2 <-> Guard[cite: 74]
        // Backward: Guard <-> B2 <-> A1 <-> Engine[cite: 74]
        train.printForward();
        train.printBackward();
    }
}