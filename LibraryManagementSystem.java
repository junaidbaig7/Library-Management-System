package dsaProject;
import java.util.Scanner;

public class LibraryManagementSystem
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        BookList library = new BookList();
        library.loadFromFile();
        IssueQueue iq = new IssueQueue();
        int choice;

        do
        {
            System.out.println("                        WELCOME TO THE LIBRARY   ");
            System.out.println("1. Display Books");
            System.out.println("2. Search by Title");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Count Total Books");
            System.out.println("7. Sort Books by Title");
            System.out.println("8. Request Book Issue");
            System.out.println("9. Process Issue Request");
            System.out.println("10.View Issue Queue");
            System.out.println("11.Show Recently Issued Books");
            System.out.println("12.Remove Last Issued ");
            System.out.println("13.Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {

	//                case 1:
	//                    System.out.print("Enter Book ID: ");
	//                    int id = sc.nextInt();
	//                    sc.nextLine();
	//
	//                    System.out.print("Enter Title: ");
	//                    String title = sc.nextLine();
	//
	//                    System.out.print("Enter Author: ");
	//                    String author = sc.nextLine();
	//                    
	//                    System.out.print("Enter Total number of copies: ");
	//                    int copies = sc.nextInt();
	//
	//                    library.addBook(id, title, author,copies);
	//                    break;

                case 1:
                    library.displayBooks();
                    break;

                case 2:
                    System.out.print("Enter title to search: ");
                    String searchTitle = sc.nextLine();
                    library.searchByTitle(searchTitle);
                    break;

                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    library.issueBook(issueId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;

                case 5:
                    System.out.print("Enter Book ID to delete: ");
                    int delId = sc.nextInt();
                    library.deleteBook(delId);
                    break;

                case 6:
                    System.out.println("Total Books: " + library.countBooks());
                    break;

                case 7:
                    library.sortBooks();
                    break;
                    
                case 8:
                    System.out.print("Enter Member ID to request issue: ");
                    iq.enqueue(sc.nextInt());
                    break;
                
                case 9:
                    iq.dequeue();
                    break;
                    
                case 10:
                    iq.displayQueue();
                    break;
                    
                case 11:
                    library.issuedStack.display();
                    break;

                case 12:
                    library.issuedStack.pop();
                    break;
                    
                case 13:
                    library.saveToFile();
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 13);

        sc.close();
    }
}