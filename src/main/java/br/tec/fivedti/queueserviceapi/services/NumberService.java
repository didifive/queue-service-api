package br.tec.fivedti.queueserviceapi.services;

import br.tec.fivedti.queueserviceapi.dto.mapper.NumberMapper;
import br.tec.fivedti.queueserviceapi.dto.request.NumberDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.entities.Number;
import br.tec.fivedti.queueserviceapi.entities.Queue;
import br.tec.fivedti.queueserviceapi.excepitions.*;
import br.tec.fivedti.queueserviceapi.repositories.NumberRepository;
import br.tec.fivedti.queueserviceapi.repositories.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NumberService {

    private final NumberRepository numberRepository;
    private final QueueRepository queueRepository;
    private final NumberMapper numberMapper;

    public MessageResponseDto createNumber(NumberDto numberDto) throws QueueNotFoundException, QueueDeactivatedException {
        Number newNumber = numberMapper.toModel(numberDto);

        Queue queue = findQueue(newNumber.getQueue().getId());
        newNumber.setQueue(queue);

        if (queue.isDeactivated())
            throw new QueueDeactivatedException(queue.getId());

        if (!newNumber.isDeactivated())
            newNumber.setDeactivated(false);

        int newNumberSequence = (queue.getLastNumber() + 1);
        newNumber.setNumber(newNumberSequence);
        queue.setLastNumber(newNumberSequence);
        queueRepository.save(queue);

        Number savedNumber = numberRepository.save(newNumber);

        return createMessageResponse("Number successfully created.", savedNumber);
    }

    public List<NumberDto> listAllNumbers() {
        List<Number> numbers = numberRepository.findAll();
        return numbers.stream()
                .map(numberMapper::toDTO)
                .collect(Collectors.toList());
    }

    public NumberDto findById(UUID id) throws NumberNotFoundException {
        Number number = numberRepository.findById(id)
                .orElseThrow(() -> new NumberNotFoundException(id));
        return numberMapper.toDTO(number);
    }


    public MessageResponseDto update(UUID id, NumberDto numberDto) throws NumberNotFoundException, QueueNotFoundException {
        numberRepository.findById(id)
                .orElseThrow(() -> new NumberNotFoundException(id));

        Number updatedNumber = numberMapper.toModel(numberDto);

        Queue queue = findQueue(updatedNumber.getQueue().getId());
        updatedNumber.setQueue(queue);

        Number savedNumber = numberRepository.save(updatedNumber);

        return createMessageResponse("Number successfully updated.", savedNumber);
    }

    public MessageResponseDto delete(UUID id) throws NumberNotFoundException {
        Number deletedNumber = numberRepository.findById(id)
                .orElseThrow(() -> new NumberNotFoundException(id));

        numberRepository.delete(deletedNumber);

        return createMessageResponse("Number successfully deleted.", deletedNumber);
    }

    private MessageResponseDto createMessageResponse(String s, Object o) {
        return MessageResponseDto.builder()
                .message(s)
                .payload(o)
                .build();
    }

    private Queue findQueue(UUID id) throws QueueNotFoundException{
        return queueRepository.findById(id)
                .orElseThrow(() -> new QueueNotFoundException(id));
    }
}

