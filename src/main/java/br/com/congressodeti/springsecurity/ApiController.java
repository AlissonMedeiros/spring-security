package br.com.congressodeti.springsecurity;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/me")
    public String getUserName(Principal principal){
        return principal.getName();
    }

}
