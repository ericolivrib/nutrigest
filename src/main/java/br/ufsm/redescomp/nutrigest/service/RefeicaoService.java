package br.ufsm.redescomp.nutrigest.service;

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

        atualizarMacronutrientes(novaRefeicao);
        refeicaoRepository.save(novaRefeicao);
    }

    public List<Refeicao> getRefeicoesByPessoa(Long pessoaId) {
        return refeicaoRepository.findAllByPessoaId(pessoaId);
    }

    public Refeicao getRefeicaoById(Long refeicaoId) {
        return refeicaoRepository.findById(refeicaoId).orElseThrow();
    }

    public void atualizarRefeicao(Long refeicaoId, Refeicao refeicao) {
        Refeicao r = refeicaoRepository.findById(refeicaoId).orElseThrow();

        r.setPeriodo(refeicao.getPeriodo());
        r.setDataRealizacao(refeicao.getDataRealizacao());
        r.setItens(refeicao.getItens());

        refeicaoRepository.save(r);
        atualizarMacronutrientes(r);
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
        atualizarMacronutrientes(item.getRefeicao());
    }

    public void deletarItemRefeicao(Long itemId) {
        ItemRefeicao item = itemRefeicaoRepository.findById(itemId).orElseThrow();
        itemRefeicaoRepository.delete(item);
        atualizarMacronutrientes(item.getRefeicao());
    }

    private void atualizarMacronutrientes(Refeicao refeicao) {
        List<ItemRefeicao> itens = refeicao.getItens();

        int carboidratosTotais = itens.stream().map(ItemRefeicao::getCarboidratos).reduce(0, Integer::sum);
        int caloriasTotais = itens.stream().map(ItemRefeicao::getCalorias).reduce(0, Integer::sum);
        int proteinasTotais = itens.stream().map(ItemRefeicao::getProteinas).reduce(0, Integer::sum);
        int gordurasTotais = itens.stream().map(ItemRefeicao::getGorduras).reduce(0, Integer::sum);

        refeicao.setCarboidratosTotais(carboidratosTotais);
        refeicao.setCaloriasTotais(caloriasTotais);
        refeicao.setProteinasTotais(proteinasTotais);
        refeicao.setGordurasTotais(gordurasTotais);

        refeicaoRepository.save(refeicao);
    }

}
