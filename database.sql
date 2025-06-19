CREATE DATABASE GooDooIt;
USE GooDooIt;

-- Problemas referente a autoincrementos de valores muito instáveis (como com cálculo aritmetico ou de acordo com o ano ou outros atributos

-- Opção 1 (temporária): IDENTITY(2025100, 1)
-- Pró: funciona incrementando os valores da forma que se pede
-- Contra: Funcionaria somente no ano atual. Depois o IDENTITY deverá ser atualizado

-- Opção 2: CONSTRAINT chk_projeto_id CHECK (ID >= YEAR(CURRENT_DATE) * 100 AND ID < (YEAR(CURRENT_DATE) + 1) * 1000),
-- Pró: Confirma que os primeiros 4 dígitos serão do ano de criação do projeto e valida que a quantidade de dígitos total será de 7
-- Contra: não tem autoincrement, logo a adição dos projetos não terá uma sequência certa nos últimos 3 dígitos

-- Opção 3: PROCEDURES e TRIGGERS
-- Pró: Aparentemente resolve os contras de ambas as opções anteriores
-- Contra: Foge do conteúdo de Banco de Dados, já iria de aprendizado para o Laboratório de BD isso


CREATE TABLE Usuario(
	ID int IDENTITY(10000, 1) NOT NULL,
	nome varchar(100) NOT NULL,
	login varchar(100) UNIQUE NOT NULL, 
	senha varchar(50) NOT NULL,
	email varchar(50) NOT NULL
	PRIMARY KEY(ID)
);

CREATE TABLE Status_Projeto(
	ID int CHECK(ID BETWEEN 1 AND 3) NOT NULL,
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
	UsuarioID int NOT NULL, 
	NotificacaoID int NOT NULL,
	visualizada bit NOT NULL DEFAULT 0
	PRIMARY KEY(UsuarioID, NotificacaoID)
	FOREIGN KEY(UsuarioID) REFERENCES Usuario(ID),
	FOREIGN KEY(NotificacaoID) REFERENCES Notificacao(ID)
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


CREATE TABLE Equipe(
	UsuarioID int NOT NULL,
	ProjetoID int NOT NULL,
	PRIMARY KEY(UsuarioID, ProjetoID),
	FOREIGN KEY(UsuarioID) REFERENCES Usuario(ID),
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID)
);


CREATE TABLE Quadro(
	ID int NOT NULL,
	titulo varchar(100) NOT NULL,
	ProjetoID int NOT NULL,
--	CONSTRAINT chk_quadro_id CHECK(ID BETWEEN ProjetoID + 1 AND ProjetoID + 3),
	PRIMARY KEY(ID),
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID)
);

-- Lista não possuí chave primária do Quadro, não é possível fazer uma CONSTRAINT nem autoincremento
CREATE TABLE Lista(
	ID int NOT NULL,
	titulo varchar(100) NOT NULL
	PRIMARY KEY(ID)
);

CREATE TABLE Lista_Quadro(
	ListaID int NOT NULL,
	QuadroID int NOT NULL,
	PRIMARY KEY(ListaID, QuadroID),
	FOREIGN KEY(ListaID) REFERENCES Lista(ID),
	FOREIGN KEY(QuadroID) REFERENCES Quadro(ID)
);

CREATE TABLE Status_Tarefa(
	ID int CHECK(ID BETWEEN 1 AND 4) NOT NULL,
	nome varchar(50) NOT NULL,
	descricao varchar(250) NOT NULL,
	ProjetoID int NOT NULL
	PRIMARY KEY(ID)
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID)
);

CREATE TABLE Tarefa(
	ID int NOT NULL,
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
	CONSTRAINT chk_tarefa_id CHECK(ID >= (ListaID * 10) * 100 AND ID >= (ListaID * 10) * 1000),
	PRIMARY KEY(ID),
	FOREIGN KEY(Status_TarefaID) REFERENCES Status_Tarefa(ID),
	FOREIGN KEY(QuadroID) REFERENCES Quadro(ID),
	FOREIGN KEY(ListaID) REFERENCES Lista(ID),
	FOREIGN KEY(CriadorID) REFERENCES Usuario(ID),
	FOREIGN KEY(ResponsavelID) REFERENCES Usuario(ID)	
);

