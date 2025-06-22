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

public class FXVisualizarNotificacoes extends Application {

    @Override
    public void start(Stage primaryStage) {
        double larguraTela = Screen.getPrimary().getBounds().getWidth();
        double alturaTela = Screen.getPrimary().getBounds().getHeight();

        primaryStage.setTitle("Notificações - Julia Fernandes");

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
        Label sino = new Label("🔔 2");
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

        // Painel roxo de notificações
        VBox notificacoesBox = new VBox(20);
        notificacoesBox.setPadding(new Insets(20));
        notificacoesBox.setStyle("-fx-background-color: #b39ddb; -fx-background-radius: 15px;");
        notificacoesBox.setPrefWidth(700);

        Label titulo = new Label("Notificações");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-family: monospace; -fx-text-fill: black;");

        VBox listaNotificacoes = new VBox(20);
        listaNotificacoes.getChildren().addAll(
                blocoNotificacao("17/06/2025  18:03", "Atualização de status", 
                        "O status do projeto “furão biônico” foi alterado para “em andamento”"),
                blocoNotificacao("17/06/2025  18:03", "Novo participante na lista", 
                        "Nicolas Domingos agora faz parte da lista “olho laser”"),
                blocoNotificacao("16/06/2025  18:03", "Atribuição de tarefa", 
                        "Gustavo atribuiu uma tarefa a você “Desenvolver olho biônico”")
        );

        ScrollPane scroll = new ScrollPane(listaNotificacoes);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        notificacoesBox.getChildren().addAll(titulo, scroll);

        // Painel cinza
        StackPane painelCinza = new StackPane(notificacoesBox);
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

    private VBox blocoNotificacao(String dataHora, String titulo, String descricao) {
        VBox box = new VBox(8);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        box.setMaxWidth(Double.MAX_VALUE);

        Label data = new Label(dataHora);
        data.setStyle("-fx-font-family: monospace; -fx-font-size: 13px; -fx-text-fill: black;");

        Label tituloLbl = new Label(titulo);
        tituloLbl.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: black;");
        tituloLbl.setWrapText(true);

        Label desc = new Label(descricao);
        desc.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-text-fill: black;");
        desc.setWrapText(true);

        HBox acoes = new HBox(10);
        acoes.setAlignment(Pos.CENTER_RIGHT);

        Button lida = new Button("✔");
        lida.setStyle("-fx-font-size: 14px; -fx-font-family: monospace;");

        Button excluir = new Button("🗑");
        excluir.setStyle("-fx-font-size: 14px; -fx-font-family: monospace;");

        Label lidaTxt = new Label("Lida");
        lidaTxt.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-text-fill: black;");

        Label excluirTxt = new Label("Excluir");
        excluirTxt.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-text-fill: black;");

        acoes.getChildren().addAll(lida, lidaTxt, excluir, excluirTxt);

        box.getChildren().addAll(data, tituloLbl, desc, acoes);
        return box;
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
