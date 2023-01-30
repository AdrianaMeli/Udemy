package com.example.carros.domain.upload;

import com.example.carros.api.upload.UploadInput;
import com.google.api.services.storage.model.Bucket;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

@Service
public class FirebaseStorageService {

    @PostConstruct
    private void init() throws IOException {
        if(FirebaseApp.getApps().isEmpty()) {
            InputStream in =
                    FirebaseStorageService.class.getResourceAsStream("/serviceAccountKey.json");

            System.out.println(in);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(in))
                    .setStorageBucket("carros-flutterweb.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        }
    }

    public String upload(UploadInput uploadInput) {


        Bucket bucket = StorageClient.getInstance().bucket();
        System.out.println(bucket);

//        Blob blob = bucket.create("nome.txt","Ricardo Ninja Lecheta".getBytes(), "text/html");

        byte[] bytes = Base64.getDecoder().decode(uploadInput.getBase64());

        String fileName = uploadInput.getFileName();
        Blob blob = bucket.create(fileName,bytes, uploadInput.getMimeType());

        // Assina URL válida por N dias
//        URL signedUrl = blob.signUrl(3, TimeUnit.DAYS);

        // Deixa URL pública
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        return String.format("https://storage.googleapis.com/%s/%s",bucket.getName(),fileName);
    }
}

/**
 service firebase.storage {
 match /b/{bucket}/o {
 match /{allPaths=**} {
 allow read, write: if request.auth != null;
 }
 }
 }
 */
