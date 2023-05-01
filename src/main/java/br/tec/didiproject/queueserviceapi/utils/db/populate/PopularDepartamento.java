package br.tec.didiproject.queueserviceapi.utils.db.populate;

import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.services.DepartamentoService;
import br.tec.didiproject.queueserviceapi.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PopularDepartamento {

    private final DepartamentoService departamentoService;
    private final EmpresaService empresaService;

    private final Pageable pageable = PageRequest.of(0, 10);

    private final Departamento novoDepartamento = Departamento.builder()
            .nome("Financeiro")
            .build();


    public void popularDepartamento() {

        novoDepartamento.setEmpresa(empresaService.findAll(pageable).getContent().get(0));

        Page<Departamento> departamentos = departamentoService.findAll(pageable);

        if (departamentos.getContent().isEmpty()) {
            departamentoService.create(novoDepartamento);
        }

    }
}
