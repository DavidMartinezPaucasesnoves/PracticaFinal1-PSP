import java.io.*;

public class FiltreLog {
    public static void main(String[] args) {
        String palabraClave = (args.length > 0) ? args[0] : "ERROR";
        int contador = 0;
        boolean hayContenido = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                hayContenido = true;
                

                String[] palabras = linea.split("\\s+");
                for (String palabra : palabras) {
                    if (palabra.equals(palabraClave)) {
                        contador++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
            System.exit(1);
        }

        if (!hayContenido) {
            System.err.println("Error: Texto vacío.");
            System.exit(1);
        }
        
        System.out.println(contador);
        System.exit(0);
    }
}