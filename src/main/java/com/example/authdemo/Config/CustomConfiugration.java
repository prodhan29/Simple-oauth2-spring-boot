package com.example.authdemo.Config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashMap;
import java.util.Map;

public class CustomConfiugration {

    public static PasswordEncoder getPasswordEncoder() {

        Map encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        PasswordEncoder encoder=  new DelegatingPasswordEncoder("bcrypt", encoders);
        return encoder;
    }
}
