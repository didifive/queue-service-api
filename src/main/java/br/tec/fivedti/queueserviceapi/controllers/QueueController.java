package br.tec.fivedti.queueserviceapi.controllers;

import br.tec.fivedti.queueserviceapi.controllers.docs.QueueControllerDocs;
import br.tec.fivedti.queueserviceapi.dto.request.QueueDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyDeactivatedException;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyNotFoundException;
import br.tec.fivedti.queueserviceapi.excepitions.QueueNotFoundException;
import br.tec.fivedti.queueserviceapi.services.QueueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/queue")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QueueController implements QueueControllerDocs {

    private final QueueService queueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createQueue(@RequestBody @Valid QueueDto queueDto) throws CompanyNotFoundException, CompanyDeactivatedException {
        return queueService.createQueue(queueDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QueueDto> listQueues() {
        return queueService.listAllQueues();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QueueDto findById(@PathVariable UUID id) throws QueueNotFoundException {
        return queueService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto update(@PathVariable UUID id
            , @RequestBody @Valid QueueDto queueDto
    ) throws QueueNotFoundException, CompanyNotFoundException {
        return queueService.update(id, queueDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto delete(@PathVariable UUID id) throws QueueNotFoundException {
        return queueService.delete(id);
    }
}
