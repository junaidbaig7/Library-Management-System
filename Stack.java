package dsaProject;

public class Stack {
    private int[] arr;
    private int top;
    private int size;

    Stack() {
        size = 50;
        arr = new int[size];
        top = -1;
    }

    void push(int bookId) {
        if (top == size - 1) {
            System.out.println("Stack is full!");
            return;
        }
        arr[++top] = bookId;
    }

    void pop() {
        if (top == -1) {
            System.out.println("No issued books in stack.");
            return;
        }
        System.out.println("Removed last issued Book ID: " + arr[top--]);
    }

    void display() {
        if (top == -1) {
            System.out.println("No recently issued books.");
            return;
        }
        System.out.println("Recently Issued Book IDs (latest first):");
        for (int i = top; i >= 0; i--) {
            System.out.println("Book ID: " + arr[i]);
        }
    }
}