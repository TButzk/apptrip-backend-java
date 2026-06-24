package unisinos.apptrip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import unisinos.apptrip.model.shared.DtoResponse;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<DtoResponse<Void>> handleApi(ApiException exception) {
        return ResponseEntity.status(exception.getStatus()).body(DtoResponse.error(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DtoResponse<Void>> handleValidation(MethodArgumentNotValidException exception) {
        var field = exception.getBindingResult().getFieldErrors().stream().findFirst();
        var message = field.map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Dados inválidos.");
        return ResponseEntity.badRequest().body(DtoResponse.error(message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DtoResponse<Void>> handleUnreadableBody() {
        return ResponseEntity.badRequest().body(DtoResponse.error("Corpo da requisição inválido."));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DtoResponse<Void>> handleMissingParameter(MissingServletRequestParameterException exception) {
        var message = "Parâmetro obrigatório ausente: " + exception.getParameterName() + ".";
        return ResponseEntity.badRequest().body(DtoResponse.error(message));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DtoResponse<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException exception) {
        var name = exception.getName() == null ?"parâmetro" : exception.getName();
        var message = "Formato inválido para o parâmetro: " + name + ".";
        return ResponseEntity.badRequest().body(DtoResponse.error(message));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<DtoResponse<Void>> handleCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(DtoResponse.error("E-mail ou senha inválidos."));
    }

    @ExceptionHandler({AccessDeniedException.class, SecurityException.class})
    public ResponseEntity<DtoResponse<Void>> handleForbidden(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(DtoResponse.error(exception.getMessage() == null ?"Acesso negado." : exception.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<DtoResponse<Void>> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DtoResponse.error("Recurso não encontrado."));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<DtoResponse<Void>> handleUploadSize() {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(DtoResponse.error("O arquivo excede o tamanho permitido."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DtoResponse<Void>> handleUnexpected(Exception exception) {
        return ResponseEntity.internalServerError().body(DtoResponse.error("Erro interno do servidor."));
    }
}
