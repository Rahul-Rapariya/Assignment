package com.natwest.findprimenumbers.service;

import com.natwest.findprimenumbers.exception.NotValidInputException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FindPrimeNumberTest {
    @InjectMocks
    FindPrimeNumberImpl findPrimeNumber;

    @Test
    public void tesIfPrimeWithLowestPossibleNumber() {
        List<Integer> actual = findPrimeNumber.findPrimeNumber(2, 1);
        assertEquals(1, actual.size());
    }

    @Test
    public void testIfPrimeWithLargeNumbersAndSmallLimit() {
        List<Integer> actual = findPrimeNumber.findPrimeNumber(1000, 10);
        assertEquals(10, actual.size());
    }

    @Test
    public void testIfPrimeWithLargeNumbersAndLargeLimit() {
        List<Integer> actual = findPrimeNumber.findPrimeNumber(1000, 999);
        assertEquals(168, actual.size());
    }

    @Test(expected = NotValidInputException.class)
    public void testIfPrimeWithInvalidLimit() {
        findPrimeNumber.findPrimeNumber(2, 4);
    }

    @Test(expected = NotValidInputException.class)
    public void testIfPrimeWithInvalidNumber() {
        findPrimeNumber.findPrimeNumber(1, 0);
    }

    @Test
    public void testIfPrimeWithInvalidLimitGiveExpectedMessage() {
        try {
            findPrimeNumber.findPrimeNumber(2, 4);
        } catch (NotValidInputException exception) {
            assertEquals("Please enter a Valid Limit", exception.getMessage());
        }
    }

    @Test
    public void testIfPrimeWithInvalidNumberGiveExpectedMessage() {
        try {
            findPrimeNumber.findPrimeNumber(-66, 1);
        } catch (NotValidInputException exception) {
            assertEquals("Please enter a Valid Number", exception.getMessage());
        }
    }

}
