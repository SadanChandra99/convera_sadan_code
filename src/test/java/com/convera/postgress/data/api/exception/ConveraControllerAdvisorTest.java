package com.convera.postgress.data.api.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConveraControllerAdvisorTest {

    @InjectMocks
    ConveraControllerAdvisor converaControllerAdvisor;

    @Mock
    DataNotFoundException dataNotFoundException;

    @Mock
    WebRequest request;

    @Test
    void handleDataNotFoundException() {
        Mockito.when(request.getContextPath()).thenReturn("data-api");
        assertNotNull(converaControllerAdvisor.handleDataNotFoundException(dataNotFoundException, request));
    }
}