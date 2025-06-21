package edu.curso.goodooit.app.usecase;

import edu.curso.goodooit.domain.model.Projeto;
import edu.curso.goodooit.infra.repository.ConviteDAO;
import edu.curso.goodooit.infra.repository.ProjetoDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProjetoService {
    private final ProjetoDAO projetoDAO;
    private final ConviteDAO conviteDAO;

    public ProjetoService(ProjetoDAO projetoDAO, ConviteDAO conviteDAO) {
        this.projetoDAO = projetoDAO;
        this.conviteDAO = conviteDAO;
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
        List<Projeto> listaLider = projetoDAO.buscarProjetoUsuarioLider(idLider);

        return listaLider;
    }
}
