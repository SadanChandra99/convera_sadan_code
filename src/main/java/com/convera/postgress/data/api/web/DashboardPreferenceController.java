package com.convera.postgress.data.api.web;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.convera.common.template.CommonResponse;
import com.convera.common.template.response.error.constants.ResponseErrorCode500;
import com.convera.common.template.response.util.CommonResponseUtil;
import com.convera.postgress.data.api.repository.DashboardPreferenceRepository;
import com.convera.postgress.data.api.repository.model.DashboardPreference;
import com.convera.postgress.data.api.service.DashboardPreferenceService;

import datadog.trace.api.Trace;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("dashboardPreference")
@Slf4j
@Validated
@Tag(name = "dashboard", description = "the dashboard API")
public class DashboardPreferenceController {

    @Autowired
    private DashboardPreferenceService dashboardService;
    

    @Operation(
            operationId = "getDashboards",
            responses = {
                    @ApiResponse(responseCode = "200", description = "dashboard response", content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = DashboardsResponse.class))
                    }),

            }
    )
    @Trace
    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponse<DashboardPreference>> getDashboards(
            @Parameter(description = "User Id ", example = "1")
            @PathVariable(required = true) Integer userId,
//            @RequestParam(name = "userId") Integer userId,
            @RequestHeader(value = "correlationId", required = false) String correlationId) {

        final String dashboardPath = "/" + userId;
        Optional<DashboardPreference> dashboardList = dashboardService.getDashboardsByUserId(userId);
        Optional<DashboardPreference> defaultDashboard = Optional.of(dashboardService.getDefaultPreference().get());
        
        if (dashboardList.isPresent()) {
            return ResponseEntity
                    .ok(CommonResponseUtil.createResponse200(dashboardList.get(),
                            dashboardPath, correlationId));
        } else {
        	return ResponseEntity
        			.ok(CommonResponseUtil.createResponse200(defaultDashboard.get(), 
        					dashboardPath, correlationId ));

        }
    }


    @Operation(
            operationId = "saveDashboards",
            responses = {
                    @ApiResponse(responseCode = "200", description = "dashboard response", content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = DashboardsResponse.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "unexpected error", content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = CommonResponse.class))
                    })
            }
    )
    @Trace
    @PostMapping("/")
    public ResponseEntity<CommonResponse<List<DashboardPreference>>> saveDashboards(
            @Valid @NotNull @RequestBody(required = true) List<DashboardPreference> request,
            @RequestHeader(value = "correlationId", required = false) String correlationId) {

        final String dashboardPath = "/dashboardPreference/";
        try {
            List<DashboardPreference> result = dashboardService.saveAll(request);
            return ResponseEntity.ok(CommonResponseUtil.createResponse200(result,
                    dashboardPath, correlationId));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body(CommonResponseUtil.createResponse500(null, dashboardPath, correlationId,
                            Collections.singletonList(ResponseErrorCode500.ERR_50000.build("saveDashboards",
                                    "Error in saving the record in the DB." + "Message:" + ex.getMessage()))));
        }
    }
    
    @GetMapping("/bywidgets")
    public void getAllUsersByWidgetId(Long id) {
    	dashboardService.findAllUserPreferenceIdsByWidgetId(id);
    }
    
    @GetMapping("/all")
    public List<DashboardPreference> getAllDashboards(){
    	List<DashboardPreference> dashboards = dashboardService.getAllDashboards();
    	return dashboards;
    }
    
    private class DashboardsResponse extends CommonResponse<List<DashboardPreference>> {
    }
    private class DashboardResponse extends CommonResponse<DashboardPreference> {
    }
}
