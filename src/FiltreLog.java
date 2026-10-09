import java.io.*;

public class FiltreLog {
    public static void main(String[] args) {
        String palabraClave = "ERROR";
        int contador = 0;
        boolean hayContenido = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                tieneContenido = true;
                if (linea.contains((palabraClave))){
                    contador++;
                }
            }
        }
        catch (IOException e) {
            IO.println(e.getMessage());
        }

        if (!hayContenido) {
            IO.println("Error: Texto Vacío.");
        }
        
        IO.println(contador);
    }
}