package com.convera.postgress.data.api.web;

import java.util.Collections;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.convera.common.template.CommonResponse;
import com.convera.common.template.response.error.constants.ResponseErrorCode404;
import com.convera.common.template.response.error.constants.ResponseErrorCode500;
import com.convera.common.template.response.util.CommonResponseUtil;
import com.convera.postgress.data.api.helper.WidgetsHelper;
import com.convera.postgress.data.api.repository.model.DashboardPreference;
import com.convera.postgress.data.api.repository.model.Product;
import com.convera.postgress.data.api.repository.model.Widget;
import com.convera.postgress.data.api.repository.model.WidgetsID;
import com.convera.postgress.data.api.service.WidgetsService;

import datadog.trace.api.Trace;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("widgets")
@Slf4j
@Validated
@Tag(name = "widgets", description = "the widgets API")
public class WidgetController {
	
	@Autowired
	private WidgetsService widgetsService; 
	
	
	 @Operation(
		      operationId = "getAllWidgets",
		      responses = {
		          @ApiResponse(responseCode = "200", description = "widget response", content = {
		              @Content(mediaType = "application/json", schema =
		              @Schema(implementation = WidgetResponse.class))
		          }),
		          @ApiResponse(responseCode = "404", description = "Not found", content = {
		              @Content(mediaType = "application/json", schema =
		              @Schema(implementation = CommonResponse.class))
		          })
		      }
		  )
		  @Trace
		  @GetMapping("/")
		  public ResponseEntity<CommonResponse<List<Widget>>> getWidgets(
		      @Parameter(description = "numeric product id", example = "1") 
		      @RequestHeader(value = "correlationID", required = false) String correlationId) {

		    final String widgetsPath = "/widgets/" ;
		    List<Widget> widgets = widgetsService.getAllWidgets();
		    
		    if (widgets.size() > 0) {
		      return ResponseEntity
		          .ok(CommonResponseUtil.createResponse200(widgets,
		        		  widgetsPath, correlationId));
		    } else {
		      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		          CommonResponseUtil.createResponse404(null, widgetsPath,
		              correlationId, Collections.singletonList(ResponseErrorCode404.ERR_40400.build(
		                  "widgets", "Records not found : "))));
		    }
		  }
	 
	 
	 @Operation(
	            operationId = "saveWidget",
	            responses = {
	                    @ApiResponse(responseCode = "200", description = "widget response", content = {
	                            @Content(mediaType = "application/json", schema =
	                            @Schema(implementation = WidgetResponse.class))
	                    }),
	                    @ApiResponse(responseCode = "500", description = "unexpected error", content = {
	                            @Content(mediaType = "application/json", schema =
	                            @Schema(implementation = CommonResponse.class))
	                    })
	            }
	    )
	    @Trace
	    @PostMapping("/")
	    public ResponseEntity<CommonResponse<Widget>> saveWidget(
	            @Valid @NotNull @RequestBody(required = true) Widget request,
	            @RequestHeader(value = "correlationId", required = false) String correlationId) {

	        final String dashboardPath = "/widgets/";
	        try {
	            Widget result = this.widgetsService.addWidget(request);
	            return ResponseEntity.ok(CommonResponseUtil.createResponse200(result,
	                    dashboardPath, correlationId));
	        } catch (Exception ex) {
	            return ResponseEntity.internalServerError()
	                    .body(CommonResponseUtil.createResponse500(null, dashboardPath, correlationId,
	                            Collections.singletonList(ResponseErrorCode500.ERR_50000.build("saveWidget",
	                                    "Error in saving the record in the DB." + "Message:" + ex.getMessage()))));
	        }
	    }
	 
	 
	 @Operation(
	            operationId = "getWidgetById",
	            responses = {
	                    @ApiResponse(responseCode = "200", description = "Widget response", content = {
	                            @Content(mediaType = "application/json", schema =
	                            @Schema(implementation = WidgetResponse.class))
	                    }),
	                    @ApiResponse(responseCode = "404", description = "Not found", content = {
	                            @Content(mediaType = "application/json", schema =
	                            @Schema(implementation = CommonResponse.class))
	                    })
	            }
	    )
	    @Trace
	    @GetMapping("/id")
	    public ResponseEntity<CommonResponse<Widget>> getWidgetById(
	            @Parameter(description = "User Id ", example = "1")
//	            @PathVariable(required = true) Long widgetId,	 
	            @RequestParam(name = "widgetId") Long widgetId,
	            @RequestParam(name = "widgetName") String widgetName,
	            @RequestHeader(value = "correlationId", required = false) String correlationId) {

	        final String Path = "/widgets/id" ;

	        WidgetsID ID = new WidgetsID(widgetId, widgetName);
	        
	        
	        Optional<Widget> widget = widgetsService.getWidgetByUserId(ID);
	        Optional<Widget> w = Optional.of(new Widget());
	        if (widget.isPresent()) {
	            return ResponseEntity
	                    .ok(CommonResponseUtil.createResponse200(widget.get(),
	                            Path, correlationId));
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	                    CommonResponseUtil.createResponse404(w.get(), Path, correlationId,
	                            Collections.singletonList(ResponseErrorCode404.ERR_40400.build(
	                                    "Widget", "Record not found with UserID: "))));
	        }
	    }


	 
	  private class WidgetResponse extends CommonResponse<Widget> {
	  }
	

}
