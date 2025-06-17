package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    public List<Usuario> buscarTodosUsuarios();
    public void registrarUsuario(Usuario usuario);
    public void atualizarUsuario(Usuario usuario);
    public void excluirUsuario(Usuario usuario);
    public Usuario buscarUsuarioID(Long id);
    public Usuario buscarUsuarioLogin(String email);
    public Usuario buscarUsuarioNome(String nome);
}
