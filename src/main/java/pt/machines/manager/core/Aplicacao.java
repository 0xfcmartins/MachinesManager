package pt.machines.manager.core;

import pt.machines.manager.core.storage.Memoria;
import pt.machines.manager.exceptions.FileLoadException;
import pt.machines.manager.files.GestorDeRecursos;
import pt.machines.manager.objects.Maquina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import static java.util.Objects.isNull;


/**
 * Class com a lógica principal da aplicação
 */
public class Aplicacao {

    @SuppressWarnings("SpellCheckingInspection")
    private final static String CONFIGURACAO_INICIAL = "maquinas.txt";

    /**
     * Metodo que é iniciado logo no arranque, vai carregar todas as maquinas na memoria
     *
     * @throws RuntimeException No caso de existir algum problema na leitura das maquinas, devolve um erro
     */
    private void inicializar() {
        try {
            String[] linhasNoFicheiro = GestorDeRecursos.carregar(CONFIGURACAO_INICIAL);

            for (String linha : linhasNoFicheiro) {
                // com o trim remove os espaços do texto lido e faz o dividir do texto pela virgula
                String[] split = linha.trim().split(",");

                // valida se o total de divisões é 2, [CÓDIGO / HORAS]
                if (split.length == 2) {
                    // adiciona a máquina na memoria
                    Memoria.armazenar(split[0], Integer.parseInt(split[1]));
                } else {

                    throw new IllegalArgumentException("Configuração inválida! " + linha);
                }
            }

            System.out.println("Maquinas carregadas:");

            // No iniciar mostra todas as maquina que foram lidas do ficheiro
            for (Maquina maquina : Memoria.obterTodasAsMaquinas())
                System.out.println(maquina);

            System.out.println();
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * 4. Estudante 1
     * </p>
     * Escreve a informação de uma maquina, a forma de como a descrição é feita está no {@link Maquina}{@code .toString()}
     *
     * @param code Código da máquina
     */
    private void printMachineInformation(String code) {
        Maquina maquina = Memoria.selecionaAMaquinaPorCodigo(code);

        // Se encontrar a máquina escreve
        if (isNull(maquina)) {
            System.out.println("Máquina não encontrada!");

            return;
        }

        System.out.println(maquina);
    }

    /**
     * 4. Student 2
     * </p>
     * Atualiza o numero de horas de uma maquina que se encontra na memoria
     *
     * @param codigoDaMaquina Código que representa uma maquina em memória
     * @param horasDeTrabalho Novo valor de horas de trabalho para definir na máquina
     */
    private void atualizaOTotalDeHorasDeUmaMaquina(String codigoDaMaquina, int horasDeTrabalho) {
        Maquina maquina = Memoria.selecionaAMaquinaPorCodigo(codigoDaMaquina);

        if (maquina != null)
            maquina.defineHorasDeTrabalho(horasDeTrabalho);

        System.out.println("Máquina atualizada -> " + maquina);
    }

    /**
     * 4. Student 3
     * </p>
     * Remove uma maquina da memória
     *
     * @param codigoDaMaquina Código da máquina
     */
    private void removeUmaMaquina(String codigoDaMaquina) {
        boolean resultadoDaOperacao = Memoria.remover(codigoDaMaquina);

        if (resultadoDaOperacao)
            System.out.println("Máquina " + codigoDaMaquina + " foi removida com sucesso!");

        System.out.println("Máquina não encontrada!");
    }

    /**
     * 4. Student 4
     * </p>
     * Este processo serve para guardar no ficheiro de configuração o estado das maquinas atuais em memória.
     * Se forem carregas 4 máquinas, depois uma for removida, este processo vai guardar no ficheiro apenas as 3 restantes
     * no proximo inicio da aplicação só essas 3 seram carregadas
     */
    private void guardaEstadoAtualDaMaquina() {
        Maquina[] maquinasCarregadas = Memoria.obterTodasAsMaquinas();
        String[] maquinasParaGuardar = new String[maquinasCarregadas.length];

        for (int i = 0; i < maquinasCarregadas.length; i++) {
            maquinasParaGuardar[i] = maquinasCarregadas[i].storingString();
        }

        try {
            GestorDeRecursos.guardaAsMaquinasNoFicheiro(CONFIGURACAO_INICIAL, maquinasParaGuardar);
        } catch (IOException | FileLoadException | URISyntaxException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * Mostra o menu para ser possivel utilizar a applicação
     */
    public void mostraOMenu() {
        int opcao = 0;

        BufferedReader leitorDaConsola = new BufferedReader(new InputStreamReader(System.in));

        // Enquando a opcao não for 4 ele continua a mostrar o menu
        while (opcao != 4) {
            System.out.println("1. Mostrar informação de uma máquina");
            System.out.println("2. Atualizar as horas de trabalho para uma máquina");
            System.out.println("3. Remover uma máquina");
            System.out.println("4. Sair da aplicação (guarda o estado atual)");
            System.out.print("> ");

            try {
                opcao = Integer.parseInt(leitorDaConsola.readLine());

                //valores que podem ser lidos nas opcões
                String codigo;
                int horasDeTrabalho;

                switch (opcao) {
                    case 1:
                        System.out.print("Código > ");
                        codigo = leitorDaConsola.readLine();
                        this.printMachineInformation(codigo);

                        break;
                    case 2:
                        System.out.print("Código > ");
                        codigo = leitorDaConsola.readLine();

                        System.out.print("Novo valor de horas de trabalho > ");
                        horasDeTrabalho = Integer.parseInt(leitorDaConsola.readLine());
                        this.atualizaOTotalDeHorasDeUmaMaquina(codigo, horasDeTrabalho);

                        break;
                    case 3:
                        System.out.print("Código > ");
                        codigo = leitorDaConsola.readLine();
                        this.removeUmaMaquina(codigo);

                        break;
                    case 4:
                        this.guardaEstadoAtualDaMaquina();
                }

                if (opcao != 4) {
                    System.out.println("> Pressione alguma tecla para continuar");
                    leitorDaConsola.readLine();
                }
            } catch (Exception e) {
                System.out.println("Por favor intruduza um valor numérico!.");
            }
        }
    }

    public void iniciar() {
        this.inicializar();
        this.mostraOMenu();
    }

}
