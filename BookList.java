package dsaProject;
import java.io.*;
class BookNode
{
    int bookId;
    String title;
    String author;
    int totalCopies;
    int availableCopies;
    BookNode link;

    BookNode(int bookId, String title, String author,int copies)
    {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalCopies=copies;
        this.availableCopies=copies;
        this.link = null;
    }
}


class BookList
{
	Stack issuedStack = new Stack();
	BookNode head = null;
	
	void loadFromFile()
	{
	    try
	    {
	        FileReader fr = new FileReader("books.txt");
	        String line = "";
	        int ch;

	        while ((ch = fr.read()) != -1)
	        {
	            if (ch != '\n')
	            {
	                line += (char) ch;
	            }
	            else
	            {
	                processLine(line);
	                line = "";
	            }
	        }

	        if (!line.equals("")) // last line
	        {
	            processLine(line);
	        }

	        fr.close();
	        System.out.println("Books loaded from file");
	    }
	    catch (Exception e)
	    {
	        System.out.println("Error reading file");
	    }
	}
	
	void processLine(String line)
	{
	    String[] data = line.split(",");

	    int id = Integer.parseInt(data[0]);
	    String title = data[1];
	    String author = data[2];
	    int total = Integer.parseInt(data[3]);
	    int available = Integer.parseInt(data[4]);

	    BookNode newNode = new BookNode(id, title, author, total);
	    newNode.availableCopies = available;

	    if (head == null)
	    {
	        head = newNode;
	    }
	    else
	    {
	        BookNode temp = head;
	        while (temp.link != null)
	            temp = temp.link;

	        temp.link = newNode;
	    }
	}
	
	void saveToFile()
	{
	    try
	    {
	        FileWriter fw = new FileWriter("books.txt");

	        BookNode temp = head;

	        while (temp != null)
	        {
	            fw.write(temp.bookId + "," +
	                     temp.title + "," +
	                     temp.author + "," +
	                     temp.totalCopies + "," +
	                     temp.availableCopies + "\n");

	            temp = temp.link;
	        }

	        fw.close();
	        System.out.println("Data saved to file");
	    }
	    catch (Exception e)
	    {
	        System.out.println("Error writing file");
	    }
	}
	
	
//    void addBook(int id, String title, String author,int copies)
//    {
//        BookNode newNode = new BookNode(id, title, author,copies);
//        if (head == null)
//        {
//            head = newNode;
//            return;
//        }
//
//        BookNode temp = head;
//        while (temp.link != null)
//        {
//            temp = temp.link;
//        }
//        temp.link = newNode;
//    }

    void displayBooks()
    {
        if (head == null)
        {
            System.out.println("Library is Empty");
            return;
        }

        BookNode temp = head;
        System.out.println("Library Book List:");
        while (temp != null)
        {
            System.out.println("ID: " + temp.bookId +" | Title: " + temp.title +" | Author: " + temp.author);
            System.out.println("Total copies: "+ temp.totalCopies +" | Available: "+temp.availableCopies);
            System.out.println();
            temp = temp.link;
        }
    }

    void searchByTitle(String title)
    {
        BookNode temp = head;
        boolean found = false;

        while (temp != null)
        {
            if (temp.title.equalsIgnoreCase(title))
            {
                System.out.println("Found -> ID: " + temp.bookId +" | Title: " + temp.title +" | Author: " + temp.author);
                found = true;
            }
            temp = temp.link;
        }

        if (!found)
        {
            System.out.println("No books found");
        }
    }

    void issueBook(int id)
    {
        BookNode temp = head;

        while (temp != null)
        {
            if (temp.bookId == id)
            {
                if (temp.availableCopies>0)
                {
                    temp.availableCopies--;
                    issuedStack.push(id);
                    System.out.println("Book Issued Successfully");
                    System.out.println("Copies left: " + temp.availableCopies);
                }
                else
                {
                    System.out.println("No copies available");
                }
                return;
            }
            temp = temp.link;
        }

        System.out.println("Book not found");
    }

    void returnBook(int id)
    {
        BookNode temp = head;

        while (temp != null)
        {
            if (temp.bookId == id)
            {
                if (temp.availableCopies<temp.totalCopies)
                {
                    temp.availableCopies++;
                    System.out.println("Book Returned Successfully");
                }
                else
                {
                    System.out.println("No copies issued");
                }
                return;
            }
            temp = temp.link;
        }

        System.out.println("Book not found");
    }

    void deleteBook(int id)
    {

        if (head == null)
        {
            System.out.println("Library is empty");
            return;
        }

        if (head.bookId == id)
        {
            head = head.link;
            System.out.println("Book deleted successfully");
            return;
        }

        BookNode temp = head;
        while (temp.link != null && temp.link.bookId != id)
        {
            temp = temp.link;
        }

        if (temp.link == null)
        {
            System.out.println("Book not found");
        }
        else
        {
            temp.link = temp.link.link;
            System.out.println("Book deleted successfully");
        }
    }

    int countBooks()
    {
        int count = 0;
        BookNode temp = head;

        while (temp != null)
        {
            count++;
            temp = temp.link;
        }
        return count;
    }
    
    void sortBooks()
    {
        if (head == null)
            return;

        BookNode i, j;

        for (i = head; i.link != null; i = i.link)
        {
            for (j = head; j.link != null; j = j.link)
            {
                if (j.title.compareToIgnoreCase(j.link.title) > 0)
                {
                    int tempId = j.bookId;
                    String tempTitle = j.title;
                    String tempAuthor = j.author;
                    int tempTotal=j.totalCopies;
                    int tempAvailable=j.availableCopies;

                    j.bookId = j.link.bookId;
                    j.title = j.link.title;
                    j.author = j.link.author;
                    j.totalCopies=j.link.totalCopies;
                    j.availableCopies=j.link.availableCopies;

                    j.link.bookId = tempId;
                    j.link.title = tempTitle;
                    j.link.author = tempAuthor;
                    j.link.totalCopies=tempTotal;
                    j.link.availableCopies=tempAvailable;
                }
            }
        }

        System.out.println("Books sorted successfully");
    }

}