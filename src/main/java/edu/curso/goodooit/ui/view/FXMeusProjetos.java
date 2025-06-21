package edu.curso.goodooit.ui.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMeusProjetos extends Application {

    @Override
    public void start(Stage primaryStage) {
        double larguraTela = Screen.getPrimary().getBounds().getWidth();
        double alturaTela = Screen.getPrimary().getBounds().getHeight();

        primaryStage.setTitle("Meus Projetos - Julia Fernandes");

        // Sidebar
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #eeeeee;");
        sidebar.setPrefWidth(220);
        sidebar.setAlignment(Pos.TOP_CENTER);

        Image avatarImage = new Image(getClass().getResourceAsStream("/images/Goo.png"), 100, 100, true, true);
        ImageView avatarView = new ImageView(avatarImage);

        Label nome = new Label("Julia Fernandes");
        nome.setStyle("-fx-font-size: 18px; -fx-font-family: monospace;");

        HBox notificacoes = new HBox(20);
        notificacoes.setAlignment(Pos.CENTER);
        Label sino = new Label("🔔 5");
        Label email = new Label("✉️ 1");
        sino.setStyle("-fx-font-size: 16px;");
        email.setStyle("-fx-font-size: 16px;");
        notificacoes.getChildren().addAll(sino, email);

        sidebar.getChildren().addAll(avatarView, nome, notificacoes);
        sidebar.getChildren().addAll(
                botaoMenu("Meus projetos", true),
                botaoMenu("Colaborando", false),
                botaoMenu("Equipes", true),
                botaoMenu("Tarefas", false),
                botaoMenu("Editar perfil", true),
                botaoMenu("Sair", false)
        );

        // Painel principal
        VBox areaPrincipal = new VBox(30);
        areaPrincipal.setPadding(new Insets(20));
        areaPrincipal.setStyle("-fx-background-color: #b39ddb; -fx-background-radius: 15px;");
        areaPrincipal.setPrefWidth(700);

        Label titulo = new Label("Meus projetos");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-family: monospace; -fx-text-fill: black;");

        // Bloco de projeto com ícone textual
        VBox blocoProjeto = new VBox(10);
        blocoProjeto.setPadding(new Insets(20));
        blocoProjeto.setSpacing(10);
        blocoProjeto.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        blocoProjeto.setMaxWidth(Double.MAX_VALUE);

        Label nomeProjeto = new Label("Projeto galo eletrônico");
        Label status = new Label("Status: em andamento");
        Label concluidas = new Label("Tarefas concluídas: 25");
        Label outras = new Label("Outras tarefas: 95");

        for (Label lbl : new Label[]{nomeProjeto, status, concluidas, outras}) {
            lbl.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-text-fill: black;");
        }

        // Ícone ✎
        Label iconeEditar = new Label("✎");
        iconeEditar.setStyle("-fx-font-size: 18px; -fx-text-fill: gray; -fx-font-family: monospace;");

        StackPane editarStack = new StackPane(blocoProjeto, iconeEditar);
        StackPane.setAlignment(iconeEditar, Pos.TOP_RIGHT);
        StackPane.setMargin(iconeEditar, new Insets(10));

        blocoProjeto.getChildren().addAll(nomeProjeto, status, concluidas, outras);

        // Botão Criar projeto
        Button btnCriar = new Button("Criar novo projeto");
        btnCriar.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-background-color: #dddddd; -fx-background-radius: 10;");
        btnCriar.setMaxWidth(Double.MAX_VALUE);
        btnCriar.setPrefHeight(35);

        areaPrincipal.getChildren().addAll(titulo, editarStack, btnCriar);

        // Painel cinza de fundo
        StackPane painelCinza = new StackPane(areaPrincipal);
        painelCinza.setStyle("-fx-background-color: #dddddd; -fx-background-radius: 20px;");
        painelCinza.setPadding(new Insets(15));

        // Layout principal
        HBox root = new HBox();
        root.setPadding(new Insets(10));
        root.getChildren().addAll(sidebar, painelCinza);
        HBox.setHgrow(painelCinza, Priority.ALWAYS);

        Scene scene = new Scene(root, larguraTela, alturaTela * 0.9);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button botaoMenu(String texto, boolean roxo) {
        return botaoMenu(texto, roxo, false);
    }

    private Button botaoMenu(String texto, boolean roxo, boolean selecionado) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(40);
        btn.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-background-radius: 15;" +
                (selecionado ? "-fx-border-color: black; -fx-border-width: 2;" : "") +
                (roxo ? "-fx-background-color: #d681f0; -fx-text-fill: black;" : "-fx-background-color: #cccccc;"));
        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
