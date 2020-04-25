package pl.kacper.backend.exception;

public class DomainException extends RuntimeException {
    private CodeException codeException;

    public DomainException(CodeException codeException) {
        this.codeException = codeException;
    }

    public CodeException getCodeException() {
        return codeException;
    }
}
