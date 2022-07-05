package org.geojson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class GeoJsonObjectVisitorTest {

	private GeoJsonObjectVisitor<GeoJsonObject> instance = new GeoJsonObjectVisitor<GeoJsonObject>() {

		@Override
		public GeoJsonObject visit(GeometryCollection geoJsonObject) {
			Assertions.assertEquals(GeometryCollection.class, geoJsonObject.getClass());
			return geoJsonObject;
		}

		@Override
		public GeoJsonObject visit(FeatureCollection geoJsonObject) {
			Assertions.assertEquals(FeatureCollection.class, geoJsonObject.getClass());
			return geoJsonObject;
		}

		@Override
		public GeoJsonObject visit(Point geoJsonObject) {
			Assertions.assertEquals(Point.class, geoJsonObject.getClass());
			return geoJsonObject;
		}

		@Override
		public GeoJsonObject visit(Feature geoJsonObject) {
			Assertions.assertEquals(Feature.class, geoJsonObject.getClass());
			return geoJsonObject;
		}

		@Override
		public GeoJsonObject visit(MultiLineString geoJsonObject) {
			Assertions.assertEquals(MultiLineString.class, geoJsonObject.getClass());
			return geoJsonObject;
		}

		@Override
		public GeoJsonObject visit(Polygon geoJsonObject) {
			Assertions.assertEquals(Polygon.class, geoJsonObject.getClass());
			return geoJsonObject;
		}

		@Override
		public GeoJsonObject visit(MultiPolygon geoJsonObject) {
			Assertions.assertEquals(MultiPolygon.class, geoJsonObject.getClass());
			return geoJsonObject;
		}

		@Override
		public GeoJsonObject visit(MultiPoint geoJsonObject) {
			Assertions.assertEquals(MultiPoint.class, geoJsonObject.getClass());
			return geoJsonObject;
		}

		@Override
		public GeoJsonObject visit(LineString geoJsonObject) {
			Assertions.assertEquals(LineString.class, geoJsonObject.getClass());
			return geoJsonObject;
		}
	};

	@ParameterizedTest
	@MethodSource("getArguments")
	public void should_visit_right_class(GeoJsonObject geoJsonObject) {
		// When
		GeoJsonObject result = geoJsonObject.accept(this.instance);
		// Then
		Assertions.assertEquals(geoJsonObject, result);
	}

	@ParameterizedTest
	@MethodSource("getArguments")
	public void itShouldAdapter(GeoJsonObject geoJsonObject) throws Exception {
		Assertions.assertNull(geoJsonObject.accept(new GeoJsonObjectVisitor.Adapter<Void>()));
	}

	private static Stream<Arguments> getArguments() {
		return Stream.of(
				Arguments.of(new GeometryCollection()),
				Arguments.of(new FeatureCollection()),
				Arguments.of(new Point(12D, 13D)),
				Arguments.of(new Feature()),
				Arguments.of(new MultiLineString(Arrays.asList(new LngLatAlt(12D, 13D)))),
				Arguments.of(new Polygon()),
				Arguments.of(new MultiPolygon()),
				Arguments.of(new MultiPoint()),
				Arguments.of(new LineString()));
	}

}
