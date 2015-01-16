package net.blog.dev.services.jackson.deserializer;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/**
 * Created by romainn on 16/01/2015.
 */
public class CleanFloatDeserializer extends JsonDeserializer<Float> {

    @Override
    public Float deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        if (node.asText() != null)
            return Float.parseFloat(node.asText().replaceAll("[^0-9.]*", ""));
        return null;
    }
}