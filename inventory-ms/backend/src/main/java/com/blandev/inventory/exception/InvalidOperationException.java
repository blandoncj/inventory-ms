package com.blandev.inventory.exception;

public class InvalidOperationException extends RuntimeException {
  public InvalidOperationException(String message) {
    super(message);
  }
}
