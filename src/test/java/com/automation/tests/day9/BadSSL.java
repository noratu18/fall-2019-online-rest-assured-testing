package com.automation.tests.day9;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class BadSSL {

    @Test
    public void badSSLCertificateTest(){
        /*
        No valid certificate --> no handshake, no secure connection
        it gives --> SSLHandShakeException certificate issues
        relaxedHTTPSValidation()--> ignores SSL
        Use relaxed HTTP validation with SSLContext protocol SSL.
        This means that you'll trust all hosts regardless if the SSL certificate is invalid.
        By using this method you don't need to specify a keystore or trust store.
         */
        baseURI = "https://untrusted-root.badssl.com/";
        Response response = RestAssured.given()
                .relaxedHTTPSValidation().get()
                .prettyPeek();
        System.out.println(response.statusCode());

        //regex--> ro check the pattern
        System.out.println("apple".matches("\\D"));


    }
}
