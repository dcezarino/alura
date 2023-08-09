package alura.jmilhas.api.infra.exception;

import com.theokanning.openai.OpenAiHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import alura.jmilhas.api.domain.ValidationException;
import jakarta.persistence.EntityNotFoundException;

// Anotação que indica que é uma classe de tratamento de erros
@RestControllerAdvice
public class ErrorHandler {

    /*
     Anotação para quando esse método deve ser chamado, se em qualquer controller
     for lançada uma exception do tipo EntityNotFoundException esse método tratarErro404
     deve ser chamado.
    */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
    }

    /*
    Código/Erro400: indica que o servidor não conseguiu processar uma requisição
    por erro de validação nos dados enviados pelo cliente.
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> tratarErroRegraDeNegocio(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    @ExceptionHandler(OpenAiHttpException.class)
    public ResponseEntity<?> tratarErro400(OpenAiHttpException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}