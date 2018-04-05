package br.com.congressodeti.springsecurity;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.testcontainers.shaded.com.google.common.collect.Lists;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner init(DataSource dataSource, PasswordEncoder passwordEncoder) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User manuel = new User("manuel", passwordEncoder.encode("robertoleal"), Lists.newArrayList(new SimpleGrantedAuthority("USER")));
                User maria = new User("maria", passwordEncoder.encode("rodarodavira"), Lists.newArrayList(new SimpleGrantedAuthority("USER")));
                User toni = new User("TONI", passwordEncoder.encode("toni123"), Lists.newArrayList(new SimpleGrantedAuthority("USER")));
                jdbcUserDetailsManager.createUser(manuel);
                jdbcUserDetailsManager.createUser(maria);
                jdbcUserDetailsManager.createUser(toni);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
