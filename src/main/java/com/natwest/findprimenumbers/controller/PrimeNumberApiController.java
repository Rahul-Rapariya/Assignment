package com.natwest.findprimenumbers.controller;

import com.natwest.findprimenumbers.exception.NotValidInputException;
import com.natwest.findprimenumbers.model.PrimeNumberInput;
import com.natwest.findprimenumbers.service.FindPrimeNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/v1")
public class PrimeNumberApiController {
    FindPrimeNumber findPrimeNumber;

    @Autowired PrimeNumberApiController(FindPrimeNumber findPrimeNumber) {
        this.findPrimeNumber = findPrimeNumber;
    }

    @PostMapping("/findNumbers")
    public ResponseEntity findPrimeNumbers(@RequestBody PrimeNumberInput input) {
        int inputNumber = input.getGivenNumber();
        int limit = input.getLimit();
        try {
            return ResponseEntity.ok(findPrimeNumber.findPrimeNumber(inputNumber, limit));
        } catch (NotValidInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
