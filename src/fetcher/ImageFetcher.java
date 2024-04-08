package fetcher;

import model.FetchResult;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageFetcher implements Fetcher {

    @Override
    public FetchResult fetch(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up the connection properties
            setUpConnection(connection);

            // Validate if the URL points to an image
            if (!isImageUrl(connection)) {
                return notAnImageResult(urlString, connection.getContentType());
            }

            // Process the HTTP response
            return processResponse(urlString, connection);
        } catch (Exception e) {
            return errorResult(urlString, e.getMessage());
        }
    }

    private void setUpConnection(HttpURLConnection connection) throws Exception {
        connection.setRequestMethod("GET");
        connection.connect();
    }

    private boolean isImageUrl(HttpURLConnection connection) {
        String contentType = connection.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    private FetchResult processResponse(String urlString, HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Measure response time, handle content length and MIME type
            long responseTime = 0; // Implement this
            return new FetchResult(urlString, connection.getContentLengthLong(), responseTime, connection.getContentType(), true, null);
        } else {
            return new FetchResult(urlString, -1, -1, null, false, "Non-OK HTTP response: " + responseCode);
        }
    }

    private FetchResult notAnImageResult(String urlString, String contentType) {
        System.err.println("URL does not point to an image: " + urlString);
        return new FetchResult(urlString, -1, -1, contentType, false, "Not an image");
    }

    private FetchResult errorResult(String urlString, String errorMessage) {
        System.err.println("Error fetching URL: " + urlString + " - " + errorMessage);
        return new FetchResult(urlString, -1, -1, null, false, "Error fetching URL: " + errorMessage);
    }
}
