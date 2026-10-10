public class RoundRobinGameTurnsApp {

    // Circular singly linked list node
    static class PlayerNode {
        String name;
        PlayerNode next;

        PlayerNode(String name) {
            this.name = name;
            this.next = null;
        }
    }

    static class CircularPlayerList {
        private PlayerNode tail;

        public CircularPlayerList() {
            this.tail = null;
        }

        // Appends player in O(1) time maintaining tail pointer
        public void addLast(String name) {
            PlayerNode newNode = new PlayerNode(name);
            if (tail == null) {
                tail = newNode;
                tail.next = tail; // Points to itself
            } else {
                newNode.next = tail.next; // New node points to head (tail.next)
                tail.next = newNode;      // Old tail points to new node
                tail = newNode;           // Tail pointer moves to new node[cite: 75]
            }
        }

        // Prints players for k consecutive turns[cite: 75]
        public void printTurns(int turns) {
            if (tail == null || turns <= 0) {
                return;
            }

            // Head is always tail.next in a circular list[cite: 75]
            PlayerNode curr = tail.next;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < turns; i++) {
                sb.append(curr.name);
                if (i < turns - 1) {
                    sb.append(" ");
                }
                curr = curr.next; // Wraps around automatically[cite: 75]
            }

            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        CircularPlayerList game = new CircularPlayerList();

        // Sample input: Asha, Ravi, Neha; turns = 7[cite: 75]
        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        // Expected output: Asha Ravi Neha Asha Ravi Neha Asha[cite: 75]
        game.printTurns(7);
    }
}