CREATE TABLE Convite(
  ID int IDENTITY(400000, 1),
	RemetenteID int NOT NULL,
	ProjetoID int NOT NULL,
	DestinatarioID int NOT NULL,
	data_criacao DATE NOT NULL DEFAULT(GETDATE()),
	CONSTRAINT chk_remetente_destinario CHECK(RemetenteID <> DestinatarioID),
	PRIMARY KEY(ID, RemetenteID, ProjetoID, DestinatarioID),
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID),
	FOREIGN KEY(RemetenteID) REFERENCES Usuario(ID),
	FOREIGN KEY(DestinatarioID) REFERENCES Usuario(ID)
);

CREATE TABLE Comentario(
  ID int IDENTITY(300000, 1), 
	TarefaID int NOT NULL,
	UsuarioID int NOT NULL,
	texto varchar(255) NOT NULL,
	PRIMARY KEY(ID, TarefaID, UsuarioID),
	FOREIGN KEY(TarefaID) REFERENCES Tarefa(ID),
	FOREIGN KEY(UsuarioID) REFERENCES Usuario(ID)
);

INSERT INTO Usuario (nome, login, senha, email) VALUES
('Ana Costa', 'ana', '123456', 'ana@exemplo.com'),
('Bruno Lima', 'bruno', 'senha123', 'bruno@exemplo.com'),
('Carla Mendes', 'carla', 'abc123', 'carla@exemplo.com'),
('Daniel Souza', 'daniel', '456789', 'daniel@exemplo.com'),
('Eduardo Silva', 'eduardo', 'senha456', 'eduardo@exemplo.com'),
('Fernanda Rocha', 'fernanda', 'pass123', 'fernanda@exemplo.com'),
('Gabriel Almeida', 'gabriel', 'senha789', 'gabriel@exemplo.com'),
('Helena Torres', 'helena', 'hel123', 'helena@exemplo.com'),
('Igor Nunes', 'igor', 'pass456', 'igor@exemplo.com'),
('Juliana Faria', 'juliana', 'jul123', 'juliana@exemplo.com');

INSERT INTO Status_Projeto (ID, nome, descricao) VALUES
(1, 'Em Andamento', 'Projeto está em progresso'),
(2, 'Concluído', 'Projeto finalizado com sucesso'),
(3, 'Cancelado', 'Projeto foi interrompido');


INSERT INTO Notificacao (titulo, descricao, dataEnvio) VALUES
('Bem-vindo', 'Você foi cadastrado no sistema', GETDATE()),
('Nova Tarefa', 'Uma nova tarefa foi atribuída', GETDATE()),
('Prazo Final', 'Lembrete do prazo final', GETDATE()),
('Atualização de Projeto', 'Projeto atualizado', GETDATE()),
('Convite', 'Você foi convidado para um projeto', GETDATE()),
('Comentário', 'Comentaram na sua tarefa', GETDATE()),
('Aviso', 'Sistema ficará indisponível hoje', GETDATE()),
('Nova Equipe', 'Você foi adicionado a uma equipe', GETDATE()),
('Tarefa Atrasada', 'Tarefa passou do prazo', GETDATE());

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

INSERT INTO Equipe (UsuarioID, ProjetoID) VALUES
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
(10008, 500008, 0)


INSERT INTO Quadro (ID, titulo, ProjetoID) VALUES
(2025101, 'Quadro Principal', 2025100),
(2025102, 'Urgente', 2025100),
(2025103, 'Quadro A', 2025101),
(2025104, 'Quadro B', 2025101),
(2025105, 'Quadro C', 2025102),
(2025106, 'Quadro D', 2025102),
(2025107, 'Quadro E', 2025103),
(2025108, 'Quadro F', 2025103),
(2025109, 'Quadro G', 2025104),
(2025110, 'Quadro H', 2025104);


