import java.io.IOException;

public class Hijo {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("notepad.exe");
            pb.start(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
