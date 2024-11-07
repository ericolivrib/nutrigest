package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.AlimentoDto;
import br.ufsm.redescomp.nutrigest.model.Alimento;
import br.ufsm.redescomp.nutrigest.repository.AlimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public void adicionarAlimento(Alimento alimento) {
        alimentoRepository.save(Alimento.builder()
                .nome(alimento.getNome())
                .categoria(alimento.getCategoria())
                .porcao(alimento.getPorcao())
                .proteinas(alimento.getProteinas())
                .calorias(alimento.getCalorias())
                .gorduras(alimento.getGorduras())
                .carboidratos(alimento.getCarboidratos())
                .vitaminas(alimento.getVitaminas())
                .minerais(alimento.getMinerais())
                .indiceGlicemico(alimento.getIndiceGlicemico())
                .build());
    }

    public List<AlimentoDto> getAlimentos() {
        return alimentoRepository.findAll()
                .stream()
                .map(AlimentoDto::new)
                .toList();
    }

    public AlimentoDto getAlimentoById(Long id) {
        var alimento = alimentoRepository.findById(id).orElseThrow();
        return new AlimentoDto(alimento);
    }

    public void atualizarAlimento(Long id, Alimento alimento) {
        var a = alimentoRepository.findById(id).orElseThrow();

        a.setNome(alimento.getNome());
        a.setCategoria(alimento.getCategoria());
        a.setPorcao(alimento.getPorcao());
        a.setProteinas(alimento.getProteinas());
        a.setCalorias(alimento.getCalorias());
        a.setGorduras(alimento.getGorduras());
        a.setCarboidratos(alimento.getCarboidratos());
        a.setVitaminas(alimento.getVitaminas());
        a.setMinerais(alimento.getMinerais());
        a.setIndiceGlicemico(alimento.getIndiceGlicemico());

        alimentoRepository.save(a);
    }

    public void deletarAlimento(Long id) {
        var alimento = alimentoRepository.findById(id).orElseThrow();
        alimentoRepository.delete(alimento);
    }

}
