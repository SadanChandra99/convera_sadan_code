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
import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.QuickLinkID;
import com.convera.postgress.data.api.repository.model.Widget;
import com.convera.postgress.data.api.service.QuickLinkService;


import datadog.trace.api.Trace;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("quickLink")
@Slf4j
@Validated
@Tag(name = "QuickLink", description = "the QuickLink API")
public class QuickLinkController {

	@Autowired
	QuickLinkService quickLinkService;
	
	
	 public QuickLinkController(QuickLinkService quickLinkService) {
		super();
		this.quickLinkService = quickLinkService;
	}

	@Operation(
		      operationId = "getAllQuickLinks",
		      responses = {
		          @ApiResponse(responseCode = "200", description = "QuickLinks response", content = {
		              @Content(mediaType = "application/json", schema =
		              @Schema(implementation = QuickLinkResponse.class))
		          }),
		          @ApiResponse(responseCode = "404", description = "Not found", content = {
		              @Content(mediaType = "application/json", schema =
		              @Schema(implementation = CommonResponse.class))
		          })
		      }
		  )
		  @Trace
		  @GetMapping("/")
		  public ResponseEntity<CommonResponse<List<QuickLink>>> getQuickLinks(
		      @Parameter(description = "numeric product id", example = "1") 
		      @RequestHeader(value = "correlationID", required = false) String correlationId) {

		    final String quickLinksPath = "/quickLinks/" ;
		    List<QuickLink> quickLinks = this.quickLinkService.getAllQuickLinks();
		    
		    if (quickLinks.size() > 0) {
		      return ResponseEntity
		          .ok(CommonResponseUtil.createResponse200(quickLinks,
		        		  quickLinksPath, correlationId));
		    } else {
		      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		          CommonResponseUtil.createResponse404(null, quickLinksPath,
		              correlationId, Collections.singletonList(ResponseErrorCode404.ERR_40400.build(
		                  "QuickLinks", "Records not found : "))));
		    }
		  }
	 
	 @Operation(
	            operationId = "saveQuickLink",
	            responses = {
	                    @ApiResponse(responseCode = "200", description = "QuickLink response", content = {
	                            @Content(mediaType = "application/json", schema =
	                            @Schema(implementation = QuickLinkResponse.class))
	                    }),
	                    @ApiResponse(responseCode = "500", description = "unexpected error", content = {
	                            @Content(mediaType = "application/json", schema =
	                            @Schema(implementation = CommonResponse.class))
	                    })
	            }
	    )
	    @Trace
	    @PostMapping("/")
	    public ResponseEntity<CommonResponse<QuickLink>> saveQuickLink(
	            @Valid @NotNull @RequestBody(required = true) QuickLink request,
	            @RequestHeader(value = "correlationId", required = false) String correlationId) {

	        final String Path = "/quickLinks/";
	        try {
	            QuickLink result = quickLinkService.addQuickLink(request);
	            return ResponseEntity.ok(CommonResponseUtil.createResponse200(result,
	                    Path, correlationId));
	        } catch (Exception ex) {
	            return ResponseEntity.internalServerError()
	                    .body(CommonResponseUtil.createResponse500(null, Path, correlationId,
	                            Collections.singletonList(ResponseErrorCode500.ERR_50000.build("saveQuickLink",
	                                    "Error in saving the record in the DB." + "Message:" + ex.getMessage()))));
	        }
	    }

	 @Operation(
		      operationId = "getQuickLinkById",
		      responses = {
		          @ApiResponse(responseCode = "200", description = "QuickLink response", content = {
		              @Content(mediaType = "application/json", schema =
		              @Schema(implementation = QuickLinkResponse.class))
		          }),
		          @ApiResponse(responseCode = "404", description = "Not found", content = {
		              @Content(mediaType = "application/json", schema =
		              @Schema(implementation = CommonResponse.class))
		          })
		      }
		  )
		  @Trace
		  @GetMapping("/id")
		  public ResponseEntity<CommonResponse<QuickLink>> getQuickLinkById(
		      @Parameter(description = "numeric product id", example = "1") 
		      @RequestParam("userId")Integer linkId,
		      @RequestParam("linkName") String linkName,
		      @RequestHeader(value = "correlationID", required = false) String correlationId) {

		    final String quickLinksPath = "/quickLinks/id" ;
		    QuickLinkID id = new QuickLinkID(linkId, linkName);
		    Optional<QuickLink> quickLinks = quickLinkService.getQuickLinkById(id);
		    
		    if (quickLinks.isPresent()) {
		      return ResponseEntity
		          .ok(CommonResponseUtil.createResponse200(quickLinks.get(),
		        		  quickLinksPath, correlationId));
		    } else {
		      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		          CommonResponseUtil.createResponse404(null, quickLinksPath,
		              correlationId, Collections.singletonList(ResponseErrorCode404.ERR_40400.build(
		                  "QuickLink", "Records not found : "))));
		    }
		  }
	 
	  private class QuickLinkResponse extends CommonResponse<QuickLink> {
	  }
	
}
