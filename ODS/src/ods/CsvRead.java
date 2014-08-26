package ods;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvRead {

    public static void main(String[] args) throws FileNotFoundException, IOException {
    CSVReader reader = new CSVReader(new FileReader("/home/gabor/ITIM4U/users.csv"));
    List <String[]> myEntries = reader.readAll();


    for (String[] strings : myEntries) {
    System.out.println(Arrays.toString(strings));

}
}
}
