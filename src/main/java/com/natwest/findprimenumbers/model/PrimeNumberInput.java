package com.natwest.findprimenumbers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class PrimeNumberInput {
    int givenNumber;
    int limit;
}
