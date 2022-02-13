package br.tec.fivedti.queueserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumberDto implements Serializable {
    private long id;

    @NotNull(message = "The number is required!")
    private int number;

    private Timestamp createTimestamp;
    private Timestamp attendedTimestamp;

    @NotNull(message = "The number queue is required!")
    private QueueDto queue;
}