INSERT INTO Lista (ID, titulo) VALUES
(1, 'A Fazer'),
(2, 'Em Progresso'),
(3, 'Concluído'),
(4, 'Revisão'),
(5, 'Urgente'),
(6, 'Backlog'),
(7, 'Validação'),
(8, 'Testes'),
(9, 'Pronto para Produção'),
(10, 'Arquivado');


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


INSERT INTO Status_Tarefa (ID, nome, descricao, ProjetoID) VALUES
(1, 'Nova', 'Tarefa recém-criada', 2025100),
(2, 'Em andamento', 'Tarefa em execução', 2025100),
(3, 'Concluída', 'Tarefa finalizada', 2025100),
(4, 'Bloqueada', 'Tarefa com impedimentos', 2025100);


INSERT INTO Tarefa (ID, nome, descricao, posicao, data_inicio, data_fim, data_criacao, prioridade, Status_TarefaID, QuadroID, ListaID, CriadorID, ResponsavelID) VALUES
(10000, 'Criar Banco', 'Definir estrutura SQL', 1, '2025-01-01', '2025-01-05', GETDATE(), 1, 1, 2025101, 1, 10000, 10001),
(10001, 'Desenvolver API', 'Construir backend', 2, '2025-01-06', '2025-01-15', GETDATE(), 2, 2, 2025101, 1, 10000, 10001),
(10002, 'Layout Front', 'Criar protótipos', 1, '2025-01-10', '2025-01-20', GETDATE(), 3, 1, 2025101, 1, 10001, 10000),
(10003, 'Documentação', 'Manual do sistema', 3, '2025-01-15', '2025-01-25', GETDATE(), 2, 3, 2025101, 1, 10001, 10000),
(10004, 'Testes Unitários', 'Cobertura de código', 4, '2025-01-20', '2025-01-30', GETDATE(), 3, 4, 2025102, 1, 10000, 10001),
(10005, 'Deploy', 'Publicar sistema', 5, '2025-01-30', '2025-02-05', GETDATE(), 1, 1, 2025101, 1, 10000, 10001),
(10006, 'Integração Contínua', 'Configurar CI/CD', 6, '2025-02-01', '2025-02-10', GETDATE(), 2, 2, 2025102, 1, 10000, 10001),
(10007, 'Revisar Código', 'Analisar Pull Requests', 7, '2025-02-10', '2025-02-15', GETDATE(), 1, 1, 2025101, 1, 10001, 10000),
(10008, 'Criar Login', 'Autenticação de usuários', 8, '2025-02-15', '2025-02-20', GETDATE(), 1, 1, 2025101, 1, 10000, 10001),
(10009, 'Perfil Usuário', 'Editar dados pessoais', 9, '2025-02-20', '2025-02-25', GETDATE(), 2, 2, 2025101, 1, 10000, 10001);


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

SELECT * FROM Projeto;


PRINT CHAR(13) + CHAR(10) + "Tabela Usuario";
SELECT * FROM Usuario;
PRINT CHAR(13) + CHAR(10) + "Tabela Status_Projeto";
SELECT * FROM Status_Projeto;
PRINT CHAR(13) + CHAR(10) + "Tabela Notificacao";
SELECT * FROM Notificacao;
PRINT CHAR(13) + CHAR(10) + "Tabela Equipe";
SELECT * FROM Equipe;
PRINT CHAR(13) + CHAR(10) + "Tabela Usuario_Notificacao";
SELECT * FROM Usuario_Notificacao;
PRINT CHAR(13) + CHAR(10) + "Tabela Quadro";
SELECT * FROM Quadro;
PRINT CHAR(13) + CHAR(10) + "Tabela Lista";
SELECT * FROM Lista;
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