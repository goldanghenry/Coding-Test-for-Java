package Basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileInput {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        
    }
}
