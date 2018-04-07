package br.com.congressodeti.springsecurity;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/me")
    public Principal getUserName(Principal principal) {
        return principal;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/products")
    public Collection<String> getProducts() {
        return Collections.singleton("Pão frances");
    }


}
