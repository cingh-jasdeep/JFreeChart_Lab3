package org.jfree.data;

import org.jfree.data.Range;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit tests for org.jfree.data.Range class Testing methodology: Boundary Value
 * Analysis and Weak Equivalence Class Testing
 *
 * @author Maheen and Dipu
 */
public class RangeTest {

    //#region getCentralValue() tests
    /**
     *
     * #####################################################
     *
     * Test suite for Range class Method: getCentralValue()
     *
     * #####################################################
     */
    /**
     * Test getCentralValue() with a positive range Equivalence Class: Positive
     * ranges Expected: Returns midpoint (4.0)
     */
    @Test
    public void testGetCentralValueWithPositiveRange() {
        Range range = new Range(2.0, 6.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [2.0, 6.0] should be 4.0",
                4.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with a negative range Equivalence Class: Negative
     * ranges Expected: Returns midpoint (-4.0)
     */
    @Test
    public void testGetCentralValueWithNegativeRange() {
        Range range = new Range(-6.0, -2.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [-6.0, -2.0] should be -4.0",
                -4.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with a range crossing zero Equivalence Class:
     * Ranges spanning positive and negative values Expected: Returns midpoint
     * (0.0)
     */
    @Test
    public void testGetCentralValueCrossingZero() {
        Range range = new Range(-1.0, 1.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [-1.0, 1.0] should be 0.0",
                0.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with equal lower and upper bounds Boundary Value:
     * Lower == Upper Expected: Returns the same value
     */
    @Test
    public void testGetCentralValueWithEqualBounds() {
        Range range = new Range(5.0, 5.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [5.0, 5.0] should be 5.0",
                5.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with a very small range Boundary Value: Minimum
     * practical range Expected: Returns accurate midpoint despite small values
     */
    @Test
    public void testGetCentralValueWithVerySmallRange() {
        Range range = new Range(0.0000001, 0.0000003);
        double result = range.getCentralValue();
        assertEquals("Central value of range [0.0000001, 0.0000003] should be 0.0000002",
                0.0000002, result, 0.00000001d);
    }

    /**
     * Test getCentralValue() with a large range Boundary Value: Large positive
     * values Expected: Returns accurate midpoint for large numbers
     */
    @Test
    public void testGetCentralValueWithLargeRange() {
        Range range = new Range(-1000000.0, 1000000.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [-1000000.0, 1000000.0] should be 0.0",
                0.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with decimal precision Boundary Value: Non-integer
     * boundaries Expected: Handles decimal precision correctly
     */
    @Test
    public void testGetCentralValueWithDecimals() {
        Range range = new Range(1.5, 4.5);
        double result = range.getCentralValue();
        assertEquals("Central value of range [1.5, 4.5] should be 3.0",
                3.0, result, 0.0000001d);
    }
    //#endregion

    //#region getLength() tests
    /**
     * *
     * #####################################################
     *
     * Test suite for Range class Method: getLength()
     *
     * #####################################################
     */
    /**
     * Test getLength() with a positive range Equivalence Class: Positive ranges
     * Expected: Returns length (4.0)
     */
    @Test
    public void testGetLengthWithPositiveRange() {
        Range range = new Range(2.0, 6.0);
        double result = range.getLength();
        assertEquals("Length of range [2.0, 6.0] should be 4.0",
                4.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with a negative range Equivalence Class: Negative ranges
     * Expected: Returns positive length (4.0)
     */
    @Test
    public void testGetLengthWithNegativeRange() {
        Range range = new Range(-6.0, -2.0);
        double result = range.getLength();
        assertEquals("Length of range [-6.0, -2.0] should be 4.0",
                4.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with a range crossing zero Equivalence Class: Ranges
     * spanning positive and negative values Expected: Returns total span (10.0)
     */
    @Test
    public void testGetLengthCrossingZero() {
        Range range = new Range(-4.0, 6.0);
        double result = range.getLength();
        assertEquals("Length of range [-4.0, 6.0] should be 10.0",
                10.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with equal lower and upper bounds Boundary Value: Lower
     * == Upper Expected: Returns zero
     */
    @Test
    public void testGetLengthWithEqualBounds() {
        Range range = new Range(5.0, 5.0);
        double result = range.getLength();
        assertEquals("Length of range [5.0, 5.0] should be 0.0",
                0.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with a very small range Boundary Value: Minimum
     * practical range Expected: Returns accurate length for very small values
     */
    @Test
    public void testGetLengthWithVerySmallRange() {
        Range range = new Range(0.0000001, 0.0000005);
        double result = range.getLength();
        assertEquals("Length of range [0.0000001, 0.0000005] should be 0.0000004",
                0.0000004, result, 0.000000001d);
    }

    /**
     * Test getLength() with a large range Boundary Value: Large positive values
     * Expected: Returns accurate length for large numbers
     */
    @Test
    public void testGetLengthWithLargeRange() {
        Range range = new Range(1000000.0, 9000000.0);
        double result = range.getLength();
        assertEquals("Length of range [1000000.0, 9000000.0] should be 8000000.0",
                8000000.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with decimal boundaries Boundary Value: Non-integer
     * boundaries Expected: Handles decimal precision correctly
     */
    @Test
    public void testGetLengthWithDecimals() {
        Range range = new Range(1.5, 4.7);
        double result = range.getLength();
        assertEquals("Length of range [1.5, 4.7] should be 3.2",
                3.2, result, 0.0000001d);
    }

    /**
     * Test getLength() with zero as lower bound Boundary Value: Zero boundary
     * Expected: Returns upper bound value
     */
    @Test
    public void testGetLengthWithZeroLowerBound() {
        Range range = new Range(0.0, 10.0);
        double result = range.getLength();
        assertEquals("Length of range [0.0, 10.0] should be 10.0",
                10.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with zero as upper bound Boundary Value: Zero boundary
     * Expected: Returns absolute value of lower bound
     */
    @Test
    public void testGetLengthWithZeroUpperBound() {
        Range range = new Range(-10.0, 0.0);
        double result = range.getLength();
        assertEquals("Length of range [-10.0, 0.0] should be 10.0",
                10.0, result, 0.0000001d);
    }
    //#endregion

    //#region contains() tests
    /**
     * *
     * #####################################################
     *
     * Test suite for Range class Method: contains()
     *
     * #####################################################
     */
    /**
     * Test contains() with a value below the lower bound Boundary Value: Just
     * below Expected: Returns false (value is not contained)
     */
    @Test
    public void testContainsBelowLowerBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(1.0);
        assertEquals("Range [2.0, 6.0] should not contain 1.0",
                false, result);
    }

    /**
     * Test contains() with a value at the lower bound Boundary Value: Just at
     * lower bound Expected: Returns true (value is contained)
     */
    @Test
    public void testContainsAtLowerBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(2.0);
        assertEquals("Range [2.0, 6.0] should contain 2.0",
                true, result);
    }

    /**
     * Test contains() with a value just after the lower bound Boundary Value:
     * Just after lower bound Expected: Returns true (value is contained)
     */
    @Test
    public void testContainsJustAfterLowerBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(2.1);
        assertEquals("Range [2.0, 6.0] should contain 2.1",
                true, result);
    }

    /**
     * Test contains() with the midpoint value Boundary Value: NOM (midpoint of
     * range) Equivalence Class: V3 — lower < value < upper (within range)
     * Expected: Returns true
     */
    @Test
    public void testContainsAtNominalMidpoint() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(4.0);
        assertEquals("Range [2.0, 6.0] should contain 4.0",
                true, result);
    }

    /**
     * Test contains() with a value just before the upper bound Boundary Value:
     * Just before upper bound Expected: Returns true (value is contained)
     */
    @Test
    public void testContainsJustBeforeUpperBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(5.9);
        assertEquals("Range [2.0, 6.0] should contain 5.9",
                true, result);
    }

    /**
     * Test contains() with a value exactly at the upper bound Boundary Value:
     * UB (upper) Equivalence Class: V4 — value = upper Expected: Returns true
     */
    @Test
    public void testContainsAtUpperBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(6.0);
        assertEquals("Range [2.0, 6.0] should contain 6.0",
                true, result);
    }

    /**
     * Test contains() with a value just above the upper bound Boundary Value:
     * AUB (upper + 1) Equivalence Class: V5 — value > upper (above range)
     * Expected: Returns false
     */
    @Test
    public void testContainsAboveUpperBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(7.0);
        assertEquals("Range [2.0, 6.0] should not contain 7.0",
                false, result);
    }

    /**
     * Test contains() with a very small range Boundary Value Expected: Returns
     * true for values within the small range
     */
    @Test
    public void testContainsWithVerySmallRange() {
        Range range = new Range(0.0000002, 0.0000006);
        boolean result = range.contains(0.0000004);
        assertEquals("Range [0.0000002, 0.0000006] should contain 0.0000004",
                true, result);
    }

    /**
     * Test contains() with a large range Boundary Value: Large positive values
     * Expected: Returns true for values within the large range
     */
    @Test
    public void testContainsWithLargeRange() {
        Range range = new Range(2000000.0, 6000000.0);
        boolean result = range.contains(4000000.0);
        assertEquals("Range [2000000.0, 6000000.0] should contain 4000000.0",
                true, result);
    }

    //#endregion
    //#region constrain() tests
    /**
     * *
     * #####################################################
     *
     * Test suite for Range class Method: constrain()
     *
     * #####################################################
     */
    /**
     * Test constrain() with a value below the lower bound Boundary Value:
     * Expected: Returns -5.0 (lower bound)
     */
    @Test
    public void testConstrainBelowLowerBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(-6.0);
        assertEquals("Constrain of -6.0 on range [-5.0, 10.0] should return -5.0",
                -5.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value exactly at the lower bound Boundary Value
     * Expected: Returns -5.0 (lower bound)
     */
    @Test
    public void testConstrainAtLowerBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(-5.0);
        assertEquals("Constrain of -5.0 on range [-5.0, 10.0] should return -5.0",
                -5.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value just above the lower bound Boundary Value
     * Expected: Returns -4.0
     */
    @Test
    public void testConstrainAboveLowerBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(-4.0);
        assertEquals("Constrain of -4.0 on range [-5.0, 10.0] should return -4.0",
                -4.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with the midpoint value Boundary Value Expected: Returns
     * 2.5
     */
    @Test
    public void testConstrainAtNominalMidpoint() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(2.5);
        assertEquals("Constrain of 2.5 on range [-5.0, 10.0] should return 2.5",
                2.5, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value just below the upper bound Boundary Value
     * Expected: Returns 9.0
     */
    @Test
    public void testConstrainBelowUpperBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(9.0);
        assertEquals("Constrain of 9.0 on range [-5.0, 10.0] should return 9.0",
                9.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value exactly at the upper bound Boundary Value
     * Expected: Returns 10.0
     */
    @Test
    public void testConstrainAtUpperBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(10.0);
        assertEquals("Constrain of 10.0 on range [-5.0, 10.0] should return 10.0",
                10.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value just above the upper bound Boundary Value
     * Expected: Returns 10.0
     */
    @Test
    public void testConstrainAboveUpperBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(11.0);
        assertEquals("Constrain of 11.0 on range [-5.0, 10.0] should return 10.0",
                10.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a very small value Expected: Returns the lower
     * bound for values below lower bound Expected: Returns -5.0
     */
    @Test
    public void testConstrainWithVerySmallRange() {
        Range range = new Range(-5.0, 10);
        double result = range.constrain(-9999999.0);
        assertEquals("Constrain of -9999999.0 on range [-5.0, 10.0] should return -5.0",
                -5.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a very large value Expected: Returns the upper
     * bound for values above the upper bound Expected: Returns 10.0
     */
    @Test
    public void testConstrainWithLargeRange() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(9999999.0);
        assertEquals("Constrain of 9999999.0 on range [-5.0, 10.0] should return 10.0",
                10.0, result, 0.0000001d);
    }
    //#endregion

    //#region intersects() tests
    /**
     * #####################################################
     *
     * Test suite for Range class Method: intersects()
     *
     * #####################################################
     */
    /**
     * Test intersects() with an overlapping range Expected: Returns true
     */
    @Test
    public void testIntersectsWithOverlappingRange() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(4.0, 8.0);
        assertEquals("Range [2.0, 6.0] should intersect with [4.0, 8.0]",
                true, result);
    }

    /**
     * Test intersects() with a range touching the lower boundary Expected:
     * Returns true
     */
    @Test
    public void testIntersectsWithTouchingLowerBoundary() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(-2.0, 2.0);
        assertEquals("Range [2.0, 6.0] should intersect with [-2.0, 2.0]",
                true, result);
    }

    /**
     * Test intersects() with a range touching the upper boundary Expected:
     * Returns true
     */
    @Test
    public void testIntersectsWithTouchingUpperBoundary() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(6.0, 10.0);
        assertEquals("Range [2.0, 6.0] should intersect with [6.0, 10.0]",
                true, result);
    }

    /**
     * Test intersects() with a disjoint range entirely to the left \ Expected:
     * Returns false
     */
    @Test
    public void testIntersectsWithDisjointLeftRange() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(-2.0, 1.0);
        assertEquals("Range [2.0, 6.0] should not intersect with [-2.0, 1.0]",
                false, result);
    }

    /**
     * Test intersects() with a disjoint range entirely to the right
     * Expected:Returns false
     */
    @Test
    public void testIntersectsWithDisjointRightRange() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(7.0, 9.0);
        assertEquals("Range [2.0, 6.0] should not intersect with [7.0, 9.0]",
                false, result);
    }

    /**
     * Test intersects() with an identical range Expected: Returns true
     */
    @Test
    public void testIntersectsWithIdenticalRange() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(2.0, 6.0);
        assertEquals("Range [2.0, 6.0] should intersect with [2.0, 6.0]",
                true, result);
    }

    //#endregion
}
