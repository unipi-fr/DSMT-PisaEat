package webServiceClient.entities;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.Arrays;

/*
{
    "list": [...]
}
*/

public class BookSessionChat {
    public BookSessionMessage[] List;

    @JsonbCreator
    public BookSessionChat(@JsonbProperty("list") BookSessionMessage[] list) {
        List = list;
    }

    @Override
    public String toString() {
        return "BookSessionChat{" +
                "List=" + Arrays.toString(List) +
                '}';
    }
}

