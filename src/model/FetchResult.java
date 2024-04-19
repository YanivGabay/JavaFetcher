package model;

/**
 * Represents the result of fetching a URL.
 */
public class FetchResult {
    private final String url;
    private final long size; // In bytes
    private final long responseTime; // In milliseconds
    private final String mimeType;
    private final boolean success;
    private final String errorMessage;

    // Constructor
    public FetchResult(String url, long size, long responseTime, String mimeType, boolean success, String errorMessage) {
        this.url = url;
        this.size = size;
        this.responseTime = responseTime;
        this.mimeType = mimeType;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    // Getters
    public String getUrl() {
        return url;
    }

    public long getSize() {
        return size;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public String getMimeType() {
        return mimeType;
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns a string representation of the success status.
     *
     * @return "success" if the fetch was successful, "failure" otherwise.
     */
    public String getSuccess() {
        return this.isSuccess() ? "success" : "failure";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
