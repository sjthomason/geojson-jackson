package org.geojson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LngLatAltTest {

	@Test
	public void should_LngLatAlt_equals_without_alt() {
		LngLatAlt first = new LngLatAlt(14.D, 13.D);
		LngLatAlt second = new LngLatAlt(14.D, 13.D);
		Assertions.assertEquals(second, first);
	}

	@Test
	public void should_LngLatAlt_equals_with_alt() {
		LngLatAlt first = new LngLatAlt(14.D, 13.D, 15D);
		LngLatAlt second = new LngLatAlt(14.D, 13.D, 15D);
		Assertions.assertEquals(second, first);
	}

	@Test
	public void should_not_LngLatAlt_equals_with_alt() {
		LngLatAlt first = new LngLatAlt(14.D, 13.D, 15D);
		LngLatAlt second = new LngLatAlt(14.D, 13.D, 16D);
		Assertions.assertNotEquals(second, first);
	}

	@Test
	public void should_not_LngLatAlt_equals_without_alt() {
		LngLatAlt first = new LngLatAlt(14.D, 14.D, 15D);
		LngLatAlt second = new LngLatAlt(14.D, 13.D, 16D);
		Assertions.assertNotEquals(second, first);
	}

	@Test
	public void should_LngLatAlt_equals_with_additional_elements() {
		LngLatAlt first = new LngLatAlt(14.D, 14.D, 15D, 16D, 17D);
		LngLatAlt second = new LngLatAlt(14.D, 14.D, 15D, 16D, 17D);
		Assertions.assertEquals(second, first);
		Assertions.assertEquals(second.hashCode(), first.hashCode());
	}

	@Test
	public void should_LngLatAlt_equals_with_additional_elements_and_null() {
		LngLatAlt first = new LngLatAlt(14.D, 14.D, 15D, 16D, 17D);
		LngLatAlt second = new LngLatAlt(14.D, 14.D, 15D, 16D, 17D);
		Assertions.assertEquals(second, first);
		Assertions.assertEquals(second.hashCode(), first.hashCode());
	}

	@Test
	public void should_not_LngLatAlt_equals_without_additional_elements() {
		LngLatAlt first = new LngLatAlt(14.D, 14.D, 15D, 16D, 17D);
		LngLatAlt second = new LngLatAlt(14.D, 14.D, 15D);
		Assertions.assertNotEquals(second, first);
		Assertions.assertNotEquals(second.hashCode(), first.hashCode());
	}

	@Test
	public void should_not_LngLatAlt_equals_with_additional_elements_in_different_order() {
		LngLatAlt first = new LngLatAlt(14.D, 14.D, 15D, 16D, 17D);
		LngLatAlt second = new LngLatAlt(14.D, 14.D, 15D, 17D, 16D);
		Assertions.assertNotEquals(second, first);
		Assertions.assertNotEquals(second.hashCode(), first.hashCode());
	}

	@Test
	public void should_not_LngLatAlt_equals_with_additional_elements_and_different_size() {
		LngLatAlt first = new LngLatAlt(14.D, 14.D, 15D, 16D, 17D);
		LngLatAlt second = new LngLatAlt(14.D, 14.D, 15D, 16D, 17D, 18D);
		Assertions.assertNotEquals(second, first);
		Assertions.assertNotEquals(second.hashCode(), first.hashCode());
	}

	@Test
	public void should_LngLatAlt_throw_if_alt_not_specified_in_constructor() {
		try {
			new LngLatAlt(14.D, 14.D, Double.NaN, 16D, 17D);
			Assertions.fail("Additional elements are not allowed if altitude is Nan.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_alt_set_to_Nan_with_additional_elements() {
		LngLatAlt lngLatAlt = new LngLatAlt(14.D, 14.D, 15.D, 16D, 17D);

		try {
			lngLatAlt.setAltitude(Double.NaN);
			Assertions.fail("Additional elements are not allowed if altitude is Nan.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_additional_elements_set_with_missing_alt() {
		LngLatAlt lngLatAlt = new LngLatAlt(14.D, 14.D);

		try {
			lngLatAlt.setAdditionalElements(42);
			Assertions.fail("Additional elements are not allowed if altitude is Nan.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_additional_elements_set_with_Nan_alt() {
		LngLatAlt lngLatAlt = new LngLatAlt(14.D, 14.D, Double.NaN);

		try {
			lngLatAlt.setAdditionalElements(42);
			Assertions.fail("Additional elements are not allowed if altitude is Nan.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_any_additional_elements_constructed_to_Nan() {
		try {
			new LngLatAlt(14.D, 14.D, 15.D, 16.D, Double.NaN, 17.D);
			Assertions.fail("Additional elements are not allowed to be Nan.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_any_additional_elements_constructed_to_Positive_Infinity() {
		try {
			new LngLatAlt(14.D, 14.D, 15.D, 16.D, Double.POSITIVE_INFINITY, 17.D);
			Assertions.fail("Additional elements are not allowed to be positive infinity.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_any_additional_elements_constructed_to_Negative_Infinity() {
		try {
			new LngLatAlt(14.D, 14.D, 15.D, 16.D, Double.NEGATIVE_INFINITY, 17.D);
			Assertions.fail("Additional elements are not allowed to be positive infinity.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_any_additional_elements_set_to_Nan() {
		LngLatAlt lngLatAlt = new LngLatAlt(14.D, 14.D, 15.D);
		try {
			lngLatAlt.setAdditionalElements(16.D, Double.NaN, 17.D);
			Assertions.fail("Additional elements are not allowed to be Nan.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_any_additional_elements_set_to_Positive_Infinity() {
		LngLatAlt lngLatAlt = new LngLatAlt(14.D, 14.D, 15.D);
		try {
			lngLatAlt.setAdditionalElements(16.D, Double.POSITIVE_INFINITY, 17.D);
			Assertions.fail("Additional elements are not allowed to be positive infinity.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}

	@Test
	public void should_LngLatAlt_throw_if_any_additional_elements_set_to_Negative_Infinity() {
		LngLatAlt lngLatAlt = new LngLatAlt(14.D, 14.D, 15.D);
		try {
			lngLatAlt.setAdditionalElements(16.D, Double.NEGATIVE_INFINITY, 17.D);
			Assertions.fail("Additional elements are not allowed to be positive infinity.");
		} catch (IllegalArgumentException e) {
			Assertions.assertTrue(true, "Expected exception.");
		}
	}
}
