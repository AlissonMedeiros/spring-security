package br.com.congressodeti.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.stereotype.Component;

@Component
public class ApprovalStoreConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ApprovalStore getApprovalStore() {
        return new JdbcApprovalStore(dataSource);
    }

}
