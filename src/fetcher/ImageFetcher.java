package fetcher;

import model.FetchResult;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Fetcher implementation for fetching images from URLs.
 */
public class ImageFetcher implements Fetcher {
    private long startTime;
    @Override
    public FetchResult fetch(String urlString) {
        try {
            startTime = System.nanoTime();
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
    /**
     * Sets up the connection properties and establishes a connection with the given URL.
     *
     * @param connection The URL connection to be set up.
     * @throws Exception If an error occurs while setting up the connection.
     */
    private void setUpConnection(HttpURLConnection connection) throws Exception {
        connection.setRequestMethod("GET");
        connection.connect();
    }

    /**
     * Creates a fetcher instance based on the content type.
     *
     * @param connection the url connection
     * @return bool based on the content
     *
     */
    private boolean isImageUrl(HttpURLConnection connection) {
        String contentType = connection.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    /**
     * Processes the HTTP response from the given URL connection.
     *
     * @param urlString  The URL from which the response was fetched.
     * @param connection The HttpURLConnection representing the connection.
     * @return A FetchResult object representing the result of the fetch operation.
     * @throws Exception If an error occurs during processing of the response.
     */
    private FetchResult processResponse(String urlString, HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Measure response time, handle content length and MIME type
            long elapsedNano = System.nanoTime() - startTime;
            long elapsedMillis = elapsedNano / 1_000_000;
            // Implement additional processing here if needed
            return new FetchResult(urlString, connection.getContentLengthLong(), elapsedMillis, connection.getContentType(), true, null);
        } else {
            return new FetchResult(urlString, -1, -1, null, false, "Non-OK HTTP response: " + responseCode);
        }
    }

    /**
     * Creates a FetchResult object for the case when the URL does not point to an image.
     *
     * @param urlString   The URL for which the fetch operation was attempted.
     * @param contentType The content type of the response.
     * @return A FetchResult object representing the result of the fetch operation.
     */
    private FetchResult notAnImageResult(String urlString, String contentType) {
        System.err.println("URL does not point to an image: " + urlString);
        return new FetchResult(urlString, -1, -1, contentType, false, "Not an image");
    }

    /**
     * Creates a FetchResult object for the case when an error occurs during the fetch operation.
     *
     * @param urlString     The URL for which the fetch operation was attempted.
     * @param errorMessage The error message describing the error.
     * @return A FetchResult object representing the result of the fetch operation.
     */
    private FetchResult errorResult(String urlString, String errorMessage) {
        System.err.println("Error fetching URL: " + urlString + " - " + errorMessage);
        return new FetchResult(urlString, -1, -1, null, false, "Error fetching URL: " + errorMessage);
    }

}
