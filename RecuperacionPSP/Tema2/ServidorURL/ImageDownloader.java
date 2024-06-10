package ServidorURL;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageDownloader {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ImageDownloader <URL>");
            return;
        }

        String urlString = args[0];
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            StringBuilder htmlContent = new StringBuilder();
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                htmlContent.append(new String(buffer, 0, bytesRead));
            }
            inputStream.close();

            ArrayList<String> imageUrls = extractImageUrls(htmlContent.toString(), urlString);
            downloadImages(imageUrls);

        } catch (MalformedURLException e) {
            System.out.println("URL mal formada: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    private static ArrayList<String> extractImageUrls(String htmlContent, String baseUrl) {
        ArrayList<String> imageUrls = new ArrayList<>();
        Pattern pattern = Pattern.compile("<img[^>]+src=[\"']([^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            String imgSrc = matcher.group(1);
            if (!imgSrc.startsWith("http")) {
                imgSrc = baseUrl + imgSrc;
            }
            imageUrls.add(imgSrc);
        }

        return imageUrls;
    }

    private static void downloadImages(ArrayList<String> imageUrls) {
        for (String imageUrl : imageUrls) {
            try {
                URL url = new URL(imageUrl);
                String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                fileOutputStream.close();
                inputStream.close();

                System.out.println("Imagen descargada: " + fileName);

            } catch (MalformedURLException e) {
                System.out.println("URL mal formada: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            }
        }
    }
}
