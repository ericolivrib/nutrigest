package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.PessoaDto;
import br.ufsm.redescomp.nutrigest.model.Pessoa;
import br.ufsm.redescomp.nutrigest.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void adicionarPessoa(Pessoa pessoa) {
        pessoaRepository.save(Pessoa.builder()
                .nome(pessoa.getNome())
                .telefone(pessoa.getTelefone())
                .dataNascimento(pessoa.getDataNascimento())
                .genero(pessoa.getGenero())
                .altura(pessoa.getAltura())
                .peso(pessoa.getPeso())
                .nivelAtividade(pessoa.getNivelAtividade())
                .objetivo(pessoa.getObjetivo())
                .build()
        );
    }

    public List<PessoaDto> getPessoas() {
        return pessoaRepository.findAll()
                .stream()
                .map(PessoaDto::new)
                .toList();
    }

    public PessoaDto getPessoaById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();
        return new PessoaDto(pessoa);
    }

    public void atualizarPessoa(Long id, Pessoa pessoa) {
        Pessoa p = pessoaRepository.findById(id).orElseThrow();

        p.setNome(pessoa.getNome());
        p.setTelefone(pessoa.getTelefone());
        p.setObjetivo(pessoa.getObjetivo());
        p.setAltura(pessoa.getAltura());
        p.setPeso(pessoa.getPeso());
        p.setDataNascimento(pessoa.getDataNascimento());
        p.setGenero(pessoa.getGenero());
        p.setNivelAtividade(pessoa.getNivelAtividade());

        pessoaRepository.save(p);
    }

    public void deletarPessoa(Long id) {
        Pessoa p = pessoaRepository.findById(id).orElseThrow();
        pessoaRepository.delete(p);
    }

}
