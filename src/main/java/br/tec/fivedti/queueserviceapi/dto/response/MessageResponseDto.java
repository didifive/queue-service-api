package br.tec.fivedti.queueserviceapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDto {
    private String message;
    private Object payload;
}