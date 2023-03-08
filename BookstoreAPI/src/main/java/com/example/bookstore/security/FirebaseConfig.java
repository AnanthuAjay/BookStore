package com.example.bookstore.security;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.databaseUrl}")
    private String databaseUrl;

    @Value("${firebase.credentialsPath}")
    private String credentialsPath;

    @Bean
    public FirebaseDatabase firebaseDatabase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(credentialsPath);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();
        FirebaseApp.initializeApp(options);
        return FirebaseDatabase.getInstance();
    }
}

