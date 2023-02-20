package app.blog.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> validationExceptionHandler(MethodArgumentNotValidException am,WebRequest wb){
		
        Map<String,String> map=new HashMap<>();

        am.getBindingResult().getAllErrors().forEach((error)->{
        	String fieldName=((FieldError)error).getField();
        	String mess=error.getDefaultMessage();
        	map.put(fieldName, mess);
        });
		
//		MyErrorDetails err=new MyErrorDetails();
//		err.setTStamp(LocalDateTime.now());
//		err.setMessage(am.getMessage());
//		err.setDescription(wb.getDescription(false));
		
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException ue,WebRequest wb){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTStamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDescription(wb.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<MyErrorDetails> categoryExceptionHandler(CategoryException ue,WebRequest wb){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTStamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDescription(wb.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<MyErrorDetails> postExceptionHandler(PostException ue,WebRequest wb){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTStamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDescription(wb.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception ue,WebRequest wb){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTStamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDescription(wb.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}

}
