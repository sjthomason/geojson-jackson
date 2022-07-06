package org.geojson.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.geojson.LngLatAlt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LngLatAltDeserializer extends JsonDeserializer<LngLatAlt> {

    @Override
    public LngLatAlt deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.isExpectedStartArrayToken()) {
            return deserializeArray(p, ctxt);
        }
        return (LngLatAlt) ctxt.handleUnexpectedToken(LngLatAlt.class, p);
    }

    protected LngLatAlt deserializeArray(JsonParser p, DeserializationContext ctxt) throws IOException {
        final LngLatAlt node = new LngLatAlt();
        final boolean failOnUnknownFeat = ctxt.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        node.setLongitude(nextDouble(p, ctxt, !failOnUnknownFeat));
        node.setLatitude(nextDouble(p, ctxt, !failOnUnknownFeat));
        node.setAltitude(nextDouble(p, ctxt, true));
        final List<Double> additionalElementsList = new ArrayList<Double>();
        while (p.hasCurrentToken() && p.getCurrentToken() != JsonToken.END_ARRAY) {
            final double element = nextDouble(p, ctxt, true);
            if (!Double.isNaN(element)) {
                additionalElementsList.add(element);
            }
        }
        final double[] additionalElements = new double[additionalElementsList.size()];
        for (int i = 0; i < additionalElements.length; i++) {
            additionalElements[i] = additionalElementsList.get(i);
        }
        node.setAdditionalElements(additionalElements);
        return node;
    }

    private double nextDouble(JsonParser p, DeserializationContext ctxt, boolean optional) throws IOException {
        p.nextToken();
        switch (p.currentTokenId()) {
            case JsonTokenId.ID_STRING:
                return p.getValueAsDouble();
            case JsonTokenId.ID_NUMBER_INT:
            case JsonTokenId.ID_NUMBER_FLOAT:
                return p.getDoubleValue();
            case JsonTokenId.ID_NO_TOKEN:
            case JsonTokenId.ID_END_ARRAY:
            case JsonTokenId.ID_NULL:
                if (optional) {
                    return Double.NaN;
                }
                return ((Number) ctxt.handleUnexpectedToken(Double.TYPE, p)).doubleValue();
            default:
                return ((Number) ctxt.handleUnexpectedToken(Double.TYPE, p)).doubleValue();
            }
    }
}
