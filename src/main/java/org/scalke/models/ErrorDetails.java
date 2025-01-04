package org.scalke.models;

import lombok.*;

import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ErrorDetails {
    String message;
    Map<String, Object> playload;
}
