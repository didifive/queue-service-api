package br.tec.didiproject.queueserviceapi.utils.populate;

import br.tec.didiproject.queueserviceapi.entities.Fila;
import br.tec.didiproject.queueserviceapi.services.DepartamentoService;
import br.tec.didiproject.queueserviceapi.services.FilaService;
import br.tec.didiproject.queueserviceapi.services.TipoAtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@RequiredArgsConstructor
@Component
public class PopularFila {

    private final FilaService filaService;
    private final TipoAtendimentoService tipoAtendimentoService;
    private final DepartamentoService departamentoService;

    private final Pageable pageable = PageRequest.of(0, 10);

    @Lazy
    private final Fila novaFila = Fila.builder()
            .nome("Contas a receber")
            .sigla("CR")
            .tiposAtendimento(new HashSet<>())
            .build();


    public void popularFila() {
        novaFila.setTiposAtendimento(new HashSet<>(tipoAtendimentoService.findAll(pageable).getContent()));

        novaFila.setDepartamento(departamentoService.findAll(pageable).getContent().get(0));

        Page<Fila> filas = filaService.findAll(pageable);

        if (filas.getContent().isEmpty()) {
            filaService.create(novaFila);
        }

    }
}
