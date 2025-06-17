CREATE DATABASE GooDooIt;
USE GooDooIt;

CREATE TABLE Usuario(
	ID int NOT NULL,
	nome varchar(100) NOT NULL,
	login varchar(100) UNIQUE NOT NULL, 
	senha varchar(50) NOT NULL,
	email varchar(50) NOT NULL
	PRIMARY KEY(ID)
);

CREATE TABLE Status_Projeto(
	ID int NOT NULL,
	nome varchar(50) NOT NULL,
	descricao varchar(100)
	PRIMARY KEY(ID)
);

CREATE TABLE Notificacao(
	ID int NOT NULL,
	titulo varchar(100) NOT NULL,
	descricao varchar(100) NOT NULL,
	dataEnvio DATE NOT NULL DEFAULT(GETDATE())
	PRIMARY KEY(ID)
);

CREATE TABLE Usuario_Notificacao(
	UsuarioID int NOT NULL, 
	NotificacaoID int NOT NULL,
	visualizada bit NOT NULL DEFAULT 0
	PRIMARY KEY(UsuarioID, NotificacaoID),
	FOREIGN KEY(UsuarioID) REFERENCES Usuario(ID),
	FOREIGN KEY(NotificacaoID) REFERENCES Notificacao(ID),
);

CREATE TABLE Projeto(
	ID int NOT NULL,
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
	ProjetoID int NOT NULL
	PRIMARY KEY(ID),
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID)
);

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
	ID int NOT NULL,
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
	data_criacao DATE NOT NULL DEFAULT(GETDATE())
	PRIMARY KEY(RemetenteID, ProjetoID, DestinatarioID),
	FOREIGN KEY(ProjetoID) REFERENCES Projeto(ID),
	FOREIGN KEY(RemetenteID) REFERENCES Usuario(ID),
	FOREIGN KEY(DestinatarioID) REFERENCES Usuario(ID)
);

CREATE TABLE Comentario(
	TarefaID int NOT NULL,
	UsuarioID int NOT NULL,
	texto varchar(255) NOT NULL,
	
	PRIMARY KEY(TarefaID, UsuarioID),
	FOREIGN KEY(TarefaID) REFERENCES Tarefa(ID),
	FOREIGN KEY(UsuarioID) REFERENCES Usuario(ID)
);
