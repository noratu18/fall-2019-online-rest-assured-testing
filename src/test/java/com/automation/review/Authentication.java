package com.automation.review;

import org.kohsuke.rngom.parse.host.Base;

import java.util.Base64;

public class Authentication {

    public static void main(String[] args) {

        byte[] decoded = Base64.getDecoder().decode("YWRtaW46YWRtaW4=");



        String value = new String(decoded);

        System.out.println(value);


        String encoding = Base64.getEncoder().encodeToString("Success very soon!".getBytes());
        System.out.println(encoding);
    }

}
