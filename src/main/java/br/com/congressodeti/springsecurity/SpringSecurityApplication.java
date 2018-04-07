package br.com.congressodeti.springsecurity;

import javax.sql.DataSource;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
                SimpleGrantedAuthority admin = buildRole("ADMIN");
                SimpleGrantedAuthority userRole = buildRole("USER");
                User manuel = new User("manuel", passwordEncoder.encode("robertoleal"), Lists.newArrayList(admin, userRole));
                User maria = new User("maria", passwordEncoder.encode("rodarodavira"), Lists.newArrayList(admin, userRole));
                User toni = new User("TONI", passwordEncoder.encode("toni123"), Lists.newArrayList(userRole));
                User joazinho = new User("joao_hacker123", passwordEncoder.encode("p0rtug4s3mgr4c4"), Lists.newArrayList(userRole));
                jdbcUserDetailsManager.createUser(manuel);
                jdbcUserDetailsManager.createUser(maria);
                jdbcUserDetailsManager.createUser(toni);
                jdbcUserDetailsManager.createUser(joazinho);
            }

            @NotNull
            private SimpleGrantedAuthority buildRole(String user) {
                return new SimpleGrantedAuthority(user);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
