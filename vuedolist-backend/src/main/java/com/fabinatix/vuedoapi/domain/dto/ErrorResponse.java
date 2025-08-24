package com.fabinatix.vuedoapi.domain.dto;

public record ErrorResponse (
  int status,
  String message,
  String details
) {
}
