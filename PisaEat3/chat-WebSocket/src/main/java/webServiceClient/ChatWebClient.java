package webServiceClient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import webServiceClient.entities.BookSessionChat;
import webServiceClient.entities.BookSessionMessage;
import webServiceClient.entities.SendBookSessionMessage;

import java.util.logging.Logger;

public class ChatWebClient {
    private static final Logger logger = Logger.getLogger(ChatWebClient.class.getName());
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

    public static BookSessionMessage sendMessage(String bookSessionId, String name, String message) {
        String targetUrl = PROTOCOL + BASE_URL + CHAT_API + "/" + bookSessionId;

        SendBookSessionMessage sendMessage = new SendBookSessionMessage(name, message);

        Client client = ClientBuilder.newClient();
        String response = client.target(targetUrl).request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(sendMessage), String.class);
        logger.info("[DEBUG] " + response);

        BookSessionMessage bookSessionMessage = new BookSessionMessage();
        return bookSessionMessage;
    }
}
