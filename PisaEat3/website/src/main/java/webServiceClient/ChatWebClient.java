package webServiceClient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import webServiceClient.entities.BookSessionChat;
import webServiceClient.entities.BookSessionMessage;

public class ChatWebClient {
    public static String PROTOCOL = "http://";
    public static String BASE_URL = "localhost:8081/api/";
    public static String CHAT_API = "chat";

    public static BookSessionChat getChat(String bookSessionId) {
        String targetUrl = PROTOCOL + BASE_URL + CHAT_API + "/" + bookSessionId;

        Client client = ClientBuilder.newClient();
        Response response = client.target(targetUrl).request().get();
        BookSessionChat chat = response.readEntity(BookSessionChat.class);

        response.close();
        client.close();

        return chat;
    }
}
