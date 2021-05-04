package io.github.arumoy.covaxxer.config;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class TokenHolder {
    private String token;

    public void update(String token) {
        this.token = token;
    }

    public Map<String, String> h() {
        if (!isEmpty())
            return Collections.singletonMap(HttpHeaders.AUTHORIZATION, this.token);
        else
            return Collections.emptyMap();
    }

    public boolean isEmpty() {
        return this.token == null;
    }

    public String token() {
        return token;
    }
}
