CREATE DATABASE GooDooIt;
USE GooDooIt;

CREATE TABLE Usuario(
    ID INT IDENTITY(10000, 1) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    login VARCHAR(100) UNIQUE NOT NULL, 
    senha VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE Status_Projeto(
	ID int IDENTITY,
	nome varchar(50) NOT NULL,
	descricao varchar(100)
	PRIMARY KEY(ID)
);

CREATE TABLE Notificacao(
	ID int IDENTITY(500000, 1) NOT NULL,
	titulo varchar(100) NOT NULL,
	descricao varchar(100) NOT NULL,
	dataEnvio DATE NOT NULL DEFAULT(GETDATE())
	PRIMARY KEY(ID)
);

CREATE TABLE Usuario_Notificacao(
    UsuarioID INT NOT NULL,
    NotificacaoID INT NOT NULL,
    visualizada BIT NOT NULL DEFAULT 0,
    PRIMARY KEY (UsuarioID, NotificacaoID),
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(ID),
    FOREIGN KEY (NotificacaoID) REFERENCES Notificacao(ID)
);

CREATE TABLE Projeto(
	ID int IDENTITY(2025100, 1) NOT NULL,
	nome varchar(100) NOT NULL,
	descricao varchar(250),
	data_inicio date NOT NULL,
	data_fim date NOT NULL,
	data_criacao DATE NOT NULL DEFAULT(GETDATE()),
	Status_ProjetoID int NOT NULL,
	LiderID int NOT NULL
	PRIMARY KEY(ID),
	FOREIGN KEY(Status_ProjetoID) REFERENCES Status_Projeto(ID),
	FOREIGN KEY(LiderID) REFERENCES Usuario(ID)
);


CREATE TABLE Usuario_Projeto(
	UsuarioID int NOT NULL,
	ProjetoID int NOT NULL,
	PRIMARY KEY(UsuarioID, ProjetoID),
	FOREIGN KEY(UsuarioID) REFERENCES Usuario(ID),
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID)
);


CREATE TABLE Quadro(
	ID int IDENTITY(2025100, 1) NOT NULL,
	titulo varchar(100) NOT NULL,
	ProjetoID int NOT NULL,
--	CONSTRAINT chk_quadro_id CHECK(ID BETWEEN ProjetoID + 1 AND ProjetoID + 3),
	PRIMARY KEY(ID),
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID)
);

-- Lista não possuí chave primária do Quadro, não é possível fazer uma CONSTRAINT nem autoincremento
CREATE TABLE Lista(
	ID int IDENTITY NOT NULL,
	titulo varchar(100) NOT NULL
	PRIMARY KEY(ID)
);

CREATE TABLE Usuario_Lista(
	UsuarioID int,
	ListaID int,
	FOREIGN KEY(UsuarioID) REFERENCES Usuario(ID),
	FOREIGN KEY(ListaID) REFERENCES Lista(ID)
);

CREATE TABLE Lista_Quadro(
	ListaID int NOT NULL,
	QuadroID int NOT NULL,
	PRIMARY KEY(ListaID, QuadroID),
	FOREIGN KEY(ListaID) REFERENCES Lista(ID),
	FOREIGN KEY(QuadroID) REFERENCES Quadro(ID)
);

CREATE TABLE Status_Tarefa(
	ID int IDENTITY NOT NULL,
	nome varchar(50) NOT NULL,
	descricao varchar(250) NOT NULL,
	ProjetoID int NOT NULL
	PRIMARY KEY(ID)
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID)
);

CREATE TABLE Tarefa(
	ID int IDENTITY(10000, 1) NOT NULL,
	nome varchar(100) NOT NULL,
	descricao varchar(250),
	posicao int NOT NULL DEFAULT 1,
	data_inicio date NOT NULL,
	data_fim date NOT NULL,
	data_criacao DATE NOT NULL DEFAULT(GETDATE()),
	prioridade int NOT NULL DEFAULT 3,
	Status_TarefaID int NOT NULL,
	QuadroID int NOT NULL,
	ListaID int NOT NULL,
	CriadorID int NOT NULL,
	ResponsavelID int NOT NULL,
	-- CONSTRAINT chk_tarefa_id CHECK(ID >= (ListaID * 10) * 100 AND ID >= (ListaID * 10) * 1000),
	PRIMARY KEY(ID),
	FOREIGN KEY(Status_TarefaID) REFERENCES Status_Tarefa(ID),
	FOREIGN KEY(QuadroID) REFERENCES Quadro(ID),
	FOREIGN KEY(ListaID) REFERENCES Lista(ID),
	FOREIGN KEY(CriadorID) REFERENCES Usuario(ID),
	FOREIGN KEY(ResponsavelID) REFERENCES Usuario(ID)	
);

