package validator;  // Java package names should be all lowercase

public enum ValidatorsOptions {

    MIN(1),
    MAX(199);

    private final int limit;  // It should have a field declaration

    private static final int ARG_SIZE = 3;
    ValidatorsOptions(int limit) {
        this.limit = limit;
    }
    public static int getArgSize() {
        return ARG_SIZE;
    }
    public int getLimit() {  // Method name should be a verb, following Java naming conventions
        return limit;
    }

}
