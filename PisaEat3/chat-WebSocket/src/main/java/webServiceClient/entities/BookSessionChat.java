package webServiceClient.entities;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.Arrays;
import java.util.List;

/*
{
    "list": [...]
}
*/

public class BookSessionChat {
    public List<BookSessionMessage> ListMessages;

    @JsonbCreator
    public BookSessionChat(@JsonbProperty("list") BookSessionMessage[] list) {

        ListMessages = Arrays.asList(list);
    }

    @Override
    public String toString() {
        return "BookSessionChat{" +
                "List=" + ListMessages.toString() +
                '}';
    }
}

