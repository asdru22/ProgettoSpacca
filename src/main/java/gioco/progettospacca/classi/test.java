package gioco.progettospacca.classi;

import java.awt.Desktop;
import java.io.*;
import java.net.URL;

public class test {
    public static void main(String[] args) {
        // URL of the PDF file
        String pdfUrl = "https://github.com/asdru22/Secondo-Anno/raw/main/Sistemi%20Operativi/riassunti/4-Semafori.pdf";

        try {
            // Open a connection to the URL
            URL url = new URL(pdfUrl);
            InputStream in = new BufferedInputStream(url.openStream());

            // Save the PDF content to a temporary file
            File tempFile = File.createTempFile("temp_pdf_", ".pdf");
            FileOutputStream out = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            in.close();

            // Open the PDF file using the default PDF viewer on your system
            Desktop.getDesktop().open(tempFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
