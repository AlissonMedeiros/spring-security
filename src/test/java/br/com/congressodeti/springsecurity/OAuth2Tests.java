package br.com.congressodeti.springsecurity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OAuth2Tests {


    @Value("${local.server.port}")
    protected int serverPort;


    @Before
    public void init() {
        RestAssured.port = serverPort;
    }

    @Test
    public void whenHasPasswordGrantThenAuthenticate() {
        RestAssured.given()
                .auth()
                .basic("BAR_ZEH", "BAR_ZEH123")
                .queryParam("grant_type", "client_credentials")
                .post("/oauth/token")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenHasPasswordGrantWithJoaoThenAuthenticate() {
        RestAssured.given()
                .auth()
                .basic("BAR_ZEH", "BAR_ZEH123")
                .queryParam("grant_type", "password")
                .queryParam("username", "joao_hacker123")
                .queryParam("password", "p0rtug4s3mgr4c4")
                .post("/oauth/token")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenHasPasswordGrantWithManuelThenAuthenticate() {
        RestAssured.given()
                .auth()
                .basic("BAR_ZEH", "BAR_ZEH123")
                .queryParam("grant_type", "password")
                .queryParam("username", "manuel")
                .queryParam("password", "robertoleal")
                .post("/oauth/token")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenHasPasswordGrantWithWrongPasswordThenReturnError() {
        RestAssured.given()
                .auth()
                .basic("BAR_ZEH", "BAR_ZEH123")
                .queryParam("grant_type", "password")
                .queryParam("username", "manuel")
                .queryParam("password", "123")
                .post("/oauth/token")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
