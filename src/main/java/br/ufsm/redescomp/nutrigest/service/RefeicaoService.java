package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.RefeicaoRequest;
import br.ufsm.redescomp.nutrigest.dto.RefeicaoResponse;
import br.ufsm.redescomp.nutrigest.repository.RefeicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefeicaoService {

    private final RefeicaoRepository refeicaoRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
    }

    public Long adicionarRefeicao(RefeicaoRequest request) {
        var r = refeicaoRepository.save(request.mapToEntity());
        return r.getId();
    }

    public List<RefeicaoResponse> getRefeicoesByPessoa(Long pessoaId) {
        return refeicaoRepository.findAllByPessoaId(pessoaId)
                .stream()
                .map(RefeicaoResponse::mapFromEntity)
                .toList();
    }

    public RefeicaoResponse getRefeicaoById(Long id) {
        return RefeicaoResponse.mapFromEntity(refeicaoRepository.findById(id).orElseThrow());
    }

    public void atualizarRefeicao(Long id, RefeicaoRequest refeicao) {
        var r = refeicaoRepository.findById(id).orElseThrow();

        r.setPeriodo(refeicao.periodo());
        r.setData(refeicao.data());
        r.setProteinasTotais(refeicao.proteinasTotais());
        r.setCaloriasTotais(refeicao.caloriasTotais());
        r.setGordurasTotais(refeicao.gordurasTotais());
        r.setCarboidratosTotais(refeicao.carboidratosTotais());

        refeicaoRepository.save(r);
    }

    public void deletarRefeicao(Long id) {
        var refeicao = refeicaoRepository.findById(id).orElseThrow();
        refeicaoRepository.delete(refeicao);
    }

}
