package com.cg.in.controller;



import com.cg.in.helper.RestTemplateHelper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {

    

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WireMockServer wireMockServer;

    @BeforeEach
    void setup() {
        // Reset WireMock server before each test
        wireMockServer.resetAll();
    }

    @Test
    void testGetAllEmployeesRestTemplate() {
        // Set up a mock response
        wireMockServer.stubFor(get(urlEqualTo("/employee"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"empId\":1,\"empName\":\"ram\",\"empDesignation\":\"Developer\",\"salary\":60000}]")
                        .withStatus(200)));

        // Call the controller endpoint
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + 8080 + "/employee", String.class);

        System.out.println(response.getBody());
       
        // Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("ram");
    }
}
