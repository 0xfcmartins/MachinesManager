package pt.machines.manager.core.storage;

import pt.machines.manager.objects.Maquina;

import java.util.LinkedList;

/**
 * A classe Memoria oferece métodos para armazenar, selecionar, recuperar e remover objetos da classe Maquina na memória.
 */
public class Memoria {
    /**
     * A variável machines é uma LinkedList constante que armazena objetos da classe Maquina.
     * <p>
     * Ela faz parte da classe Memoria, que oferece métodos para armazenar, selecionar, recuperar e remover objetos da classe Maquina
     * na memória.
     * </p>
     * Serve como estrutura para guardar as máquinas na memoria.
     */
    public static final LinkedList<Maquina> machines;

    static {
        machines = new LinkedList<>();
    }
    /**
     * Adiciona um novo objeto da classe Maquina à lista machines na memória.
     *
     * @param codigo         O código da máquina.
     * @param horasDeTrabalho A quantidade de horas de trabalho da máquina.
     */
    public static void armazenar(String codigo, int horasDeTrabalho) {
        machines.add(new Maquina(codigo, horasDeTrabalho));
    }
    /**
     * Seleciona um objeto da classe Maquina da lista machines na memória com base no seu código.
     *
     * @param codigo O código da máquina.
     * @return O objeto Maquina selecionado, ou null se não for encontrado na lista.
     */
    public static Maquina selecionaAMaquinaPorCodigo(String codigo) {
        for (Maquina machine : machines) {
            if (machine.getCodigo().equals(codigo))
                return machine;
        }
        return null;
    }
    /**
     * Recupera todos os objetos da classe Maquina armazenados na memória.
     *
     * @return Um array de objetos da classe Maquina. Se não houver máquinas armazenadas na memória, retorna um array vazio.
     */
    public static Maquina[] obterTodasAsMaquinas() {
        return machines.toArray(new Maquina[0]);
    }
    /**
     * Remove uma máquina da memória com base no seu código.
     *
     * @param code O código da máquina a ser removida.
     * @return true se a máquina foi encontrada e removida, false caso contrário.
     */
    public static boolean remover(String code) {
        for (Maquina maquina : machines) {
            if (maquina.getCodigo().equals(code))
                return machines.remove(maquina);
        }

        return false;
    }
}
