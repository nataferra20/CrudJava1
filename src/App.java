import java.util.Scanner;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        PessoaCRUD crud = new PessoaCRUD();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n==============================");
            System.out.println("        CRUD DE PESSOAS       ");
            System.out.println("==============================");
            System.out.println("1 - Adicionar pessoa");
            System.out.println("2 - Listar pessoas");
            System.out.println("3 - Atualizar pessoa");
            System.out.println("4 - Remover pessoa");
            System.out.println("5 - Buscar pessoa por nome");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nome: "); String nome = sc.nextLine();
                    System.out.print("Idade: "); int idade = sc.nextInt(); sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    crud.adicionarPessoa(new Pessoa(id, nome, idade, email));
                    System.out.println(">>> Pessoa adicionada com sucesso!");
                    System.out.println("Total de pessoas: " + crud.listarPessoas().size());
                }
                case 2 -> {
                    System.out.println("\n--- Lista de pessoas ---");
                    crud.listarPessoas().forEach(System.out::println);
                    System.out.println("-------------------------");
                }
                case 3 -> {
                    System.out.print("ID da pessoa: "); int idUpd = sc.nextInt(); sc.nextLine();
                    Optional<Pessoa> p = crud.buscarPessoaPorId(idUpd);
                    if (p.isPresent()) {
                        System.out.print("Novo nome: "); String nNome = sc.nextLine();
                        System.out.print("Nova idade: "); int nIdade = sc.nextInt(); sc.nextLine();
                        System.out.print("Novo email: "); String nEmail = sc.nextLine();
                        System.out.print("Ativo (true/false): "); boolean nAtivo = sc.nextBoolean(); sc.nextLine();
                        crud.atualizarPessoa(idUpd, nNome, nIdade, nEmail, nAtivo);
                        System.out.println(">>> Pessoa atualizada!");
                    } else {
                        System.out.println(">>> Pessoa não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("ID da pessoa que deseja remover: "); int idDel = sc.nextInt(); sc.nextLine();
                    Optional<Pessoa> pDel = crud.buscarPessoaPorId(idDel);
                    if (pDel.isPresent()) {
                        System.out.print("Tem certeza que deseja remover " + pDel.get().getNome() + "? (S/N): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("S")) {
                            crud.removerPessoa(idDel);
                            System.out.println(">>> Pessoa removida!");
                        } else {
                            System.out.println(">>> Operação cancelada.");
                        }
                    } else {
                        System.out.println(">>> Pessoa não encontrada.");
                    }
                }
                case 5 -> {
                    System.out.print("Digite o nome para buscar: "); String busca = sc.nextLine();
                    System.out.println("\n--- Resultados da busca ---");
                    crud.listarPessoas().stream()
                            .filter(pessoa -> pessoa.getNome().toLowerCase().contains(busca.toLowerCase()))
                            .forEach(System.out::println);
                    System.out.println("----------------------------");
                }
                case 0 -> System.out.println("Saindo... Até mais!");
                default -> System.out.println(">>> Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
