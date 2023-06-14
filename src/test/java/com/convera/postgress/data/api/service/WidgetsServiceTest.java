package com.convera.postgress.data.api.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.convera.postgress.data.api.helper.WidgetsHelper;
//import com.convera.postgress.data.api.repository.WidgetRepository;
//import com.convera.postgress.data.api.repository.model.Widget;
//import com.convera.postgress.data.api.repository.model.WidgetsID;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.convera.postgress.data.api.repository.WidgetRepository;
//import com.convera.postgress.data.api.repository.model.Widget;
//import com.convera.postgress.data.api.repository.model.WidgetsID;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDateTime;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.convera.postgress.data.api.helper.WidgetsHelper;
//import com.convera.postgress.data.api.repository.WidgetRepository;
//import com.convera.postgress.data.api.repository.model.Widget;
//import com.convera.postgress.data.api.repository.model.WidgetsID;
//
//@ExtendWith(MockitoExtension.class)
//public class WidgetsServiceTest {
//	
//	@Mock
//	private WidgetRepository widgetRepository;
//	
//	@InjectMocks
//	private WidgetsService widgetsService;
//	
//    @BeforeAll
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//	
//	@Test
//	void getAllWidgets() {
//		assertNotNull(WidgetsHelper.getDefaultWidgets());
//	}
//	
//	@Test
//	void getWidgetById() {
//		WidgetsID id = new WidgetsID(1L, "Payment widget");
//		 assertNotNull(id);
//	}
//	
//	@Test
//	void saveWidget() throws Exception {
//		Widget w = new Widget(100L, "Testing widget", "testing template", "www.convera.com", true, LocalDateTime.now(), LocalDateTime.now(), 11);
////		Widget w2 = new Widget();
////		w2.builder()
////		.widgetId(102L)
////		.widgetName("Testing Widget")
////		.imageUrl("convera.com")
////		.active(true)
////		.template("Testing template")
////		.createdBy(LocalDateTime.now())
////		.createdOn(LocalDateTime.now())
////		.updatedBy(LocalDateTime.now())
////		.updatedOn(LocalDateTime.now())
////		.build();
//		
//		
//		assertNotNull(w);
//	}
//	
//	@Test
//	public void testAddWidget() throws Exception {
//		Widget widget = new Widget();
//		widget.setWidgetId((long) 1);
//		widget.setWidgetName("Test Widget");
//
//		Widget savedWidget = new Widget();
//		savedWidget.setWidgetId((long) 1);
//		savedWidget.setWidgetName("Test Widget");
//	
//		savedWidget.setCreatedOn(LocalDateTime.now());
//		
//		savedWidget.setUpdatedOn(LocalDateTime.now());
//
//		when(widgetRepository.save(widget)).thenReturn(savedWidget);
//
//		Widget result = widgetsService.addWidget(widget);
//
//		assertNotNull(result);
//		assertEquals(savedWidget, result);
//
//		assertNotNull(result.getCreatedOn());
//
//		assertNotNull(result.getUpdatedOn());
//
//		verify(widgetRepository, times(1)).save(widget);
//	}
//	
//	
//	@Test
//    public void testGetAllWidgets_WhenWidgetsExist_ShouldReturnAllWidgets() {
//        // Arrange
//        List<Widget> widgets = createSampleWidgetList();
//        when(widgetRepository.findAll()).thenReturn(widgets);
//
//        // Act
//        List<Widget> result = widgetsService.getAllWidgets();
//
//        // Assert
//        assertEquals(widgets, result);
//    }
//
//    @Test
//    public void testGetAllWidgets_WhenNoWidgetsExist_ShouldReturnDefaultWidgets() {
//        // Arrange
//        when(widgetRepository.findAll()).thenReturn(new ArrayList<>());
//
//        // Act
//        List<Widget> result = widgetsService.getAllWidgets();
//
//        // Assert
//        List<Widget> defaultWidgets = WidgetsHelper.getDefaultWidgets();
////        assertEquals(defaultWidgets, result);
//    }
//
//    @Test
//    public void testAddWidget_ShouldSaveAndReturnWidget() throws Exception {
//        // Arrange
//        Widget widget = createSampleWidget();
//        when(widgetRepository.save(widget)).thenReturn(widget);
//
//        // Act
//        Widget result = widgetsService.addWidget(widget);
//
//        // Assert
//        assertEquals(widget, result);
//        assertTrue(result.getCreatedOn() instanceof LocalDateTime);
//        assertTrue(result.getUpdatedOn() instanceof LocalDateTime);
//    }
//
//    @Test
//    public void testGetWidgetByUserId_WhenWidgetExists_ShouldReturnWidget() {
//    	Long userId = 10L;
//        // Arrange
//        WidgetsID id = new WidgetsID(userId, "widgetId");
//        Widget widget = createSampleWidget();
//        when(widgetRepository.findById(id)).thenReturn(Optional.of(widget));
//
//        // Act
//        Optional<Widget> result = widgetsService.getWidgetByUserId(id);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(widget, result.get());
//    }
//
//    @Test
//    public void testGetWidgetByUserId_WhenWidgetDoesNotExist_ShouldReturnEmptyOptional() {
//    	Long userId = 10L;
//        // Arrange
//        WidgetsID id = new WidgetsID(userId, "widgetId");
//        when(widgetRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act
//        Optional<Widget> result = widgetsService.getWidgetByUserId(id);
//
//        // Assert
//        assertFalse(result.isPresent());
//    }
//
//    @Test
//    public void testGetWidgetByWidgetId_WhenWidgetExists_ShouldReturnWidget() {
//        // Arrange
//        Long widgetId = 1L;
//        Widget widget = createSampleWidget();
//        when(widgetRepository.findByWidgetId(widgetId)).thenReturn(Optional.of(widget));
//
//        // Act
//        Optional<Widget> result = widgetsService.getWidgetByWidgetId(widgetId);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(widget, result.get());
//    }
//
//    @Test
//    public void testGetWidgetByWidgetId_WhenWidgetDoesNotExist_ShouldReturnEmptyOptional() {
//        // Arrange
//        Long widgetId = 1L;
//        when(widgetRepository.findByWidgetId(widgetId)).thenReturn(Optional.empty());
//
//        // Act
//        Optional<Widget> result = widgetsService.getWidgetByWidgetId(widgetId);
//
//        // Assert
//        assertFalse(result.isPresent());
//    }
//
//    // Helper methods to create sample data for testing
//    private List<Widget> createSampleWidgetList() {
//        List<Widget> widgets = new ArrayList<>();
//        widgets.add(createSampleWidget());
//        widgets.add(createSampleWidget());
//        return widgets;
//    }
//
//    private Widget createSampleWidget() {
//        Widget widget = new Widget(12L, "widgetName", "Template", "convera.com", true, LocalDateTime.now(),  LocalDateTime.now(), 10);
//        
////        widget.setId(1L);
////        widget.setUserId(12L);
////        widget.setWidgetId("widgetId");
////        widget.setName("Widget 1");
//        return widget;
//    }
//
//}
