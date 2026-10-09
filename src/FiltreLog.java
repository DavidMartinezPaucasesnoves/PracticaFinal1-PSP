import java.io.*;

public class FiltreLog {
    public static void main(String[] args) {
        String palabraClave = "ERROR";
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains((palabraClave))){
                    contador++;
                }
            }
        }
        catch (IOException e) {
            IO.println(e.getMessage());
        }
        
        IO.println(contador);
    }
}