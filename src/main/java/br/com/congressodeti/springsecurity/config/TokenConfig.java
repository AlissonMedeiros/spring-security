package br.com.congressodeti.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

@Component
public class TokenConfig {

    @Bean
        public TokenStore tokenStore(ApprovalStore approvalStore) {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtTokenEnhancer());
        jwtTokenStore.setApprovalStore(approvalStore);
        return jwtTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "842334yhrwejnsdfjsdf".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }

    @Bean
    public DefaultTokenServices defaultTokenServices(TokenStore tokenStore) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        return defaultTokenServices;
    }

}
