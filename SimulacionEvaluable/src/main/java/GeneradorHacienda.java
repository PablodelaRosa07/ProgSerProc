import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneradorHacienda {
	public static void main(String[] args) {

		String nombreFichero = args[0];
		String ficheroResultado = args[1];
		GeneraNSSFichero generaNSSFichero = new GeneraNSSFichero();
		
		compila();
		ejecuta(nombreFichero, ficheroResultado);
	}
	
	public static void compila() {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "GeneraNSSFichero.java");
            pb.inheritIO();
            Process proceso = pb.start();
            
            int codigoRetorno = proceso.waitFor();
            if (codigoRetorno == 0) {
                System.out.println("Compilación realizada con éxito.");
            }
        } catch (IOException | InterruptedException e) {
        	System.err.println("No se ha podido compilar");
        }
    }
	
	public static void ejecuta(String ficheroEntrada, String ficheroResultado) {
        List<Process> listaProcesos = new ArrayList<>();

            try {
                ProcessBuilder pb = new ProcessBuilder("java", "C:\\Users\\alumno\\Desktop\\Modulos\\2ºDAM\\ProgSerProc\\SimulacionEvaluable\\src\\main\\java\\GeneraNSSFichero.java", ficheroEntrada, ficheroResultado);
                Process proceso = pb.start();
                listaProcesos.add(proceso);
                
            } catch (IOException e) {
            	System.err.println("Error");
            }
        

        for (int i = 0; i < listaProcesos.size(); i++) {
            try {
                listaProcesos.get(i).waitFor();
            } catch (InterruptedException e) {
            	System.err.println("Error");
            }
        }
        
        
        
	}
	

}
