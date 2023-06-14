package com.convera.postgress.data.api.exception;

/**
 * Data not found exception class.
 *
 * @author Vikram Sahl
 */
public class DataNotFoundException extends RuntimeException {


  public DataNotFoundException(String msg) {
    super(msg);
  }
}
