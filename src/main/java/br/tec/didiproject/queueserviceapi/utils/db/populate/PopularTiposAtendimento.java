package br.tec.didiproject.queueserviceapi.utils.db.populate;

import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import br.tec.didiproject.queueserviceapi.services.TipoAtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PopularTiposAtendimento {

    private final TipoAtendimentoService tipoAtendimentoService;

    private final Pageable pageable = PageRequest.of(0, 10);

    @Lazy
    private final TipoAtendimento tipoAtendimentoPrioritario = TipoAtendimento.builder()
            .nome("Prioritario")
            .sigla("P")
            .prioridade(Short.valueOf("1"))
            .build();
    @Lazy
    private final TipoAtendimento tipoAtendimentoNormal = TipoAtendimento.builder()
            .nome("Normal")
            .sigla("N")
            .prioridade(Short.valueOf("10"))
            .build();


    public void popularTiposAtendimento() {

        Page<TipoAtendimento> tiposAtendimentos = tipoAtendimentoService.findAll(pageable);

        if (tiposAtendimentos.getContent().isEmpty()) {
            List<TipoAtendimento> novosTiposAtendimento = List.of(tipoAtendimentoNormal, tipoAtendimentoPrioritario);
            novosTiposAtendimento.forEach(tipoAtendimentoService::create);
        }

    }
}
