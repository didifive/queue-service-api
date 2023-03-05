package br.tec.didiproject.queueserviceapi.utils.populate;

import br.tec.didiproject.queueserviceapi.entities.Fila;
import br.tec.didiproject.queueserviceapi.entities.Senha;
import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import br.tec.didiproject.queueserviceapi.services.FilaService;
import br.tec.didiproject.queueserviceapi.services.SenhaService;
import br.tec.didiproject.queueserviceapi.services.TipoAtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@RequiredArgsConstructor
@Component
public class PopularSenhas {

    private final SenhaService senhaService;
    private final FilaService filaService;
    private final TipoAtendimentoService tipoAtendimentoService;

    private final Pageable pageable = PageRequest.of(0, 10);

    public void popularSenhas() {
        Page<Senha> senhas = senhaService.findAll(pageable);

        if (senhas.getContent().isEmpty()) {
            Fila fila = filaService.findAll(pageable).getContent().get(0);
            TipoAtendimento tipoAtendimento1 =  tipoAtendimentoService.findAll(pageable).getContent().get(0);
            TipoAtendimento tipoAtendimento2 =  tipoAtendimentoService.findAll(pageable).getContent().get(0);

            senhaService.create(fila.getId(), tipoAtendimento1.getId());
            senhaService.create(fila.getId(), tipoAtendimento1.getId());
            senhaService.create(fila.getId(), tipoAtendimento2.getId());
            senhaService.create(fila.getId(), tipoAtendimento1.getId());
            senhaService.create(fila.getId(), tipoAtendimento2.getId());
            senhaService.create(fila.getId(), tipoAtendimento1.getId());
            senhaService.create(fila.getId(), tipoAtendimento1.getId());
            senhaService.create(fila.getId(), tipoAtendimento2.getId());
            senhaService.create(fila.getId(), tipoAtendimento2.getId());
            senhaService.create(fila.getId(), tipoAtendimento1.getId());
        }

    }
}
