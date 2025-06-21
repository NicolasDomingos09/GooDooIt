package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Tarefa;
import edu.curso.goodooit.domain.repository.ITarefaDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {
    private final DataBaseConnection dbConn;

    public TarefaDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private Tarefa construirTarefa(ResultSet rs) throws SQLException {
        return new Tarefa(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getInt("posicao"),
                rs.getDate("data_inicio").toLocalDate(),
                rs.getDate("data_fim").toLocalDate(),
                rs.getDate("data_criacao").toLocalDate(),
                rs.getInt("prioridade"),
                rs.getInt("Status_TarefaID"),
                rs.getInt("QuadroID"),
                rs.getInt("listaID"),
                rs.getInt("criadorID"),
                rs.getInt("responsavelID")
        );
    }

    @Override
    public List<Tarefa> buscarTodosTarefa() throws SQLException {
        String sql = "SELECT * FROM tarefa";
        List<Tarefa> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(construirTarefa(rs));
            }
        }
        return lista;
    }

    @Override
    public Tarefa buscarTarefaId(Integer id) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirTarefa(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Tarefa buscarTarefaNome(String nome) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE nome LIKE ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirTarefa(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Tarefa> buscarTarefaIdResponsavel(Integer idResponsavel) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE ResponsavelID = ?";
        return comporLista(idResponsavel, sql);
    }

    @Override
    public List<Tarefa> buscarTarefaIdCriador(Integer idCriador) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE CriadorID = ?";
        return comporLista(idCriador, sql);
    }


    private List<Tarefa> comporLista(Integer id, String sql) throws SQLException {
        List<Tarefa> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(construirTarefa(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public List<Tarefa> buscarTarefaIdLista(Integer idLista) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE ListaID = ?";
        return comporLista(idLista, sql);
    }


    @Override
    public List<Tarefa> buscarTarefaPrioridade(int prioridade) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE prioridade = ?";
        return comporLista(prioridade, sql);
    }

    @Override
    public Tarefa registrarTarefa(Tarefa tarefa) throws SQLException {
        String sqlInsert = """
                    INSERT INTO tarefa (
                      nome,
                      descricao,
                      posicao,
                      data_inicio,
                      data_fim,
                      data_criacao,
                      prioridade,
                      Status_TarefaID,
                      QuadroID,
                      ListaID,
                      CriadorID,
                      ResponsavelID
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tarefa.getNome());
            ps.setString(2, tarefa.getDescricao());
            ps.setInt(3, tarefa.getPosicao());
            ps.setDate(4, Date.valueOf(tarefa.getDataInicio()));
            ps.setDate(5, Date.valueOf(tarefa.getDataFim()));
            ps.setDate(6, Date.valueOf(tarefa.getDataCriacao()));
            ps.setInt(7, tarefa.getPrioridade());
            ps.setInt(8, tarefa.getStatusTarefaID());
            ps.setInt(9, tarefa.getQuadroID());
            ps.setInt(10, tarefa.getListaID());
            ps.setInt(11, tarefa.getCriadorID());
            ps.setInt(12, tarefa.getResponsavelID());

            int linhas = ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int novoId = keys.getInt(1);
                    // Recupera e retorna a entidade completa
                    String sqlSelect = "SELECT * FROM tarefa WHERE id = ?";
                    try (PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {
                        psSelect.setInt(1, novoId);
                        try (ResultSet rs = psSelect.executeQuery()) {
                            if (rs.next()) {
                                return construirTarefa(rs);
                            } else {
                                throw new SQLException("Tarefa inserida mas não encontrada.");
                            }
                        }
                    }
                } else {
                    throw new SQLException("Falha ao obter ID da tarefa inserida.");
                }
            }
        }
    }

    @Override
    public void excluirTarefa(Tarefa tarefa) throws SQLException {
        String sql = "DELETE FROM tarefa WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tarefa.getID());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarTarefa(Tarefa tarefa) throws SQLException {
        String sql = """
                    UPDATE tarefa SET
                      nome               = ?,
                      descricao          = ?,
                      posicao            = ?,
                      data_inicio        = ?,
                      data_fim           = ?,
                      data_criacao       = ?,
                      prioridade         = ?,
                      Status_TarefaID   = ?,
                      QuadroID          = ?,
                      ListaID          = ?,
                      CriadorID         = ?,
                      ResponsavelID     = ?
                    WHERE id = ?
                """;
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tarefa.getNome());
            ps.setString(2, tarefa.getDescricao());
            ps.setInt(3, tarefa.getPosicao());
            ps.setDate(4, Date.valueOf(tarefa.getDataInicio()));
            ps.setDate(5, Date.valueOf(tarefa.getDataFim()));
            ps.setDate(6, Date.valueOf(tarefa.getDataCriacao()));
            ps.setInt(7, tarefa.getPrioridade());
            ps.setInt(8, tarefa.getStatusTarefaID());
            ps.setInt(9, tarefa.getQuadroID());
            ps.setInt(10, tarefa.getListaID());
            ps.setInt(11, tarefa.getCriadorID());
            ps.setInt(12, tarefa.getResponsavelID());
            ps.setInt(13, tarefa.getID());

            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}
