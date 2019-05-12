package com.sherlockcodes.webbtrekk;

import com.sherlockcodes.webbtrekk.entity.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    private static final Logger logger = LogManager.getLogger(IntegrationTest.class);
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private HttpEntity entity;

    @LocalServerPort
    private int port;


    @Test
    public void testPark() throws Exception {

        Email email = new Email();

        email.setAttatchment("");
        email.setBody("lorem");
        email.setSubject("ipsum");
        email.setTo("ayush.berlin.2019@gmail.com");
        HttpEntity entity = new HttpEntity(email, headers);

        ResponseEntity<String> resp = restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, String.class);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);


    }
    @Test
    public void testValidLink() throws Exception {

        Email email = new Email();

        email.setAttatchment("https://storage.cloud.google.com/interstellar-backend-challenge/T33UUP_20180804T100031_B01.tif?_ga=2.149630832.-718932627.1555600501&_gac=1.51714139.1555602810.Cj0KCQjwwODlBRDuARIsAMy_28VmL-IYHWKU-beTeESdbzA2jmbzmyLZhvvYULYU4_WRImrrcZyk-uEaAjK_EALw_wcB");
        email.setBody("lorem");
        email.setSubject("ipsum");
        email.setTo("ayush.berlin.2019@gmail.com");
        HttpEntity entity = new HttpEntity(email, headers);

        ResponseEntity<String> resp = restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, String.class);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);


    }@Test
    public void testEmailInvalid() throws Exception {

        Email email = new Email();


        email.setBody("lorem");
        email.setSubject("ipsum");
        email.setTo("ayush.berlin.201.com");
        HttpEntity entity = new HttpEntity(email, headers);

        ResponseEntity<String> resp = restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, String.class);
        assertEquals(resp.getStatusCode(), HttpStatus.BAD_REQUEST);


    }@Test
    public void testURLInvalid() throws Exception {

        Email email = new Email();

        email.setAttatchment("ewgdrg");
        email.setBody("lorem");
        email.setSubject("ipsum");
        email.setTo("ayush.berlin.2019@gmail.com");
        HttpEntity entity = new HttpEntity(email, headers);

        ResponseEntity<String> resp = restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, String.class);
        assertEquals(resp.getStatusCode(), HttpStatus.BAD_REQUEST);


    }


    private String createURLWithPort() {
        return "http://localhost:" + port + "/email/send";
    }
}