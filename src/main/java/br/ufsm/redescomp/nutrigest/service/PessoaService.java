package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.dto.PessoaDTO;
import br.ufsm.redescomp.nutrigest.model.Pessoa;
import br.ufsm.redescomp.nutrigest.model.PreferenciaAlimentar;
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
                .usuario(pessoa.getUsuario())
                .preferenciaAlimentar(PreferenciaAlimentar.builder()
                        .tipoAlimentar(pessoa.getPreferenciaAlimentar().getTipoAlimentar())
                        .intoleranteGluten(pessoa.getPreferenciaAlimentar().isIntoleranteGluten())
                        .intoleranteLactose(pessoa.getPreferenciaAlimentar().isIntoleranteLactose())
                        .outrasRestricoes(pessoa.getPreferenciaAlimentar().getOutrasRestricoes())
                        .build()).
                build());
    }

    public List<PessoaDTO> getPessoas() {
        return pessoaRepository.findAll()
                .stream()
                .map(PessoaDTO::new)
                .toList();
    }

    public PessoaDTO getPessoaById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();
        return new PessoaDTO(pessoa);
    }

    public PessoaDTO getPessoaByUsuario(Long usuarioId) {
        Pessoa pessoa = pessoaRepository.findByUsuarioId(usuarioId).orElseThrow();
        return new PessoaDTO(pessoa);
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
