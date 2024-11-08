package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.RefeicaoDTO;
import br.ufsm.redescomp.nutrigest.model.ItemRefeicao;
import br.ufsm.redescomp.nutrigest.model.Refeicao;
import br.ufsm.redescomp.nutrigest.repository.ItemRefeicaoRepository;
import br.ufsm.redescomp.nutrigest.repository.RefeicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefeicaoService {

    private final RefeicaoRepository refeicaoRepository;
    private final ItemRefeicaoRepository itemRefeicaoRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository, ItemRefeicaoRepository itemRefeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
        this.itemRefeicaoRepository = itemRefeicaoRepository;
    }

    public void adicionarRefeicao(Refeicao refeicao) {
        Refeicao novaRefeicao = Refeicao.builder()
                .pessoa(refeicao.getPessoa())
                .periodo(refeicao.getPeriodo())
                .dataRealizacao(refeicao.getDataRealizacao())
                .itens(refeicao.getItens().stream().map((i) -> ItemRefeicao.builder()
                        .alimento(i.getAlimento())
                        .quantidade(i.getQuantidade())
                        .calorias(i.getCalorias())
                        .carboidratos(i.getCarboidratos())
                        .gorduras(i.getGorduras())
                        .proteinas(i.getProteinas())
                        .build()).toList())
                .build();

        refeicaoRepository.save(novaRefeicao);
    }

    public List<RefeicaoDTO> getRefeicoesByPessoa(Long pessoaId) {
        return refeicaoRepository.findAllByPessoaId(pessoaId)
                .stream()
                .map(RefeicaoDTO::new)
                .toList();
    }

    public RefeicaoDTO getRefeicaoById(Long refeicaoId) {
        Refeicao refeicao = refeicaoRepository.findById(refeicaoId).orElseThrow();
        return new RefeicaoDTO(refeicao);
    }

    public void atualizarRefeicao(Long refeicaoId, Refeicao refeicao) {
        Refeicao r = refeicaoRepository.findById(refeicaoId).orElseThrow();

        r.setPeriodo(refeicao.getPeriodo());
        r.setDataRealizacao(refeicao.getDataRealizacao());
        r.setItens(refeicao.getItens());

        refeicaoRepository.save(r);
    }

    public void deletarRefeicao(Long refeicaoId) {
        Refeicao refeicao = refeicaoRepository.findById(refeicaoId).orElseThrow();
        refeicaoRepository.delete(refeicao);
    }

    public void adicionarItemRefeicao(Long refeicaoId, ItemRefeicao item) {
        var refeicao = refeicaoRepository.findById(refeicaoId).orElseThrow();
        item.setRefeicao(refeicao);
        itemRefeicaoRepository.save(item);
    }

    public void atualizarItemRefeicao(Long itemId, ItemRefeicao item) {
        ItemRefeicao i = itemRefeicaoRepository.findById(itemId).orElseThrow();

        i.setAlimento(item.getAlimento());
        i.setQuantidade(item.getQuantidade());
        i.setCalorias(item.getCalorias());
        i.setCarboidratos(item.getCarboidratos());
        i.setProteinas(item.getProteinas());
        i.setGorduras(item.getGorduras());

        itemRefeicaoRepository.save(item);
    }

    public void deletarItemRefeicao(Long itemId) {
        ItemRefeicao item = itemRefeicaoRepository.findById(itemId).orElseThrow();
        itemRefeicaoRepository.delete(item);
    }

}
