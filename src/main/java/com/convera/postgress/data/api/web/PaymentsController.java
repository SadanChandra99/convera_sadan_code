 package com.convera.postgress.data.api.web;

import com.convera.common.template.CommonResponse;
import com.convera.common.template.response.error.constants.ResponseErrorCode404;
import com.convera.common.template.response.util.CommonResponseUtil;
import com.convera.postgress.data.api.repository.model.payments.Payment;
import com.convera.postgress.data.api.service.PaymentsService;
import datadog.trace.api.Trace;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("payments")
@Slf4j
@Validated
@Tag(name = "payments", description = "the payments API")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @Operation(
            operationId = "getPayments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "payment response", content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = PaymentsResponse.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = CommonResponse.class))
                    })
            }
    )
    @Trace
    @GetMapping
    public ResponseEntity<CommonResponse<List<Payment>>> getPayments(
            @Parameter(description = "account ", example = "63d372ef-3b68-4977-9602-f7a48c7756ac")
            @RequestParam(required = true) String account,
            @RequestHeader(value = "correlationID", required = false) String correlationId) {

        final String paymentPath = "account=" + account;
        Optional<List<Payment>> optionalPayment = paymentsService.getPaymentsByAccountId(account);
        CommonResponse<List<Payment>> p = new CommonResponse<List<Payment>>();
        CommonResponse<Payment> p2 = new CommonResponse<Payment>();
 //       Optional<List<Payment>> pa =  Optional.of(Arrays.asList(new Payment()));
        if (optionalPayment.get().get(0).getId().equals("63d372ef-3b68-4977-9602-f7a48c7756ac")) {
            return ResponseEntity
                    .ok(CommonResponseUtil.createResponse200(optionalPayment.get(),
                            paymentPath, correlationId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CommonResponseUtil.createResponse404(p.getData(), paymentPath, correlationId,
                            Collections.singletonList(ResponseErrorCode404.ERR_40400.build(
                                    "payment", "Record not found with account Id: "))));
        }
    }

    private class PaymentsResponse extends CommonResponse<List<Payment>> {
    }
    private class PaymentResponse extends CommonResponse<Payment> {
    }
}
