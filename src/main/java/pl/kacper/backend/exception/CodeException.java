package pl.kacper.backend.exception;

public enum CodeException {
    USER_DUPLICATE_FOUND("U_001", "User duplicate exception", "The given login already exists"),
    USER_NOT_FOUND("U_002","User not found", "The user with the given id was not found in the set"),
    COMMENT_DUPLICATE_FOUND("C_001", "Comment duplicate exception", "The given comment already exists"),
    COMMENT_NOT_FOUND("C_002", "Comment not found", "The comment with the given id was not found in the set");

    private String code;
    private String message;
    private String details;

    CodeException(String code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
