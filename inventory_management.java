import java.io.FileWriter;
import com.opencsv.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;




public class inventory_management extends sort_bydate{
    // variables for employee details
    static String engine_num = "";
    static String date_entered = "";
    static String stock_label = "";
    static String stock_brand = "";
    static String stock_status = "";
    static Scanner input = new Scanner(System.in);

    
    public static void main(String args[]) throws IOException {
        clear_screen();
        main_menu();
        
    
     }

   
    private static void main_menu() throws IOException {
        System.out.println("");
        System.out.printf("+---------------------------------------------+\n");
        System.out.printf("| %-10s > %-10s < %-10s |%n","", "MPH Stocks System","");
        System.out.printf("+---------------------------------------------+\n");
        System.out.println("[ 1 ] : Add Stocks");
        System.out.println("[ 2 ] : Delete Stock");
        System.out.println("[ 3 ] : Sort By Month");
        System.out.println("[ 4 ] : Search Stock");
        System.out.println("[ 5 ] : Exit\n");
        
        System.out.printf("Enter choice: ");
        Scanner input = new Scanner(System.in);

        String choice = input.nextLine();

        switch(choice){
            case "1":
            clear_screen();
            input_data ();  
            
            break;

            case "2":
            clear_screen();
            delete();
            main_menu();       
            break;

            case "3":
            sort_bydate.main(null);
            System.out.println("File was sorted by month");
            clear_screen();
            main_menu();
                break;
            case "4":
            clear_screen();
            search();             
            
            break;
            case "5":
                
                System.out.println("\nGoodbye!");
                System.exit(0);
                break;

            default:
                
                System.out.println("Invalid entry");
                
        }

        input.close();



    }
     
    public static void clear_screen()    {
        
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private static void search() {
         
     
         // for adding data to our csv file
         Scanner search = new Scanner(System.in);

         System.out.println("Enter Stock Engine Number to show");
         String engine = search.nextLine();
         
 
         try {
             // create br variable to hold csv file data
             // open our csv file with FileReader giving our path with csv_file variable
             BufferedReader br = new BufferedReader(new FileReader("output.csv"));
             String line = "";
 
             // create a while loop and assign our csv file to line variable
             // until the end of the file {null}
             while((line = br.readLine()) != null) {
                 // create an array of string variable that will hold each line
                 // each array element is separated by comma
                 // this will result to: employee = {"10001","Juan","Dela Cruz","etc"}
                 // to access each element we have to declare: employee[0] for 10001, employee[1] for Juan and etc
                 String[] stockDetails = line.split(",");
 
                 if(stockDetails[0].equals(engine)) {
                    
                     // In an array arr{data[0],data[1],data[2]....}
                     //stockDetails{}
                     engine_num = stockDetails[0];
                     date_entered = stockDetails[1];
                     stock_label = stockDetails[2];
                     stock_brand = stockDetails[3];
                     stock_status = stockDetails[4];
 
                      // Print details
 System.out.println("");
 System.out.printf("+---------------------------------------------+\n");
 System.out.printf("| %-13s > %-12s < %-12s |%n","", "Stock Details","");
 System.out.printf("+---------------------------------------------+\n");
 System.out.printf("-----------------------------------------------%n");
 System.out.printf("| %-20s | %-20s |%n", "Engine No:", engine_num);
 System.out.printf("-----------------------------------------------%n");
 System.out.printf("| %-20s | %-20s |%n", "Date Entered:", date_entered);
 System.out.printf("-----------------------------------------------%n");
 System.out.printf("| %-20s | %-20s |%n", "Stock Label:", stock_label);
 System.out.printf("-----------------------------------------------%n");
 System.out.printf("| %-20s | %-20s |%n", "Brand:", stock_brand);
 System.out.printf("-----------------------------------------------%n");
 System.out.printf("| %-20s | %-20s |%n", "Status:", stock_status);
 System.out.printf("-----------------------------------------------%n");

 
                     
                 }
                     
                     
             }
 
             br.close();
         } catch (Exception e) {
             // TODO: handle exception
             System.out.println("Error!");
         }       
    }


    private static void delete() throws IOException {
        // for adding data to our csv file
        Scanner delete = new Scanner(System.in);

        System.out.println("Enter Engine Number to delete");
        String deleteID = delete.nextLine();
        String CSVFilename2 = "output.csv";
        String tempFilename2 = CSVFilename2.replace(".csv", ".tmp");
        CSVReader reader2 = new CSVReader(new FileReader(CSVFilename2));
        String[] row;
        try(CSVWriter writer = new CSVWriter(new FileWriter(tempFilename2, true), ',', CSVWriter.NO_QUOTE_CHARACTER)){
            while((row = reader2.readNext()) != null){
                if(!row[0].equals(deleteID)){ //12346
                    writer.writeNext(row);
                }
            }
            reader2.close();
        } finally {
            new File(CSVFilename2).delete();
            new File(tempFilename2).renameTo(new File(CSVFilename2));
        }
    }

    

    // Adding stocks
    private static void input_data() throws IOException {
        try (// for adding data to our csv file
            Scanner details = new Scanner(System.in)) {
            System.out.println("Enter Engine Number");
            String engineNo = details.nextLine();

            System.out.println("Enter Date Entered format MM/dd/yyyy");
            System.out.println("ex.01/01/2001");
            String date = details.nextLine();

            System.out.println("Enter Stock Label");
            String brand = details.nextLine();

            System.out.println("Enter Stock Brand");
            String label = details.nextLine();

            System.out.println("Enter Stock Status");
            String status = details.nextLine();


            //Instantiating the CSVWriter class
            CSVWriter adwriter = new CSVWriter(new FileWriter("output.csv",true), ',', CSVWriter.NO_QUOTE_CHARACTER);
            //Writing data to a csv file
            String line1[] = {engineNo, date, label, brand, status};
            //Writing data to the csv file
            adwriter.writeNext(line1);
            //Flushing data from writer to file
            adwriter.flush();
        }

        System.out.println("Data saved");
        
        
   }


   
   
}

