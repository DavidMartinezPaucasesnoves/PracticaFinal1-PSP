import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) {
        String textoPrueba = "akasn asirfasiurapoi WARNING ipasoi u0fajf ERROR airh WARNING afpas WARNING afpaejp f ERROR jasbf ERROR";
        File archivoErrores = new File("errors_filtre.log");
        
        try {
            // Proceso 1: ERRORES
            ProcessBuilder pbe = new ProcessBuilder("java", "src\\FiltreLog.java", "ERROR");
            pbe.redirectError(archivoErrores);
            Process processErr = pbe.start();
            

            enviarDatosAlProceso(processErr, textoPrueba);

            String resultadoErroresStr = leerSalidaProceso(processErr);
            processErr.waitFor();
            
            int numErrores = (resultadoErroresStr != null) ? Integer.parseInt(resultadoErroresStr.trim()) : 0;


            // Proceso 2: WARNINGS
            ProcessBuilder pbw = new ProcessBuilder("java", "src\\FiltreLog.java", "WARNING");
            pbw.redirectError(archivoErrores);
            Process processWar = pbw.start();
            

            enviarDatosAlProceso(processWar, textoPrueba);
            String resultadoWarningStr = leerSalidaProceso(processWar);
            processWar.waitFor();
            
            int numWarnings = (resultadoWarningStr != null) ? Integer.parseInt(resultadoWarningStr.trim()) : 0;

            int exitValueErr = processErr.exitValue();
            int exitValueWar = processWar.exitValue();

            int exitValueFinal = 0;

            if (exitValueErr != 0 || exitValueWar != 0){
                exitValueFinal = 1;
            }
                
            System.out.println("RESULTAT: ERRORS=" + numErrores + " | WARNINGS=" + numWarnings + " | EXIT_CODE=" + exitValueFinal);
            
        } catch (Exception e) {
            System.err.println("Error en proceso principal: " + e.getMessage());
        }
    }

    private static void enviarDatosAlProceso(Process process, String datos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            bw.write(datos);
            bw.flush();
            bw.close();
        }
    }

    private static String leerSalidaProceso(Process process) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            return br.readLine();
        }
    }
}