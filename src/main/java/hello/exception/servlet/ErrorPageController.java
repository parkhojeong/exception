package hello.exception.servlet;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ErrorPageController {
    @RequestMapping("/error-page/404")
    public String  error404(HttpServletRequest request, HttpServletRequest response) {
        log.info("error404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String  error500(HttpServletRequest request, HttpServletRequest response) {
        log.info("error500");
        return "error-page/500";
    }
}
