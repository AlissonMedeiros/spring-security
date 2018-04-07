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
public class SpringSecurityApplicationTests {

	@Value("${local.server.port}")
	protected int serverPort;


	@Before
	public void init() {
		RestAssured.port = serverPort;
	}

	@Test
	public void whenHasWrongUserThenReturnError() {
		RestAssured.given()
				.auth()
				.basic("qweqw","sdada")
				.get("/api/me")
				.then()
				.statusCode(HttpStatus.UNAUTHORIZED.value());
	}

	@Test
	public void whenHasManuelUserThenReturnOwner() {
		RestAssured.given()
				.auth()
				.basic("manuel","robertoleal")
				.get("/api/me")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void whenHasMariaUserThenReturnOwner() {
		RestAssured.given()
				.auth()
				.basic("maria","rodarodavira")
				.get("/api/me")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void whenHasMariaAndGetProductsThenReturnProducts() {
		RestAssured.given()
				.auth()
				.basic("maria","rodarodavira")
				.get("/api/products")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void whenHasToniUserThenReturnOwner() {
		RestAssured.given()
				.auth()
				.basic("TONI","toni123")
				.get("/api/me")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void whenHasToniUserThenReturnUnauthorized() {
		RestAssured.given()
				.auth()
				.basic("TONI","toni123")
				.get("/api/products")
				.then()
				.statusCode(HttpStatus.FORBIDDEN.value());
	}

	@Test
	public void whenHasJoazinhoUserThenReturnUnauthorized() {
		RestAssured.given()
				.auth()
				.basic("joao_hacker123","p0rtug4s3mgr4c4")
				.get("/api/products")
				.then()
				.statusCode(HttpStatus.FORBIDDEN.value());
	}

}
