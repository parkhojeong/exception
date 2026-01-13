package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException ex){
        log.info("IllegalArgumentException handler");
        return new ErrorResult("BAD_REQUEST", ex.getMessage());
    }

    @ExceptionHandler/*(UserException.class)*/
    public ResponseEntity<ErrorResult> userExHandler(UserException ex){
        log.info("[exceptionHandler] ex=" + ex);
        ErrorResult errorResult = new ErrorResult("USER-EX", ex.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception ex){
        log.info("[exceptionHandler] ex=" + ex);
        return new ErrorResult("EX", "internal server error");
    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){
        if(id.equals("ex")){
            throw new RuntimeException("ex");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("bad");
        }
        if(id.equals("user-ex")){
            throw new UserException("user-ex");
        }

        return new MemberDto(id, "member-" + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
