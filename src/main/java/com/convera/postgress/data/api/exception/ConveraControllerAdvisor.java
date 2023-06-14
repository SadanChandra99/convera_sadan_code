package com.convera.postgress.data.api.exception;

import com.convera.common.template.CommonResponse;
import com.convera.common.template.response.error.constants.ResponseErrorCode404;
import com.convera.common.template.response.util.CommonResponseUtil;
import java.util.Collections;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Convera Controller Advisor class.
 *
 * @author Vikram Sahl
 */
@ControllerAdvice
@Slf4j
public class ConveraControllerAdvisor {

  /**
   * Handle Data not Found Exception.
   *
   * @param ex      {@link DataNotFoundException}
   * @param request for tracing {@link WebRequest}
   * @return {@link ResponseEntity Object}
   */
  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<CommonResponse<Object>> handleDataNotFoundException(
      DataNotFoundException ex, WebRequest request) {

    return ResponseEntity.badRequest().body(CommonResponseUtil.createResponse404(
        null, request.getContextPath(), request.getHeader("correlationID"),
        Collections.singletonList(ResponseErrorCode404.ERR_40400.build(
            "product", Optional.ofNullable(ex.getMessage())
                .orElse("HttpMessageNotReadableException::"
                    + "please check server logs for the details.")))));
  }

}
