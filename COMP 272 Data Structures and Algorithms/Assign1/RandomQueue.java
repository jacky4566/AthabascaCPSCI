public class RandomQueue {
    private LLNode head; // start of list
    private int size; // Used to keep track of list size

    public void add(LLNode newNode) {
        System.out.println("Adding Element: " + newNode.data); // Confirm back to user
        size++; // incrament counter
        if (head == null){
            head = newNode;
            return;
        }
        newNode.nextPtr = head;
        head = newNode;
    }

    public LLNode remove() {
        LLNode returnNode = head;
        int targetNode = (int) (Math.random() * size);
        System.out.println("Removing: " + targetNode);
        while(targetNode-- != 0){
            returnNode = returnNode.nextPtr;
        }

        return returnNode;
    }

    public void print(){
        LLNode pointer = head;
        for (int i = 0; i < size; i++) {
            System.out.print(pointer.data);
            if (pointer.nextPtr != null){
                System.out.print(", ");
            }
            pointer = pointer.nextPtr;
        }
        System.out.println();
    }
}
