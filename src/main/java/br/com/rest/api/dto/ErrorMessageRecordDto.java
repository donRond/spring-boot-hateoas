package br.com.rest.api.dto;

import org.springframework.http.HttpStatus;

public record ErrorMessageRecordDto(HttpStatus status, String message) {}
