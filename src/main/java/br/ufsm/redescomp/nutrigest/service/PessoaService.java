package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.PessoaRequest;
import br.ufsm.redescomp.nutrigest.dto.PessoaResponse;
import br.ufsm.redescomp.nutrigest.repository.PessoaRepository;
import br.ufsm.redescomp.nutrigest.util.PessoaEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Long adicionarPessoa(PessoaRequest pessoa) {
        var p =  pessoaRepository.save(PessoaEntityMapper.mapToEntity(pessoa));
        return p.getId();
    }

    public List<PessoaResponse> getPessoas() {
        return pessoaRepository.findAll()
                .stream()
                .map(PessoaEntityMapper::mapFromEntity)
                .toList();
    }

    public PessoaResponse getPessoaById(Long id) {
        return PessoaEntityMapper.mapFromEntity(pessoaRepository.findById(id).orElseThrow());
    }

    public void atualizarPessoa(Long id, PessoaRequest pessoa) {
        var p = pessoaRepository.findById(id).orElseThrow();

        p.setNome(pessoa.nome());
        p.setTelefone(pessoa.telefone());
        p.setObjetivo(pessoa.objetivo());
        p.setAltura(pessoa.altura());
        p.setPeso(pessoa.peso());
        p.setDataNascimento(pessoa.dataNascimento());
        p.setGenero(pessoa.genero());
        p.setNivelAtividade(pessoa.nivelAtividade());

        pessoaRepository.save(p);
    }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

}
