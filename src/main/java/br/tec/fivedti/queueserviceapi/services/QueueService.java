package br.tec.fivedti.queueserviceapi.services;

import br.tec.fivedti.queueserviceapi.dto.mapper.QueueMapper;
import br.tec.fivedti.queueserviceapi.dto.request.QueueDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.entities.Company;
import br.tec.fivedti.queueserviceapi.entities.QueueRow;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyDeactivatedException;
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

    public MessageResponseDto createQueue(QueueDto queueDto) throws CompanyNotFoundException, CompanyDeactivatedException {
        QueueRow newQueue = queueMapper.toModel(queueDto);
        QueueRow extantQueue = queueRepository.findByAbbreviation(newQueue.getAbbreviation());

        if (!Objects.isNull(extantQueue))
            return createMessageResponse("Queue with abbreviation "
                    + newQueue.getAbbreviation()
                    + " already extant"
                    , extantQueue);

        Company company = findCompany(newQueue.getCompany().getId());
        newQueue.setCompany(company);

        if (company.isDeactivated())
            throw new CompanyDeactivatedException(company.getId());

        if (!newQueue.isDeactivated())
            newQueue.setDeactivated(false);

        QueueRow savedQueue = queueRepository.save(newQueue);

        return createMessageResponse("Queue successfully created.", savedQueue);
    }

    public List<QueueDto> listAllQueues() {
        List<QueueRow> queues = queueRepository.findAll();
        return queues.stream()
                .map(queueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QueueDto findById(UUID id) throws QueueNotFoundException {
        QueueRow queue = queueRepository.findById(id)
                .orElseThrow(() -> new QueueNotFoundException(id));
        return queueMapper.toDTO(queue);
    }


    public MessageResponseDto update(UUID id, QueueDto queueDto) throws QueueNotFoundException, CompanyNotFoundException {
        queueRepository.findById(id)
                .orElseThrow(() -> new QueueNotFoundException(id));

        QueueRow updatedQueue = queueMapper.toModel(queueDto);

        Company company = findCompany(updatedQueue.getCompany().getId());
        updatedQueue.setCompany(company);

        QueueRow savedQueue = queueRepository.save(updatedQueue);

        return createMessageResponse("Queue successfully updated.", savedQueue);
    }

    public MessageResponseDto delete(UUID id) throws QueueNotFoundException {
        QueueRow deletedQueue = queueRepository.findById(id)
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

    private Company findCompany(UUID id) throws CompanyNotFoundException{
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }
}

