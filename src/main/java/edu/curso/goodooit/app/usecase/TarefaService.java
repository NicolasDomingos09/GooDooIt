package edu.curso.goodooit.app.usecase;

import edu.curso.goodooit.domain.model.Projeto;
import edu.curso.goodooit.domain.model.Tarefa;
import edu.curso.goodooit.infra.repository.ProjetoDAO;
import edu.curso.goodooit.infra.repository.TarefaDAO;
import edu.curso.goodooit.infra.repository.UsuarioDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TarefaService {
    private final TarefaDAO tarefaDAO;
    private final UsuarioDAO usuarioDAO;
    private final ProjetoDAO projetoDAO;

    public TarefaService(TarefaDAO tarefaDAO, UsuarioDAO usuarioDAO, ProjetoDAO projetoDAO) {
        this.tarefaDAO = tarefaDAO;
        this.usuarioDAO = usuarioDAO;
        this.projetoDAO = projetoDAO;
    }

    /*
        Status.ID = 1 = Em Andamento
        Status.ID = 2 = Concluído
        Status.ID = 3 = Cancelado
     */

    private boolean existeNull(String nome, LocalDate dataInicio, LocalDate dataFinal, int prioridade, Integer idCriador, Integer idProjeto) {
        return nome == null || nome.trim().isEmpty() ||
                dataInicio == null ||
                dataFinal == null ||
                prioridade < 1 || prioridade > 5 ||
                idCriador == null ||
                idProjeto == null;
    }

    public Tarefa criarTarefa(String nome, String descricao, LocalDate dataInicio, LocalDate dataFinal, int prioridade, Integer idCriador, Integer idResponsavel, Integer idProjeto) throws SQLException, Exception {
        if (existeNull(nome, dataInicio, dataFinal, prioridade, idCriador, idProjeto))
            throw new Exception("Apenas a descricao pode ser vazia");

        if (dataInicio.isBefore(dataFinal))
            throw new Exception("Data de finalização deve ser maior que data de inicio");

        Tarefa novaTarefa;
        if (idResponsavel == null) {
            novaTarefa = new Tarefa(nome, descricao, dataInicio, dataFinal, LocalDate.now(), prioridade, idCriador, 1, null, idProjeto);
        } else {
            novaTarefa = new Tarefa(nome, descricao, dataInicio, dataFinal, LocalDate.now(), prioridade, idCriador, 1, idResponsavel, idProjeto);
        }

        Integer novoId = tarefaDAO.registrarTarefa(novaTarefa);
        novaTarefa.setID(novoId);
        return novaTarefa;
    }

    public Tarefa editarTarefa(Integer idTarefa, String nome, String descricao, LocalDate dataInicio, LocalDate dataFinal, int prioridade, Integer idUsuario) throws Exception {
        Tarefa tarefaAtualizada = tarefaDAO.buscarTarefaId(idTarefa);
        Projeto p = projetoDAO.buscarProjetoId(tarefaAtualizada.getProjetoID());

        if (!Objects.equals(p.getLiderID(), idUsuario) || !Objects.equals(tarefaAtualizada.getCriadorID(), idUsuario))
            throw new Exception("Somente o lider do projeto ou o criador da tarefa pode editá-la");

        if (nome == null || dataInicio == null || dataFinal == null || prioridade < 1 || prioridade > 5)
            throw new Exception("Apenas a descricao pode ser vazia");

        tarefaAtualizada.setNome(nome);
        tarefaAtualizada.setDescricao(descricao);
        tarefaAtualizada.setDataInicio(dataInicio);
        tarefaAtualizada.setDataFim(dataFinal);
        tarefaAtualizada.setPrioridade(prioridade);

        tarefaDAO.atualizarTarefa(tarefaAtualizada);
        return tarefaAtualizada;
    }

    public void excluirTarefa(Integer idTarefa, Integer idUsuario) throws Exception {
        Tarefa tarefaExcluida = tarefaDAO.buscarTarefaId(idTarefa);


        tarefaDAO.excluirTarefa(tarefaExcluida);
    }

    public List<Tarefa> listarTarefasResponsavel(Integer idResponsavel) throws Exception {
        return tarefaDAO.buscarTarefaIdResponsavel(idResponsavel);
    }

    public List<Tarefa> listarTarefasResponsavelProjeto(Integer idResponsavel, Integer idProjeto) throws Exception {
        return tarefaDAO.buscarTarefaIdResponsavelProjeto(idResponsavel, idProjeto);
    }


}
