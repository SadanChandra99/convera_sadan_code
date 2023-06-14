package com.convera.postgress.data.api.web;



//
//
//
//
//
//import com.convera.common.template.CommonResponse;
//import com.convera.common.template.response.error.constants.ResponseErrorCode404;
//import com.convera.common.template.response.error.constants.ResponseErrorCode500;
//import com.convera.common.template.response.util.CommonResponseUtil;
//import com.convera.postgress.data.api.repository.model.Widget;
//import com.convera.postgress.data.api.repository.model.WidgetsID;
//import com.convera.postgress.data.api.service.WidgetsService;
//import datadog.trace.api.Trace;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//class WidgetControllerTest {
//    @Mock
//    private WidgetsService widgetsService;
//
//    @InjectMocks
//    private WidgetController widgetController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetWidgets() {
//        // Mocking the necessary objects
//        String correlationId = "123456";
//        List<Widget> widgets = new ArrayList<>();
//        widgets.add(new Widget());
//
//        when(widgetsService.getAllWidgets()).thenReturn(widgets);
//
//        // Calling the method under test
//        ResponseEntity<CommonResponse<List<Widget>>> response = widgetController.getWidgets(correlationId);
//
//        // Verifying the interactions and assertions
//        verify(widgetsService, times(1)).getAllWidgets();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertTrue(response.getBody().getData());
//        assertEquals(widgets, response.getBody().getData());
//    }
//
//    @Test
//    void testGetWidgets_NotFound() {
//        // Mocking the necessary objects
//        String correlationId = "123456";
//
//        when(widgetsService.getAllWidgets()).thenReturn(Collections.emptyList());
//
//        // Calling the method under test
//        ResponseEntity<CommonResponse<List<Widget>>> response = widgetController.getWidgets(correlationId);
//
//        // Verifying the interactions and assertions
//        verify(widgetsService, times(1)).getAllWidgets();
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
////        assertTrue(response.getBody().getMetadata().getErrors());
////        assertEquals(ResponseErrorCode404.ERR_40400.build("widgets", "Records not found : "),
////                response.getBody().getMetadata().getErrors());
//    }
//
//    @Test
//    void testSaveWidget() throws Exception {
//        // Mocking the necessary objects
//        String correlationId = "123456";
//        Widget widget = new Widget();
//        Widget savedWidget = new Widget();
//
//        when(widgetsService.addWidget(widget)).thenReturn(savedWidget);
//
//        // Calling the method under test
//        ResponseEntity<CommonResponse<Widget>> response = widgetController.saveWidget(widget, correlationId);
//
//        // Verifying the interactions and assertions
//        verify(widgetsService, times(1)).addWidget(widget);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertTrue(response.getBody().getData().isPresent());
//        assertEquals(savedWidget, response.getBody().getData());
//    }
//
//    @Test
//    void testSaveWidget_InternalServerError() throws Exception {
//        // Mocking the necessary objects
//        String correlationId = "123456";
//        Widget widget = new Widget();
//
//        when(widgetsService.addWidget(widget)).thenThrow(new Exception("Error in saving the record."));
//
//        // Calling the method under test
//        ResponseEntity<CommonResponse<Widget>> response = widgetController.saveWidget(widget, correlationId);
//
//        // Verifying the interactions and assertions
//        verify(widgetsService, times(1)).addWidget(widget);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
////        assertTrue(response.getBody().getErrors().isPresent());
////        assertEquals(ResponseErrorCode500.ERR_50000.build("saveWidget",
////                "Error in saving the record in the DB.Message: Error in saving the record."),
////                response.getBody().getMetadata().getErrors());
//    }
//
//    @Test
//    void testGetWidgetById() {
//        // Mocking the necessary objects
//        String correlationId = "123456";
//        Long widgetId = 1L;
//        String widgetName = "example";
//        WidgetsID id = new WidgetsID(widgetId, widgetName);
//        Widget widget = new Widget();
//
//        when(widgetsService.getWidgetByUserId(id)).thenReturn(Optional.of(widget));
//
//        // Calling the method under test
//        ResponseEntity<CommonResponse<Widget>> response = widgetController.getWidgetById(widgetId, widgetName, correlationId);
//
//        // Verifying the interactions and assertions
////        verify(widgetsService, times(1)).getWidgetByUserId(id);
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertTrue(response.getBody().getData().isPresent());
////        assertEquals(widget, response.getBody().getData());
//    }
//
//    @Test
//    void testGetWidgetById_NotFound() {
//        // Mocking the necessary objects
//        String correlationId = "123456";
//        Long widgetId = 1L;
//        String widgetName = "example";
//        WidgetsID id = new WidgetsID(widgetId, widgetName);
//
//        when(widgetsService.getWidgetByUserId(id)).thenReturn(Optional.empty());
//
//        // Calling the method under test
//        ResponseEntity<CommonResponse<Widget>> response = widgetController.getWidgetById(widgetId, widgetName, correlationId);
//
//        // Verifying the interactions and assertions
////        verify(widgetsService, times(1)).getWidgetByUserId(id);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
////        assertTrue(response.getBody().getErrors().isPresent());
////        assertEquals(ResponseErrorCode404.ERR_40400.build("Widget", "Record not found with UserID: "),
////                response.getBody().getMetadata().getErrors());
//    }
//}














