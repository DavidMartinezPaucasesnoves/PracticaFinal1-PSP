import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) {
        String textoPrueba = "akasn asirfasiurapoi ipasoi u0fajf ERROR airh afpas afpaejp f ERROR jasbf error";
        File archivoErrores = new File("errors_filtre.log");
        
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "src\\FiltreLog.java");
            pb.redirectError(archivoErrores);
            Process process = pb.start();

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))){
                bw.write(textoPrueba);
                bw.flush();
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                String resultadoErr = br.readLine();
                IO.println("Lineas con ERROR encontradas: " + resultadoErr);
            }

            int exitCode = process.waitFor();
            System.out.println("Código de finalización del hijo: " + exitCode);
            
        }
        catch (Exception e) {
                IO.println(e.getMessage());
            }
    }
}