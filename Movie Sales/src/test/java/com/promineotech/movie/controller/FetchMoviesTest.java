package com.promineotech.movie.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.promineotech.movie.controller.support.BaseTest;
import com.promineotech.movie.entity.Movie;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doThrow;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

class FetchMoviesTest extends BaseTest {

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @ActiveProfiles("test")
    @Sql(scripts = {
            "classpath:flyway/migrations/V1.0__Movies_Schema.sql",
            "classpath:flyway/migrations/V1.1__Movies_Data.sql"},
            config = @SqlConfig(encoding = "utf-8"))
    class TestsThatDoNotPolluteTheApplicationContext extends BaseTest {
        @Test
        void testThatMoviesAreReturnedWhenCalled() {
            // Given: a valid model, trim and URI
            String uri = getBaseUriForMovies();

            // When: a connection is made to the URI
            ResponseEntity<List<Movie>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });

            // Then: a success-OK status code is returned
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
        /*@Test
        void testThatAnErrorMessageIsReturnedWhenAnUnknownValueIsSupplied() {
            // Given: a valid model, trim and URI
            JeepModel model = JeepModel.WRANGLER;
            String trim = "Unknown Value";
            String uri = String.format("%s?model=%s&trim=%s", getBaseUriForMovies(), model, trim);

            // When: a connection is made to the URI
            ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

            // Then: a not found (404) status code is returned
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

            // And: an error message is returned
            Map<String, Object> error = response.getBody();

            assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
        }
        @ParameterizedTest
        @MethodSource("com.promineotech.jeep.controller.FetchJeepTest#parametersForInvalidInput")
        void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(String model, String trim, String reason) {
            // Given: a valid model, trim and URI
            String uri = String.format("%s?model=%s&trim=%s", getBaseUriForMovies(), model, trim);

            // When: a connection is made to the URI
            ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

            // Then: a not found (404) status code is returned
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

            // And: an error message is returned
            Map<String, Object> error = response.getBody();

            assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
        }
    }

    static Stream<Arguments> parametersForInvalidInput() {
        return Stream.of(
                arguments("WRANGLER", "@$%%##@", "Trim contains non-alpha-numeric characters"),
                arguments("WRANGLER", "C".repeat(Constants.TRIM_MAX_LENGTH +1), "Trim length too long"),
                arguments("INVALID", "Sport", "Model is not enum value")
        );
    }

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @ActiveProfiles("test")
    @Sql(scripts = {
            "classpath:flyway/migrations/V1.0__Movies_Schema.sql",
            "classpath:flyway/migrations/V1.1__Movies_Data.sql"},
            config = @SqlConfig(encoding = "utf-8"))
    class TestsThatPolluteTheApplicationContext extends FetchJeepTestSupport {
        @MockBean
        private MovieService jeepSalesService;

        @Test
        void testThatAnUnplannedErrorResultsInA500Status() {
            // Given: a valid model, trim and URI
            JeepModel model = JeepModel.WRANGLER;
            String trim = "Invalid";
            String uri = String.format("%s?model=%s&trim=%s", getBaseUriForMovies(), model, trim);

            doThrow(new RuntimeException("Ouch!")).when(jeepSalesService).fetchJeeps(model, trim);

            // When: a connection is made to the URI
            ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

            // Then: an internal server error (500) status code is returned
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

            // And: an error message is returned
            Map<String, Object> error = response.getBody();

            assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    */
    }
}