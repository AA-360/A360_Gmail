package com.automationanywhere.botcommand.samples.commands.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;

public class GmailQuickstart {
    public static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    public static final String TOKENS_DIRECTORY_PATH = "tokens";
    public static Gmail service = null;
    public static String SCOPE = null;

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    public static List<String> SCOPES = null;
    //private static final List SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    public static String CREDENTIALS_FILE_PATH = "";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = new FileInputStream(new File(CREDENTIALS_FILE_PATH));

        //InputStream in = GmailQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        String addrs = System.getProperty("java.io.tmpdir") + "GmailAA360/" + TOKENS_DIRECTORY_PATH+"-"+SCOPE;
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(addrs)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    public static Gmail start()throws IOException , GeneralSecurityException{
        //InputStream in = GmailQuickstart.class.getResourceAsStream("/package.template");
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;
    }


    public List<Message> emails(String UserId,String Q)throws IOException, GeneralSecurityException{
        ListMessagesResponse a  = service.users().messages().list(UserId).setQ(Q).execute();
        List<Message> list = a.getMessages();
        List<Message> output = new ArrayList<>();
        Message mail = null;
        if (list == null || list.isEmpty()) {
            return output;
        } else {
            for (Message msg : list) {
                output.add(getMail(UserId,msg.getId()));
            }
        }
        return output;
    }

    public Message getMail(String UserId,String MessageId)throws IOException{
        return service.users().messages().get(UserId, MessageId).execute();
    }

    public void setScope(String type){
        Map<String, String> SCOPES = new HashMap<>();
        SCOPES.put("READONLY",GmailScopes.GMAIL_READONLY);
        SCOPES.put("COMPOSE",GmailScopes.GMAIL_COMPOSE);
        SCOPES.put("INSERT",GmailScopes.GMAIL_INSERT);
        SCOPES.put("SEND",GmailScopes.GMAIL_SEND);
        SCOPES.put("LABELS",GmailScopes.GMAIL_LABELS);
        SCOPES.put("METADATA",GmailScopes.GMAIL_METADATA);
        SCOPES.put("MODIFY",GmailScopes.GMAIL_MODIFY);
        SCOPES.put("SETTINGS_BASIC",GmailScopes.GMAIL_SETTINGS_BASIC);
        SCOPES.put("SETTINGS_SHARING",GmailScopes.GMAIL_SETTINGS_SHARING);

        this.SCOPE = type;
        this.SCOPES = Collections.singletonList(SCOPES.get(type));
    }

//    public static void main(String... args) throws IOException, GeneralSecurityException {
//        // Build a new authorized API client service.
//        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//
//        // Print the labels in the user's account.
//        String user = "me";
//        ListLabelsResponse listResponse = service.users().labels().list(user).execute();
//        List<Label> labels = listResponse.getLabels();
//        if (labels.isEmpty()) {
//            System.out.println("No labels found.");
//        } else {
//            System.out.println("Labels:");
//            for (Label label : labels) {
//                System.out.printf("- %s\n", label.getName());
//            }
//        }
//    }

}