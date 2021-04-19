package com.natwest.findprimenumbers.service;

import com.natwest.findprimenumbers.exception.NotValidInputException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindPrimeNumberImpl implements FindPrimeNumber {

    private static final boolean TRUE = true;

    @Override
    public List<Integer> findPrimeNumber(int givenNumber, int resultLimit) {
        validateInput(givenNumber, resultLimit);
        boolean[] completeList = populatePrimeNumbers(givenNumber);
        List<Integer> primeNumbersList = filterPrimeNumbersUpToLimit(givenNumber, completeList, resultLimit);
        return primeNumbersList;
    }

    private List<Integer> filterPrimeNumbersUpToLimit(int givenNumber, boolean[] primeNumbers, int resultLimit) {
        List<Integer> primeNumbersList = new ArrayList<>();
        for (int k = 2; k <= givenNumber && resultLimit != 0; k++) {
            if (primeNumbers[k] == TRUE) {
                primeNumbersList.add(k);
                resultLimit--;
            }
        }
        return primeNumbersList;
    }

    private boolean[] populatePrimeNumbers(int number) {
        boolean[] primeNumbers = new boolean[number + 1];
        //first assume that all are prime numbers
        for (int i = 0; i <= number; i++) {
            primeNumbers[i] = TRUE;
        }

        for (int p = 2; p * p <= number; p++) {
            if (primeNumbers[p] == TRUE) {
                //mark multiple of prime numbers to false as they would be composite i.e. not prime
                for (int j = (p * p); j <= number; j = (j + p)) {
                    primeNumbers[j] = false;
                }
            }
        }
        return primeNumbers;
    }

    private void validateInput(int givenNumber, int resultLimit) {
        if ((givenNumber < 2)) {
            throw new NotValidInputException("Please enter a Valid Number");
        }
        if ((resultLimit >= givenNumber) || resultLimit < 1) {
            throw new NotValidInputException("Please enter a Valid Limit");
        }
    }

}
