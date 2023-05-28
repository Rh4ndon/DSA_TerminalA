import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class sort_bydate {
    
    public static void main(String[] args) throws IOException {
        File input = new File("output.csv");
        File output = new File("sorted.csv");
        sort_bydate m = new sort_bydate();
        List<Stock> stocks = m.sort(input, 2);
        m.write(output, stocks);
        new File("output.csv").delete();
        new File("sorted.csv").renameTo(new File("output.csv"));

    }

    private void write(File output, List<Stock> stocks) throws IOException {
        Path path = Paths.get(output.toURI());
        List<String> listStocks = new ArrayList<>(stocks.size());
        for (Stock s: stocks) {
            listStocks.add(s.toString());
        }
        Files.write(path, listStocks);
    }

    private List<Stock> sort(File f, int columnToSort) throws IOException {
        Path path = Paths.get(f.toURI());
        List<String> allLines = Files.readAllLines(path);
        List<Stock> stocks = new ArrayList<>(allLines.size());
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i);
            String[] columns = line.split(",");
            Stock s = new Stock(columns[0], Date.parse(columns[1]), columns[2], columns[3], columns[4]);
            stocks.add(s);
        }
        Collections.sort(stocks);
        return stocks;
    }
}

class Stock implements Comparable<Stock> {

    private String stockID;
    private long date;
    private String brand;
    private String label;
    private String status;
   

    public Stock(String stockID, long l, String brand, String label, String status) {
        this.stockID = stockID;
        this.date = l;
        this.brand = brand;
        this.label = label;
        this.status = status;
    }

    

    @Override
    public int compareTo(Stock o) {
        return (int) (o.date- this.date); // DESC
    }

    @Override
    public String toString() {
        SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy");
        String day = sdformat.format(date); 
        return stockID + "," + day + "," + brand + "," + label + "," + status;
    }
}