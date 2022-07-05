package org.geojson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeatureTest {

	private Feature testObject = new Feature();
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void itShouldHaveProperties() throws Exception {
		assertNotNull(testObject.getProperties());
	}

	@Test
	public void itShouldSerializeFeature() throws Exception {
		// http://geojson.org/geojson-spec.html#feature-objects
		// A feature object must have a member with the name "properties".
		// The value of the properties member is an object (any JSON object or a JSON null value).
		assertEquals("{\"type\":\"Feature\",\"properties\":{},\"geometry\":null}",
				mapper.writeValueAsString(testObject));
	}
}
