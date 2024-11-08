package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.RefeicaoDTO;
import br.ufsm.redescomp.nutrigest.model.ItemRefeicao;
import br.ufsm.redescomp.nutrigest.model.Refeicao;
import br.ufsm.redescomp.nutrigest.repository.ItemRefeicaoRepository;
import br.ufsm.redescomp.nutrigest.repository.PessoaRepository;
import br.ufsm.redescomp.nutrigest.repository.RefeicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefeicaoService {

    private final RefeicaoRepository refeicaoRepository;
    private final ItemRefeicaoRepository itemRefeicaoRepository;
    private final PessoaRepository pessoaRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository, ItemRefeicaoRepository itemRefeicaoRepository, PessoaRepository pessoaRepository) {
        this.refeicaoRepository = refeicaoRepository;
        this.itemRefeicaoRepository = itemRefeicaoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public void adicionarRefeicao(Refeicao refeicao) {
        Refeicao novaRefeicao = Refeicao.builder()
                .pessoa(refeicao.getPessoa())
                .periodo(refeicao.getPeriodo())
                .dataRealizacao(refeicao.getDataRealizacao())
                .build();

        List<ItemRefeicao> itens = refeicao.getItens()
                .stream()
                .map((i) -> ItemRefeicao.builder().alimento(i.getAlimento()).quantidade(i.getQuantidade())
                        .calorias(i.getCalorias()).carboidratos(i.getCarboidratos()).gorduras(i.getGorduras())
                        .proteinas(i.getProteinas()).refeicao(novaRefeicao).build()).toList();

        novaRefeicao.setItens(itens);
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

        refeicaoRepository.save(r);
    }

    public void deletarRefeicao(Long refeicaoId) {
        Refeicao refeicao = refeicaoRepository.findById(refeicaoId).orElseThrow();
        refeicaoRepository.delete(refeicao);
    }

    public void adicionarItemRefeicao(Long refeicaoId, ItemRefeicao item) {
        Refeicao refeicao = refeicaoRepository.findById(refeicaoId).orElseThrow();

        itemRefeicaoRepository.save(ItemRefeicao.builder()
                .alimento(item.getAlimento())
                .quantidade(item.getQuantidade())
                .calorias(item.getCalorias())
                .carboidratos(item.getCarboidratos())
                .gorduras(item.getGorduras())
                .proteinas(item.getProteinas())
                .refeicao(refeicao)
                .build());
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
