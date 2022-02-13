package br.tec.fivedti.queueserviceapi.services;

import br.tec.fivedti.queueserviceapi.dto.mapper.QueueMapper;
import br.tec.fivedti.queueserviceapi.dto.request.QueueDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.entities.Company;
import br.tec.fivedti.queueserviceapi.entities.Queue;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyNotFoundException;
import br.tec.fivedti.queueserviceapi.excepitions.QueueNotFoundException;
import br.tec.fivedti.queueserviceapi.repositories.CompanyRepository;
import br.tec.fivedti.queueserviceapi.repositories.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QueueService {

    private final QueueRepository queueRepository;
    private final CompanyRepository companyRepository;
    private final QueueMapper queueMapper;

    public MessageResponseDto createQueue(QueueDto queueDto) throws CompanyNotFoundException {
        Queue newQueue = queueMapper.toModel(queueDto);
        Queue extantQueue = queueRepository.findByAbbreviation(newQueue.getAbbreviation());

        if (!Objects.isNull(extantQueue))
            return createMessageResponse("Queue with abbreviation "
                    + newQueue.getAbbreviation()
                    + " already extant"
                    , extantQueue);

        Company company = companyRepository.findById(newQueue.getCompany().getId())
                .orElseThrow(() -> new CompanyNotFoundException(newQueue.getCompany().getId()));

        newQueue.setCompany(company);

        if (!newQueue.isDeactivated())
            newQueue.setDeactivated(false);

        Queue savedQueue = queueRepository.save(newQueue);

        return createMessageResponse("Queue successfully created.", savedQueue);
    }

    public List<QueueDto> listAllQueues() {
        List<Queue> queues = queueRepository.findAll();
        return queues.stream()
                .map(queueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QueueDto findById(UUID id) throws QueueNotFoundException {
        Queue queue = queueRepository.findById(id)
                .orElseThrow(() -> new QueueNotFoundException(id));
        return queueMapper.toDTO(queue);
    }


    public MessageResponseDto update(UUID id, QueueDto queueDto) throws QueueNotFoundException, CompanyNotFoundException {
        queueRepository.findById(id)
                .orElseThrow(() -> new QueueNotFoundException(id));

        Queue updatedQueue = queueMapper.toModel(queueDto);

        Company company = companyRepository.findById(updatedQueue.getCompany().getId())
                .orElseThrow(() -> new CompanyNotFoundException(updatedQueue.getCompany().getId()));

        updatedQueue.setCompany(company);

        Queue savedQueue = queueRepository.save(updatedQueue);

        return createMessageResponse("Queue successfully updated.", savedQueue);
    }

    public MessageResponseDto delete(UUID id) throws QueueNotFoundException {
        Queue deletedQueue = queueRepository.findById(id)
                .orElseThrow(() -> new QueueNotFoundException(id));

        queueRepository.delete(deletedQueue);

        return createMessageResponse("Queue successfully deleted.", deletedQueue);
    }

    private MessageResponseDto createMessageResponse(String s, Object o) {
        return MessageResponseDto.builder()
                .message(s)
                .payload(o)
                .build();
    }
}