//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.convera.common.template.CommonResponse;
//import com.convera.common.template.response.error.constants.ResponseErrorCode404;
//import com.convera.common.template.response.error.constants.ResponseErrorCode500;
//import com.convera.postgress.data.api.repository.WidgetRepository;
//import com.convera.postgress.data.api.repository.model.Widget;
//import com.convera.postgress.data.api.repository.model.WidgetsID;
//import com.convera.postgress.data.api.service.WidgetsService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(controllers = WidgetController.class)
//public class WidgetControllerTest {
//	
//    @Autowired
//    private MockMvc mockMvc;
//    
//    @InjectMocks
//    private ObjectMapper objectMapper;
//    
//    @MockBean
//    private WidgetRepository widgetRepository;
//    
//    @MockBean
//    private WidgetsService widgetsService;
//    
//    @InjectMocks
//	private WidgetController widgetController;
//	@InjectMocks
//	private WidgetController underTest;
//
//
//	@Test
//	public void testSaveWidget_Success() throws Exception {
//		String correlationId = "12345";
//
//		Widget request = new Widget();
//		request.setWidgetId((long) 1);
//		request.setWidgetName("Test Widget");
//		request.setTemplate("Template");
//		request.setImageUrl("Image URL");
//		request.setActive(true);
//
//		
//		Widget savedWidget = new Widget();
//		savedWidget.setWidgetId((long) 1);
//		savedWidget.setWidgetName("Test Widget");
//		savedWidget.setTemplate("Template");
//		savedWidget.setImageUrl("Image URL");
//		savedWidget.setActive(true);
//		savedWidget.setCreatedBy(LocalDateTime.now());
//		savedWidget.setCreatedOn(LocalDateTime.now());
//		savedWidget.setUpdatedBy(LocalDateTime.now());
//		savedWidget.setUpdatedOn(LocalDateTime.now());
//
//		when(this.widgetsService.addWidget(request)).thenReturn(savedWidget);
//		ResponseEntity<CommonResponse<Widget>> response = widgetController.saveWidget(request, correlationId);
//
//		assertNotNull(response);
////		assertEquals(HttpStatus.OK, response.getStatusCode());
////
////		CommonResponse<Widget> responseBody = response.getBody();
////		assertNotNull(responseBody);
////		assertNotNull(responseBody.getData());
////		assertEquals(savedWidget, responseBody.getData());
////
////		verify(widgetsService, times(1)).addWidget(request);
//	}
//
//	@Test
//	public void testSaveWidget_InternalServerError() throws Exception {
//		String correlationId = "12345";
//
//		Widget request = new Widget();
//		request.setWidgetId(1L);
//		request.setWidgetName("Test Widget");
//		request.setTemplate("Template");
//		request.setImageUrl("Image URL");
//		request.setActive(true);
//
//		String errorMessage = "Error in saving the record in the DB.";
//
//		when(widgetsService.addWidget(request)).thenThrow(new Exception(errorMessage));
//
//		ResponseEntity<CommonResponse<Widget>> response = widgetController.saveWidget(request, correlationId);
//
//		assertNotNull(response);
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//
//		CommonResponse<Widget> responseBody = response.getBody();
//		assertNotNull(responseBody);
//		assertNull(responseBody.getData());
//		assertEquals(1, responseBody.getMetadata().getErrors().size());
////		assertEquals(ResponseErrorCode500.ERR_50000.build("saveWidget",
////				"Error in saving the record in the DB. Message: " + errorMessage), responseBody.getMetadata().getErrors().get(0));
//
////		verify(widgetsService, times(1)).addWidget(request);
//	}
//
//	@Test
//	public void testGetWidgetById_Success() {
//		String correlationId = "12345";
//		Long widgetId = 1L;
//		String widgetName = "Test Widget";
//
//		Widget widget = new Widget();
//		widget.setWidgetId(widgetId);
//		widget.setWidgetName(widgetName);
//		widget.setTemplate("Template");
//		widget.setImageUrl("Image URL");
//		widget.setActive(true);
//		widget.setCreatedBy(LocalDateTime.now());
//		widget.setCreatedOn(LocalDateTime.now());
//		widget.setUpdatedBy(LocalDateTime.now());
//		widget.setUpdatedOn(LocalDateTime.now());
//
//		WidgetsID widgetsID = new WidgetsID(widgetId, widgetName);
//
//		when(widgetsService.getWidgetByUserId(widgetsID)).thenReturn(Optional.of(widget));
//
////		ResponseEntity<CommonResponse<Widget>> response = widgetController.getWidgetById(widgetId, widgetName, correlationId);
////
////		assertNotNull(response);
////		assertEquals(HttpStatus.OK, response.getStatusCode());
////
////		CommonResponse<Widget> responseBody = response.getBody();
////		assertNotNull(responseBody);
////		assertNotNull(responseBody.getData());
////		assertEquals(widget, responseBody.getData());
////
////		verify(widgetsService, times(1)).getWidgetByUserId(widgetsID);
//	}
//
//	@Test
//	public void testGetWidgetById_NotFound() {
//		String correlationId = "12345";
//		Long widgetId = 1L;
//		String widgetName = "Test Widget";
//
//		WidgetsID widgetsID = new WidgetsID(widgetId, widgetName);
//
//		when(widgetsService.getWidgetByUserId(widgetsID)).thenReturn(Optional.empty());
//
////		ResponseEntity<CommonResponse<Widget>> response = widgetController.getWidgetById(widgetId, widgetName, correlationId);
////
////		assertNotNull(response);
////		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
////
////		CommonResponse<Widget> responseBody = response.getBody();
////		assertNotNull(responseBody);
////		assertNull(responseBody.getData());
////		assertEquals(1, responseBody.getMetadata().getErrors().size());
////		assertEquals(ResponseErrorCode404.ERR_40400.build("Widget", "Record not found with UserID: "),
////				responseBody.getMetadata() .getErrors().get(0));
////
////		verify(widgetsService, times(1)).getWidgetByUserId(widgetsID);
//	}
//
//	@Nested
//	class WhenGettingWidgets {
//		private final String CORRELATION_ID = "CORRELATION_ID";
//
//		@BeforeEach
//		void setup() {
//		}
//	}
//
//	@Nested
//	class WhenSavingWidget {
//		private final String CORRELATION_ID = "CORRELATION_ID";
//		@Mock
//		private @Valid @NotNull Widget request;
//
//		@BeforeEach
//		void setup() {
//		}
//	}
//
//	@Nested
//	class WhenGettingWidgetById {
//		private final String WIDGET_NAME = "WIDGET_NAME";
//		private final String CORRELATION_ID = "CORRELATION_ID";
//
//		@BeforeEach
//		void setup() {
//		}
//	}
//}
