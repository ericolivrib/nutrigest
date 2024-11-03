package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.ItemRefeicaoRequest;
import br.ufsm.redescomp.nutrigest.dto.RefeicaoRequest;
import br.ufsm.redescomp.nutrigest.dto.RefeicaoResponse;
import br.ufsm.redescomp.nutrigest.model.Alimento;
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

    public Long adicionarRefeicao(RefeicaoRequest refeicaoDto) {
        var refeicao = refeicaoRepository.save(refeicaoDto.mapToEntity());
        return refeicao.getId();
    }

    public Long adicionarItemRefeicao(Long refeicaoId, ItemRefeicaoRequest itemDto) {
        var refeicao = refeicaoRepository.findById(refeicaoId).orElseThrow();

        var item = itemDto.mapToEntity();
        item.setRefeicao(refeicao);

        itemRefeicaoRepository.save(item);
        return item.getId();
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

    public void atualizarRefeicao(Long id, RefeicaoRequest refeicaoDto) {
        Refeicao refeicao = refeicaoRepository.findById(id).orElseThrow();

        refeicao.setPeriodo(refeicaoDto.periodo());
        refeicao.setData(refeicaoDto.data());
        refeicao.setItens(refeicaoDto.itens().stream().map(ItemRefeicaoRequest::mapToEntity).toList());

        refeicaoRepository.save(refeicao);

        atualizarMacronutrientesRefeicao(id);
    }

    public void atualizarItemRefeicao(Long id, ItemRefeicaoRequest itemDto) {
        ItemRefeicao item = itemRefeicaoRepository.findById(id).orElseThrow();

        item.setAlimento(Alimento.builder().id(itemDto.alimentoId()).build());
        item.setQuantidade(itemDto.quantidade());
        item.setCalorias(itemDto.calorias());
        item.setCarboidratos(itemDto.carboidratos());
        item.setProteinas(itemDto.proteinas());
        item.setGorduras(itemDto.gorduras());

        itemRefeicaoRepository.save(item);

        atualizarMacronutrientesRefeicao(item.getRefeicao().getId());
    }

    private void atualizarMacronutrientesRefeicao(Long id) {
        Refeicao refeicao = refeicaoRepository.findById(id).orElseThrow();
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

    public void deletarRefeicao(Long id) {
        Refeicao refeicao = refeicaoRepository.findById(id).orElseThrow();
        refeicaoRepository.delete(refeicao);
    }

    public void deletarItemRefeicao(Long id) {
        ItemRefeicao item = itemRefeicaoRepository.findById(id).orElseThrow();
        itemRefeicaoRepository.delete(item);

        /// Deve recalcular o total de cada um dos macronutrientes da refeição
        atualizarMacronutrientesRefeicao(id);
    }

}
