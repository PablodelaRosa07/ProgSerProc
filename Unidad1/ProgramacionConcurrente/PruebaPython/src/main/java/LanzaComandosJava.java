import java.io.IOException;

public class LanzaComandosJava {
	
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("python", "src\\main\\resources\\Python.py\"");
		
		pb.inheritIO();
		pb.start();
		
		
		try {
			pb = new ProcessBuilder("java", "src.main.java.Hijo.java\"");
			pb.inheritIO();
			Process proceso = pb.start();
			int exit = proceso.waitFor();
			System.out.println(exit);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		}

	}
