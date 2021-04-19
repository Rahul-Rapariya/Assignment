package com.natwest.findprimenumbers.controller;

import com.natwest.findprimenumbers.exception.NotValidInputException;
import com.natwest.findprimenumbers.model.PrimeNumberInput;
import com.natwest.findprimenumbers.service.FindPrimeNumber;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = PrimeNumberApiController.class)
public class PrimeNumberApiControllerTest {
    @MockBean
    FindPrimeNumber findPrimeNumber;

    @Autowired MockMvc mockMvc;

    @Test
    public void findPrimeNumbersTest() throws Exception {
        when(findPrimeNumber.findPrimeNumber((anyInt()), anyInt()))
                        .thenReturn(Arrays.asList(2, 3, 5, 7, 11));
        String json = "{  \n"
                        + "  \"givenNumber\" : 233,\n"
                        + "  \"limit\" : 12\n"
                        + "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bank/v1/findNumbers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json);

        MvcResult mockMvc = this.mockMvc.perform(requestBuilder).andReturn();
        verify(findPrimeNumber).findPrimeNumber(anyInt(), anyInt());
        assertEquals(HttpStatus.OK.value(), mockMvc.getResponse().getStatus());
        System.out.println(mockMvc.getResponse().getErrorMessage());
    }

    @Test
    public void findPrimeNumbersInvalidInputTest() throws Exception {
        NotValidInputException notValidInputException = new NotValidInputException("Pleas");
        when(findPrimeNumber.findPrimeNumber((anyInt()), anyInt()))
                        .thenThrow(notValidInputException);
        String json = "{  \n"
                        + "  \"givenNumber\" : 233,\n"
                        + "  \"limit\" : 0\n"
                        + "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bank/v1/findNumbers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json);

        MvcResult mockMvc = this.mockMvc.perform(requestBuilder).andReturn();
        verify(findPrimeNumber).findPrimeNumber(anyInt(), anyInt());
        assertEquals(HttpStatus.BAD_REQUEST.value(), mockMvc.getResponse().getStatus());
        System.out.println(mockMvc.getResponse().getContentAsString());
    }
}
