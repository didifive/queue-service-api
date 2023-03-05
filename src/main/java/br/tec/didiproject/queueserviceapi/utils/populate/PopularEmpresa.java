package br.tec.didiproject.queueserviceapi.utils.populate;

import br.tec.didiproject.queueserviceapi.entities.Empresa;
import br.tec.didiproject.queueserviceapi.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PopularEmpresa {

    private final EmpresaService empresaService;

    private final Empresa novaEmpresa = Empresa.builder()
            .nome("Super Atendimento")
            .cpfCnpj("12.345.678/0001-90")
            .endereco("Rua Quinze de Novembro, 555, Jd Botânico, São Paulo-SP")
            .build();

    public void popularEmpresa() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Empresa> empresas = empresaService.findAll(pageable);

        if(empresas.getContent().isEmpty()) {
            empresaService.create(novaEmpresa);
        }

    }
}