CREATE TABLE Convite(
	RemetenteID int NOT NULL,
	ProjetoID int NOT NULL,
	DestinatarioID int NOT NULL,
	data_criacao DATE NOT NULL DEFAULT(GETDATE()),
	CONSTRAINT chk_remetente_destinario CHECK(RemetenteID <> DestinatarioID),
	PRIMARY KEY(RemetenteID, ProjetoID, DestinatarioID),
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID),
	FOREIGN KEY(RemetenteID) REFERENCES Usuario(ID),
	FOREIGN KEY(DestinatarioID) REFERENCES Usuario(ID)
);

CREATE TABLE Comentario(
	TarefaID int NOT NULL,
	UsuarioID int NOT NULL,
	texto varchar(255) NOT NULL,
	dataCriacao date NOT NULL DEFAULT (GETDATE())
	PRIMARY KEY(TarefaID, UsuarioID),
	FOREIGN KEY(TarefaID) REFERENCES Tarefa(ID),
	FOREIGN KEY(UsuarioID) REFERENCES Usuario(ID)
);

INSERT INTO Usuario (nome, sobrenome, login, senha, email) VALUES
('Ana', 'Costa', 'ana', '123456', 'ana@exemplo.com'),
('Bruno', 'Lima', 'bruno', 'senha123', 'bruno@exemplo.com'),
('Carla', 'Mendes', 'carla', 'abc123', 'carla@exemplo.com'),
('Daniel', 'Souza', 'daniel', '456789', 'daniel@exemplo.com'),
('Eduardo', 'Silva', 'eduardo', 'senha456', 'eduardo@exemplo.com'),
('Fernanda', 'Rocha', 'fernanda', 'pass123', 'fernanda@exemplo.com'),
('Gabriel', 'Almeida', 'gabriel', 'senha789', 'gabriel@exemplo.com'),
('Helena', 'Torres', 'helena', 'hel123', 'helena@exemplo.com'),
('Igor', 'Nunes', 'igor', 'pass456', 'igor@exemplo.com'),
('Juliana', 'Faria', 'juliana', 'jul123', 'juliana@exemplo.com');

INSERT INTO Status_Projeto (nome, descricao) VALUES
('Em Andamento', 'Projeto está em progresso'),
('Concluído', 'Projeto finalizado com sucesso'),
('Cancelado', 'Projeto foi interrompido');


INSERT INTO Notificacao (titulo, descricao, dataEnvio) VALUES
('Bem-vindo', 'Você foi cadastrado no sistema', GETDATE()),
('Nova Tarefa', 'Uma nova tarefa foi atribuída', GETDATE()),
('Prazo Final', 'Lembrete do prazo final', GETDATE()),
('Atualização de Projeto', 'Projeto atualizado', GETDATE()),
('Convite', 'Você foi convidado para um projeto', GETDATE()),
('Comentário', 'Comentaram na sua tarefa', GETDATE()),
('Aviso', 'Sistema ficará indisponível hoje', GETDATE()),
('Nova Equipe', 'Você foi adicionado a uma equipe', GETDATE()),
('Tarefa Atrasada', 'Tarefa passou do prazo', GETDATE()),
('Feedback', 'Você recebeu um feedback', GETDATE());

