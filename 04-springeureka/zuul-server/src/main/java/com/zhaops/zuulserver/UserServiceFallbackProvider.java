package com.zhaops.zuulserver;


import org.checkerframework.checker.compilermsgs.qual.CompilerMessageKey;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author SoYuan
 */
@Component
public class UserServiceFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "userservice";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String mockUserJson = "{\n" +
                        "  \"id\": -3,\n" +
                        "  \"nickname\": \"fakeUser\",\n" +
                        "  \"avatar\": \"/users/avatar/user.png\"\n" +
                        "}";
                return new ByteArrayInputStream(mockUserJson.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
