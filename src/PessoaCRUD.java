import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaCRUD {
    private List<Pessoa> pessoas = new ArrayList<>();

    // CREATE
    public void adicionarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    // READ
    public List<Pessoa> listarPessoas() {
        return pessoas;
    }

    public Optional<Pessoa> buscarPessoaPorId(int id) {
        return pessoas.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // UPDATE
    public boolean atualizarPessoa(int id, String nome, int idade, String email, boolean ativo) {
        Optional<Pessoa> pessoaOpt = buscarPessoaPorId(id);
        if (pessoaOpt.isPresent()) {
            Pessoa p = pessoaOpt.get();
            p.setNome(nome);
            p.setIdade(idade);
            p.setEmail(email);
            p.setAtivo(ativo);
            return true;
        }
        return false;
    }

    // DELETE
    public boolean removerPessoa(int id) {
        return pessoas.removeIf(p -> p.getId() == id);
    }
}
