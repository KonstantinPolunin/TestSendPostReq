package org.example;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App {
    private static final String POST_URL = "http://localhost:8080/api/v1/wallet";
    private static final String JSON = "{\"id\":\"1\",\"operation\":\"DEPOSIT\",\"amount\":\"1000\"}";


    public static void main( String[] args ) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        sendPost();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
    private static void sendPost() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(POST_URL);
        StringEntity entity = new StringEntity(JSON, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");
        httpClient.execute(httpPost);

    }
}
