package br.tec.didiproject.queueserviceapi.utils.db.populate;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PopularBanco implements CommandLineRunner {

    private final PopularEmpresa popularEmpresa;
    private final PopularDepartamento popularDepartamento;
    private final PopularAtendente popularAtendente;
    private final PopularTiposAtendimento popularTiposAtendimento;
    private final PopularFila popularFila;
    private final PopularSenhas popularSenhas;

    @Override
    public void run(String... args) {
        popularEmpresa.popularEmpresa();
        popularDepartamento.popularDepartamento();
        popularAtendente.popularAtendente();
        popularTiposAtendimento.popularTiposAtendimento();
        popularFila.popularFila();
        popularSenhas.popularSenhas();
    }
}
