package org.geojson.jackson;

import java.io.IOException;

import org.geojson.LngLatAlt;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LngLatAltSerializer extends JsonSerializer<LngLatAlt> {

    @Override
    public void serialize(LngLatAlt value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        gen.writeNumber(value.getLongitude());
        gen.writeNumber(value.getLatitude());
        if (value.hasAltitude()) {
            gen.writeNumber(value.getAltitude());

            for (double d : value.getAdditionalElements()) {
                gen.writeNumber(d);
            }
        }
        gen.writeEndArray();
    }
}
