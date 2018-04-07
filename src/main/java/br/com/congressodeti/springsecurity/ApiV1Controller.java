package br.com.congressodeti.springsecurity;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiV1Controller {

    @PreAuthorize("#oauth2.hasScope('lista_pedidos') and #oauth2.isUser()")
    @GetMapping("/orders")
    public Collection<String> getOrders() {
        return Arrays.asList("");
    }


    @PreAuthorize("#oauth2.hasScope('cadastra_pedidos') and #oauth2.isUser()")
    @PostMapping("/orders")
    public String addOrder(String order) {
        return order;
    }

    @PreAuthorize("#oauth2.hasScope('remove_pedidos') and #oauth2.isUser() and id != null")
    @DeleteMapping("/orders")
    public void delete(Long id) {
    }

    @PreAuthorize("#oauth2.hasScope('lista_produtos') and #oauth2.isUser()")
    @GetMapping("/products")
    public Collection<String> getProducts() {
        return Arrays.asList("");
    }

    @PreAuthorize("#oauth2.hasScope('cadastra_produtos') and #oauth2.isClient()")
    @PostMapping("/products")
    public String addProduct(String prodcut) {
        return prodcut;
    }

}