-- SET IDENTITY_INSERT Projeto ON;
INSERT INTO Projeto (nome, descricao, data_inicio, data_fim, data_criacao, Status_ProjetoID, LiderID) VALUES
('Sistema de Tarefas', 'Controle de tarefas e quadros', '2025-01-01', '2025-12-31', GETDATE(), 1, 10000),
('App Financeiro', 'Aplicativo para gestão financeira', '2025-02-01', '2025-11-30', GETDATE(), 1, 10001),
('Loja Virtual', 'Plataforma de e-commerce', '2025-03-01', '2025-12-15', GETDATE(), 2, 10002),
('Sistema Escolar', 'Gerenciamento acadêmico', '2025-01-15', '2025-10-20', GETDATE(), 1, 10003),
('Chat Online', 'Ferramenta de comunicação', '2025-04-01', '2025-09-30', GETDATE(), 3, 10004),
('Blog Pessoal', 'Plataforma de blogs', '2025-05-01', '2025-08-31', GETDATE(), 1, 10005),
('Rede Social', 'Projeto de rede social', '2025-06-01', '2025-12-01', GETDATE(), 2, 10006),
('Sistema RH', 'Recursos Humanos', '2025-03-01', '2025-11-01', GETDATE(), 1, 10007),
('Agenda Médica', 'Sistema para clínicas', '2025-02-15', '2025-10-15', GETDATE(), 3, 10008),
('Controle de Estoque', 'Gestão de produtos', '2025-01-10', '2025-09-10', GETDATE(), 1, 10009);
-- SET IDENTITY_INSERT Projeto OFF;

INSERT INTO Usuario_Projeto (UsuarioID, ProjetoID) VALUES
(10000, 2025100),
(10001, 2025100),
(10002, 2025101),
(10003, 2025101),
(10004, 2025102),
(10005, 2025102),
(10006, 2025103),
(10007, 2025103),
(10008, 2025104),
(10009, 2025104);


INSERT INTO Usuario_Notificacao (UsuarioID, NotificacaoID, visualizada) VALUES
(10000, 500000, 1),
(10001, 500001, 0),
(10002, 500002, 1),
(10003, 500003, 0),
(10004, 500004, 1),
(10005, 500005, 0),
(10006, 500006, 0),
(10007, 500007, 1),
(10008, 500008, 0),
(10009, 500009, 1);


INSERT INTO Quadro (titulo, ProjetoID) VALUES
('Quadro Principal', 2025100),
('Urgente', 2025100),
('Quadro A', 2025101),
('Quadro B', 2025101),
('Quadro C', 2025102),
('Quadro D', 2025102),
('Quadro E', 2025103),
('Quadro F', 2025103),
('Quadro G', 2025104),
('Quadro H', 2025104);


INSERT INTO Lista (titulo) VALUES
('A Fazer'),
('2Em Progresso'),
('Concluído'),
('Revisão'),
('Urgente'),
('Backlog'),
('Validação'),
('Testes'),
('Pronto para Produção'),
('Arquivado');

INSERT INTO Usuario_Lista
VALUES (10001, 1),
	   (10004, 2),
	   (10002, 3),
	   (10005, 7),
	   (10005, 3),
	   (10001, 8),
	   (10002, 9),
	   (10003, 2),
	   (10004, 1),
	   (10005, 7);


INSERT INTO Lista_Quadro (ListaID, QuadroID) VALUES
(1, 2025101),
(2, 2025101),
(3, 2025101),
(4, 2025102),
(5, 2025102),
(6, 2025103),
(7, 2025103),
(8, 2025104),
(9, 2025104),
(10, 2025105);


INSERT INTO Status_Tarefa (nome, descricao, ProjetoID) VALUES
('Nova', 'Tarefa recém-criada', 2025100),
('Em andamento', 'Tarefa em execução', 2025100),
('Concluída', 'Tarefa finalizada', 2025100),
('Bloqueada', 'Tarefa com impedimentos', 2025100);


