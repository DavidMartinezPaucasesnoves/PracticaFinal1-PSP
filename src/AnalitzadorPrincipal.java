import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) {
        String textoPrueba = "akasn asirfasiurapoi ipasoi u0fajf ERROR airh afpas afpaejp f ERROR jasbf error";
        File archivoErrores = new File("errors_filtre.log");
        
        try {
            ProcessBuilder pbe = new ProcessBuilder("java", "-jar", "src\\FiltreLog.java");
            pbe.redirectError(archivoErrores);
            Process processErr = pbe.start();
            String resultadoErroresStr = leerSalidaProceso(processErr);
            enviarDatosAlProceso(processErr, textoPrueba);
            int exitCodeError = processErr.waitFor();
            int numErrores = Integer.parseInt(resultadoErroresStr.trim());


            ProcessBuilder pbw = new ProcessBuilder("java", "-jar", "src\\FiltreLog.java", "WARNING");
            pbw.redirectError(archivoErrores);
            Process processWar = pbw.start();
            String resultadoWarningStr = leerSalidaProceso(processWar);
            enviarDatosAlProceso(processWar, textoPrueba);
            int exitCodeWarning = processWar.waitFor();
            int numWarnings = Integer.parseInt(resultadoWarningStr.trim());

                
            System.out.println("Resultado: ERRORES = " + numErrores + ", WARNINGS = " + numWarnings);
            
        }
        catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
    }

    private static void enviarDatosAlProceso(Process process, String datos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))){
                bw.write(datos);
                bw.flush();
                bw.close();
            }
    }

    private static String leerSalidaProceso(Process process) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                return br.readLine();
            }
    }
}