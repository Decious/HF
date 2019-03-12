package iostuff;

import java.io.FileWriter;
import java.io.IOException;

public class WriteAFile {
    public static void main(String[] args) {
        FileWriter  filewriter = null;
        try {
            filewriter = new FileWriter("Test.txt");
            filewriter.write("FILEWRITER TEST");
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