INSERT INTO Tarefa (nome, descricao, posicao, data_inicio, data_fim, data_criacao, prioridade, Status_TarefaID, QuadroID, ListaID, CriadorID, ResponsavelID) VALUES
('Criar Banco', 'Definir estrutura SQL', 1, '2025-01-01', '2025-01-05', GETDATE(), 1, 1, 2025101, 1, 10000, 10001),
('Desenvolver API', 'Construir backend', 2, '2025-01-06', '2025-01-15', GETDATE(), 2, 2, 2025101, 1, 10000, 10001),
('Layout Front', 'Criar protótipos', 1, '2025-01-10', '2025-01-20', GETDATE(), 3, 1, 2025101, 1, 10001, 10000),
('Documentação', 'Manual do sistema', 3, '2025-01-15', '2025-01-25', GETDATE(), 2, 3, 2025101, 1, 10001, 10000),
('Testes Unitários', 'Cobertura de código', 4, '2025-01-20', '2025-01-30', GETDATE(), 3, 4, 2025102, 1, 10000, 10001),
('Deploy', 'Publicar sistema', 5, '2025-01-30', '2025-02-05', GETDATE(), 1, 1, 2025101, 1, 10000, 10001),
('Integração Contínua', 'Configurar CI/CD', 6, '2025-02-01', '2025-02-10', GETDATE(), 2, 2, 2025102, 1, 10000, 10001),
('Revisar Código', 'Analisar Pull Requests', 7, '2025-02-10', '2025-02-15', GETDATE(), 1, 1, 2025101, 1, 10001, 10000),
('Criar Login', 'Autenticação de usuários', 8, '2025-02-15', '2025-02-20', GETDATE(), 1, 1, 2025101, 1, 10000, 10001),
('Perfil Usuário', 'Editar dados pessoais', 9, '2025-02-20', '2025-02-25', GETDATE(), 2, 2, 2025101, 1, 10000, 10001);


INSERT INTO Convite (RemetenteID, ProjetoID, DestinatarioID) VALUES
(10002, 2025100, 10001),
(10001, 2025101, 10002),
(10009, 2025102, 10003),
(10002, 2025103, 10004),
(10006, 2025104, 10005),
(10001, 2025105, 10006),
(10003, 2025106, 10007),
(10009, 2025107, 10008),
(10000, 2025108, 10009),
(10006, 2025109, 10000);

INSERT INTO Comentario (TarefaID, UsuarioID, texto) VALUES
(10000, 10000, 'Início da modelagem'),
(10001, 10001, 'API em desenvolvimento'),
(10002, 10000, 'Layout inicial feito'),
(10003, 10001, 'Documentação em progresso'),
(10004, 10000, 'Testes iniciados'),
(10005, 10001, 'Deploy previsto para hoje'),
(10006, 10000, 'Pipeline criado'),
(10007, 10001, 'Pull Requests revisados'),
(10008, 10000, 'Login funcional'),
(10009, 10001, 'Tela de perfil em construção');

SELECT *
                        FROM usuario u
                        INNER JOIN usuario_projeto up ON up.usuarioID = u.ID
                        WHERE up.projetoID = 2025111


PRINT CHAR(13) + CHAR(10) + "Tabela Usuario";
SELECT * FROM Usuario;
PRINT CHAR(13) + CHAR(10) + "Tabela Status_Projeto";
SELECT * FROM Status_Projeto;
PRINT CHAR(13) + CHAR(10) + "Tabela Notificacao";
SELECT * FROM Notificacao;
PRINT CHAR(13) + CHAR(10) + "Tabela Usuario_Projeto";
SELECT * FROM Usuario_Projeto;
PRINT CHAR(13) + CHAR(10) + "Tabela Usuario_Notificacao";
SELECT * FROM Usuario_Notificacao;
PRINT CHAR(13) + CHAR(10) + "Tabela Quadro";
SELECT * FROM Quadro;
PRINT CHAR(13) + CHAR(10) + "Tabela Lista";
SELECT * FROM Lista;
PRINT CHAR(13) + CHAR(10) + "Tabela Usuario_Lista";
SELECT * FROM Usuario_Lista;
PRINT CHAR(13) + CHAR(10) + "Tabela Lista_Quadro";
SELECT * FROM Lista_Quadro;
PRINT CHAR(13) + CHAR(10) + "Tabela Status_Tarefa";
SELECT * FROM Status_Tarefa;
PRINT CHAR(13) + CHAR(10) + "Tabela Tarefa";
SELECT * FROM Tarefa;
PRINT CHAR(13) + CHAR(10) + "Tabela Convite";
SELECT * FROM Convite;
PRINT CHAR(13) + CHAR(10) + "Tabela Comentario";
SELECT * FROM Comentario;