package dsaProject;
class IssueQueue
{
 int front = -1, rear = -1;
 int size = 5;
 int[] queue = new int[size];

 public void enqueue(int memberId)
 {
     if (rear == size - 1)
     {
         System.out.println("Issue queue is full! Please wait.");
         return;
     }
     if (front == -1)
     {
         front = 0;
     }
     queue[++rear] = memberId;
     System.out.println("Issue request added for Member ID: " + memberId);
 }

 public void dequeue()
 {
     if (front == -1 || front > rear)
     {
         System.out.println("No pending issue requests!");
         return;
     }
     System.out.println("Book issued to Member ID: " + queue[front]);
     front++;
 }

 public void displayQueue()
 {
     if (front == -1 || front > rear)
     {
         System.out.println("Issue queue is empty!");
         return;
     }
     System.out.print("Pending Issue Requests (Member IDs): ");
     for (int i = front; i <= rear; i++)
     {
         System.out.print(queue[i] + " ");
     }
     System.out.println();
 }
}