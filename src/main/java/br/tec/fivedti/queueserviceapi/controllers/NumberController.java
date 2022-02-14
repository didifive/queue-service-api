package br.tec.fivedti.queueserviceapi.controllers;

import br.tec.fivedti.queueserviceapi.controllers.docs.NumberControllerDocs;
import br.tec.fivedti.queueserviceapi.dto.request.NumberDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.excepitions.NumberNotFoundException;
import br.tec.fivedti.queueserviceapi.excepitions.QueueDeactivatedException;
import br.tec.fivedti.queueserviceapi.excepitions.QueueNotFoundException;
import br.tec.fivedti.queueserviceapi.services.NumberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/number")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NumberController implements NumberControllerDocs {

    private final NumberService numberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createNumber(@RequestBody @Valid NumberDto numberDto) throws QueueNotFoundException, QueueDeactivatedException {
        return numberService.createNumber(numberDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NumberDto> listNumbers() {
        return numberService.listAllNumbers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NumberDto findById(@PathVariable UUID id) throws NumberNotFoundException {
        return numberService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto update(@PathVariable UUID id
            , @RequestBody @Valid NumberDto numberDto
    ) throws NumberNotFoundException, QueueNotFoundException {
        return numberService.update(id, numberDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto delete(@PathVariable UUID id) throws NumberNotFoundException {
        return numberService.delete(id);
    }
}
