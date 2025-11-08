import java.io.*;
import java.util.*;

public class SistemaNotificacoes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Notificacao> notificacoes = new ArrayList<>();
        File arquivo = new File("notificacoes.txt");

        // carregar notificações do arquivo
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(";");
                    if (partes.length == 8) {
                        notificacoes.add(new Notificacao(
                            partes[0],
                            Integer.parseInt(partes[1]),
                            partes[2],
                            partes[3],
                            partes[4],
                            partes[5],
                            partes[6],
                            partes[7]
                        ));
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao ler arquivo: " + e.getMessage());
            }
        }

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n===== SISTEMA DE NOTIFICAÇÃO DE AGRAVOS =====");
            System.out.println("1 - Registrar nova notificação");
            System.out.println("2 - Consultar por nome do paciente");
            System.out.println("3 - Listar por bairro");
            System.out.println("4 - Gerar relatórios");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 1) {
                System.out.println("\n=== NOVA NOTIFICAÇÃO ===");
                System.out.print("Nome do paciente: ");
                String nome = scanner.nextLine();

                System.out.print("Idade: ");
                int idade = Integer.parseInt(scanner.nextLine());

                System.out.print("Sexo (M/F): ");
                String sexo = scanner.nextLine();

                System.out.print("Bairro: ");
                String bairro = scanner.nextLine();

                System.out.println("Escolha o tipo de agravo:");
                System.out.println("1 - Hanseniase");
                System.out.println("2 - Tuberculose");
                System.out.println("3 - Malaria");
                System.out.print("Opção: ");
                String agravo = "";
                int opc = Integer.parseInt(scanner.nextLine());
                switch (opc) {
                    case 1: agravo = "Hanseníase"; break;
                    case 2: agravo = "Tuberculose"; break;
                    case 3: agravo = "Malária"; break;
                    default: agravo = "Outro"; break;
                }

                System.out.print("Data da notificação (dd/mm/aaaa): ");
                String data = scanner.nextLine();

                System.out.print("Escolaridade: ");
                String escolaridade = scanner.nextLine();

                System.out.print("Raça/Cor: ");
                String racaCor = scanner.nextLine();

                Notificacao n = new Notificacao(nome, idade, sexo, bairro, agravo, data, escolaridade, racaCor);
                notificacoes.add(n);

                System.out.println("Notificação registrada com sucesso!");

                // salvar no arquivo
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                    for (Notificacao no : notificacoes) {
                        writer.write(no.getNomePaciente() + ";" +
                            no.getIdade() + ";" +
                            no.getSexo() + ";" +
                            no.getBairro() + ";" +
                            no.getAgravo() + ";" +
                            no.getDataNotificacao() + ";" +
                            no.getEscolaridade() + ";" +
                            no.getRacaCor());
                        writer.newLine();
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao salvar arquivo: " + e.getMessage());
                }

            } else if (opcao == 2) {
                System.out.print("Digite o nome do paciente: ");
                String nomeBusca = scanner.nextLine();
                boolean achou = false;
                for (Notificacao n : notificacoes) {
                    if (n.getNomePaciente().equalsIgnoreCase(nomeBusca)) {
                        System.out.println(n);
                        achou = true;
                    }
                }
                if (!achou) System.out.println("Nenhuma notificação encontrada.");

            } else if (opcao == 3) {
                System.out.print("Digite o bairro: ");
                String bairroBusca = scanner.nextLine();
                boolean achou = false;
                for (Notificacao n : notificacoes) {
                    if (n.getBairro().equalsIgnoreCase(bairroBusca)) {
                        System.out.println(n);
                        achou = true;
                    }
                }
                if (!achou) System.out.println("Nenhuma notificação encontrada.");

            } else if (opcao == 4) {
                System.out.println("\n=== RELATÓRIOS ===");

                Map<String, Integer> porAgravo = new HashMap<>();
                Map<String, Integer> porBairro = new HashMap<>();
                Map<String, Integer> porSexo = new HashMap<>();
                Map<String, Integer> porRaca = new HashMap<>();
                Map<String, Integer> porEscolaridade = new HashMap<>();
                Map<String, Integer> faixaEtaria = new LinkedHashMap<>();
                faixaEtaria.put("0-17", 0);
                faixaEtaria.put("18-59", 0);
                faixaEtaria.put("60+", 0);

                for (Notificacao n : notificacoes) {
                    porAgravo.put(n.getAgravo(), porAgravo.getOrDefault(n.getAgravo(), 0) + 1);
                    porBairro.put(n.getBairro(), porBairro.getOrDefault(n.getBairro(), 0) + 1);
                    porSexo.put(n.getSexo(), porSexo.getOrDefault(n.getSexo(), 0) + 1);
                    porRaca.put(n.getRacaCor(), porRaca.getOrDefault(n.getRacaCor(), 0) + 1);
                    porEscolaridade.put(n.getEscolaridade(), porEscolaridade.getOrDefault(n.getEscolaridade(), 0) + 1);

                    if (n.getIdade() <= 17) faixaEtaria.put("0-17", faixaEtaria.get("0-17") + 1);
                    else if (n.getIdade() <= 59) faixaEtaria.put("18-59", faixaEtaria.get("18-59") + 1);
                    else faixaEtaria.put("60+", faixaEtaria.get("60+") + 1);
                }

                System.out.println("Total por agravo: " + porAgravo);
                System.out.println("Total por bairro: " + porBairro);
                System.out.println("Total por sexo: " + porSexo);
                System.out.println("Total por raça/cor: " + porRaca);
                System.out.println("Total por escolaridade: " + porEscolaridade);
                System.out.println("Total por faixa etária: " + faixaEtaria);
            }
        }
        scanner.close();
        System.out.println("Saindo...");
    }
}

