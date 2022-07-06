package org.geojson.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.LngLatAlt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class LngLatAltDeserializerTest {

    @ParameterizedTest
    @ValueSource(strings = {"[10.0, 15.0, 5.0]", "[10, 15, 5]", "[\"10.0\", \"15.0\", \"5.0\"]"})
    public void testDeserialization(String jsonArray) throws Exception {
        final LngLatAlt expected = new LngLatAlt(10D, 15D, 5D);
        final LngLatAlt lngLatAlt = new ObjectMapper().readValue(jsonArray, LngLatAlt.class);
        Assertions.assertEquals(expected, lngLatAlt);
    }

    @ParameterizedTest
    @ValueSource(strings = {"[10.0, 15.0]", "[10, 15]", "[\"10.0\", \"15.0\"]", "[10.0, 15.0, null]"})
    public void testDeserializationAltOptional(String jsonArray) throws Exception {
        final LngLatAlt expected = new LngLatAlt(10D, 15D);
        final LngLatAlt lngLatAlt = new ObjectMapper().readValue(jsonArray, LngLatAlt.class);
        Assertions.assertEquals(expected, lngLatAlt);
    }

    @ParameterizedTest
    @ValueSource(strings = {"[10.0]", "[10.0, null]", "[null, 15.0]", "[null]", "10.0"})
    public void requiresLngLatByDefault(String jsonArray) throws Exception {
        Assertions.assertThrows(JsonMappingException.class, () -> {
            new ObjectMapper().readValue(jsonArray, LngLatAlt.class);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"[10.0]", "[10.0, null]", "[null, 15.0]", "[null]"})
    public void optionallyIgnoresInvalidInput(String jsonArray) throws Exception {
        final LngLatAlt lngLatAlt = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .readValue(jsonArray, LngLatAlt.class);
        Assertions.assertNotNull(lngLatAlt);
    }

    @Test
    public void testDeserializationAdditionalElements() throws Exception {
        final LngLatAlt expected = new LngLatAlt(10D, 15D, 5D, 4D, 3D, 2D, 1D);
        final LngLatAlt lngLatAlt = new ObjectMapper().readValue("[10.0, 15.0, 5.0, 4.0, 3.0, 2.0, 1.0]", LngLatAlt.class);
        Assertions.assertEquals(expected, lngLatAlt);
    }

    @Test
    public void testDeserializationNull() throws Exception {
        final LngLatAlt lngLatAlt = new ObjectMapper().readValue("null", LngLatAlt.class);
        Assertions.assertNull(lngLatAlt);
    }
}
