package br.tec.fivedti.queueserviceapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumberDto implements Serializable {
    private UUID id;
    private int number;
    private Timestamp createTimestamp;
    private Timestamp attendedTimestamp;
    private boolean deactivated;
    private QueueDto queue;
}
