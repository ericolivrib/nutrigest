package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.AdicionarPessoaRequest;
import br.ufsm.redescomp.nutrigest.repository.PessoaRepository;
import br.ufsm.redescomp.nutrigest.util.PessoaMapper;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void adicionarPessoa(AdicionarPessoaRequest pessoa) {
        pessoaRepository.save(PessoaMapper.mapToEntity(pessoa));
    }

}
