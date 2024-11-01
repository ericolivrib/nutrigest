package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.AlimentoRequest;
import br.ufsm.redescomp.nutrigest.dto.AlimentoResponse;
import br.ufsm.redescomp.nutrigest.repository.AlimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public Long adicionarAlimento(AlimentoRequest alimento) {
        var a = alimentoRepository.save(alimento.mapToEntity());
        return a.getId();
    }

    public List<AlimentoResponse> getAlimentos() {
        return alimentoRepository.findAll()
                .stream()
                .map(AlimentoResponse::mapFromEntity)
                .toList();
    }

    public AlimentoResponse getAlimentoById(Long id) {
        var alimento = alimentoRepository.findById(id).orElseThrow();
        return AlimentoResponse.mapFromEntity(alimento);
    }

    public void atualizarAlimento(Long id, AlimentoRequest alimento) {
        var a = alimentoRepository.findById(id).orElseThrow();

        a.setNome(alimento.nome());
        a.setCategoria(alimento.categoria());
        a.setPorcao(alimento.porcao());
        a.setProteinas(alimento.proteinas());
        a.setCalorias(alimento.calorias());
        a.setGorduras(alimento.gorduras());
        a.setCarboidratos(alimento.carboidratos());
        a.setVitaminas(alimento.vitaminas());
        a.setMinerais(alimento.minerais());
        a.setIndiceGlicemico(alimento.indiceGlicemico());

        alimentoRepository.save(a);
    }

    public void deletarAlimento(Long id) {
        var alimento = alimentoRepository.findById(id).orElseThrow();
        alimentoRepository.delete(alimento);
    }

}
