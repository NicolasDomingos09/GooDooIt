package edu.curso.goodooit.app.usecase;

import edu.curso.goodooit.domain.model.*;
import edu.curso.goodooit.infra.repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProjetoService {
    private final ProjetoDAO projetoDAO;
//    private final StatusProjetoDAO statusProjetoDAO;
    private final StatusDAO statusDAO;
    /*
        statusProjetoID = 1 = Em Andamento
        statusProjetoID = 2 = Concluído
        statusProjetoID = 3 = Cancelado
        mesma coisa para Status
     */

//    public ProjetoService(ProjetoDAO projetoDAO, StatusProjetoDAO statusProjetoDAO, StatusDAO statusDAO) {
//        this.projetoDAO = projetoDAO;
//        this.statusProjetoDAO = statusProjetoDAO;
//        this.statusDAO = statusDAO;
//    }


    public ProjetoService(ProjetoDAO projetoDAO, StatusDAO statusDAO) {
        this.projetoDAO = projetoDAO;
        this.statusDAO = statusDAO;
    }

    private boolean existeNull(String nome, String descricao, LocalDate dataInicio, LocalDate dataFinal) {
        return nome == null || descricao == null || dataInicio == null || dataFinal == null;
    }

    public Projeto criarProjeto(String nome, String descricao, LocalDate dataInicio, LocalDate dataFinal, Integer idUsuario) throws Exception {
        if (existeNull(nome, descricao, dataInicio, dataFinal))
            throw new Exception("Todos os valores sao obrigatorios");

        Projeto novoProjeto = new Projeto(nome, descricao, dataInicio, dataFinal, LocalDate.now(), 1, idUsuario);

        if (dataInicio.isBefore(dataFinal))
            throw new Exception("Data de finalização deve ser maior que data de inicio");

        Integer novoId = projetoDAO.registrarProjeto(novoProjeto);
        novoProjeto.setID(novoId);
        return novoProjeto;
    }

    public Projeto editarProjeto(Integer idProjeto, String nome, String descricao, LocalDate dataInicio, LocalDate dataFinal, Integer idUsuario) throws Exception {
        if (existeNull(nome, descricao, dataInicio, dataFinal))
            throw new Exception("Todos os valores sao obrigatorios");

        if (dataInicio.isBefore(dataFinal))
            throw new Exception("Data de finalização deve ser maior que data de inicio");

        Projeto projetoAtualizado = projetoDAO.buscarProjetoId(idProjeto);

        if (!Objects.equals(idUsuario, projetoAtualizado.getLiderID()))
            throw new Exception("Somente o lider do projeto pode editar o projeto");

        projetoAtualizado.setNome(nome);
        projetoAtualizado.setDescricao(descricao);
        projetoAtualizado.setDataInicio(dataInicio);
        projetoAtualizado.setDataFim(dataFinal);

        projetoDAO.atualizarProjeto(projetoAtualizado);
        return projetoAtualizado;
    }

    public List<Projeto> listarProjetosLider(Integer idLider) throws SQLException {
        return projetoDAO.buscarProjetoUsuarioLider(idLider);
    }

    public List<Projeto> listarProjetosColaborador(Integer idUsuario) throws SQLException {
        return projetoDAO.buscarProjetoUsuarioColaborador(idUsuario);
    }

    public void excluirProjeto(Integer idProjeto, Integer idUsuario) throws Exception {
        Projeto projetoExcluido = projetoDAO.buscarProjetoId(idProjeto);

        if (!Objects.equals(idUsuario, projetoExcluido.getLiderID()))
            throw new Exception("Somente o lider do projeto pode excluir o projeto");

        projetoDAO.excluirProjeto(projetoExcluido);
    }

    public Projeto buscarProjetoId(Integer idProjeto) throws SQLException {
        return projetoDAO.buscarProjetoId(idProjeto);
    }

    public Projeto buscarProjetoNome(String nomeProjeto) throws SQLException {
        return projetoDAO.buscarProjetoNome(nomeProjeto);
    }

    public Projeto definirStatus(Integer idStatus, Integer idProjeto) throws SQLException {
        Status st = statusDAO.buscarStatusId(idStatus);
        Projeto p = projetoDAO.buscarProjetoId(idProjeto);

        p.setStatusProjetoID(idStatus);
        projetoDAO.atualizarProjeto(p);  //status atualizado no banco

        return p;
    }

}
