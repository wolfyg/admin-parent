package cn.ctx.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller  
public class MainsiteErrorController implements ErrorController {  
    private static final String ERROR_PATH = "/error";  
    @RequestMapping(value=ERROR_PATH)  
    public String handleError(){  
        return "hplus/error";  
    }  
    @Override  
    public String getErrorPath() {  
        return ERROR_PATH;  
    }  
}
