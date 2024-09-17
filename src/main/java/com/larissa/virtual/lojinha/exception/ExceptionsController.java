package com.larissa.virtual.lojinha.exception;

import com.larissa.virtual.lojinha.dto.ErrorObjectDto;
import com.larissa.virtual.lojinha.service.SendEmailService;
import jakarta.mail.MessagingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {
    @Autowired
    SendEmailService emailService;
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorObjectDto objectDto = new ErrorObjectDto();
        objectDto.setError("Não há dados no body da requisição.");
        objectDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));

        return new ResponseEntity<>(objectDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExceptionLoja.class)
    public ResponseEntity<Object> handleCustomException(ExceptionLoja ex){
        ErrorObjectDto objectDto = new ErrorObjectDto();
        objectDto.setError(ex.getMessage());
        objectDto.setCode(String.valueOf(HttpStatus.OK));

        return new ResponseEntity<>(objectDto, HttpStatus.OK);
    }
    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ErrorObjectDto objectDto = new ErrorObjectDto();

        String msg = "";

        if (ex instanceof MethodArgumentNotValidException){
            List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError error : list) {
                msg += error.getDefaultMessage() + "\n";
            }
        } else{
            msg = ex.getMessage();
        }
        objectDto.setError(msg);
        objectDto.setCode(statusCode.toString());

        ex.printStackTrace();

        try {
            emailService.sendEmailHtml("Erro na loja virtual", ExceptionUtils.getStackTrace(ex), "lari.f4ria@gmail.com");
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(objectDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
    protected ResponseEntity<Object> handleExceptionDataIntegrity(Exception ex){
        ErrorObjectDto objectDto = new ErrorObjectDto();

        String msg = "";

        if (ex instanceof SQLException){
            msg = ex.getCause().getCause().getMessage();
        } else {
            msg = ex.getMessage();
        }
        objectDto.setError(msg);
        objectDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        ex.printStackTrace();

        try {
            emailService.sendEmailHtml("Erro na loja virtual", ExceptionUtils.getStackTrace(ex), "lari.f4ria@gmail.com");
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        return new ResponseEntity<>(objectDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
