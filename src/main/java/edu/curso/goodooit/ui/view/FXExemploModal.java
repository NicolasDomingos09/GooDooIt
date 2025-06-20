package edu.curso.goodooit.ui.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXExemploModal extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tamanho da tela
        double larguraTela = Screen.getPrimary().getBounds().getWidth();
        double alturaTela = Screen.getPrimary().getBounds().getHeight();

        // Conteúdo principal
        Button btnAbrirModal = new Button("Abrir Modal");

        VBox conteudoPrincipal = new VBox(btnAbrirModal);
        conteudoPrincipal.setAlignment(Pos.CENTER);
        conteudoPrincipal.setSpacing(10);

        // Modal simulado (inicialmente invisível)
        VBox conteudoModal = new VBox(10);
        conteudoModal.setPadding(new Insets(20));
        conteudoModal.setAlignment(Pos.CENTER_LEFT);
        conteudoModal.setMaxWidth(larguraTela * 0.5);
        conteudoModal.setMaxHeight(alturaTela * 0.5);
        conteudoModal.setStyle("-fx-background-color: #A58ACA; -fx-background-radius: 10;");

        // Campos do formulário
        TextField tfNomeProjeto = new TextField();
        TextArea taDescricao = new TextArea();
        TextField tfPrazoInicio = new TextField();
        TextField tfPrazoFim = new TextField();

        Button btnSalvar = new Button("Salvar");
        Button btnCancelar = new Button("Cancelar");

        HBox botoes = new HBox(20, btnSalvar, btnCancelar);
        botoes.setAlignment(Pos.CENTER);

        conteudoModal.getChildren().addAll(
                new Label("Nome do Projeto"), tfNomeProjeto,
                new Label("Descrição"), taDescricao,
                new Label("Prazo previsto para início"), tfPrazoInicio,
                new Label("Prazo previsto para finalização"), tfPrazoFim,
                botoes
        );

        // Fundo escurecido que cobre tudo
        StackPane fundoModal = new StackPane();
        fundoModal.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        fundoModal.setVisible(false); // começa invisível
        fundoModal.setAlignment(Pos.CENTER);
        fundoModal.getChildren().add(conteudoModal);

        // O modal deve ser adicionado por padrão em um StackPane, para que mesmo invisível, ele não ocupe espaço nos Panels
        StackPane root = new StackPane();
        root.getChildren().addAll(conteudoPrincipal, fundoModal);

        // Ações dos botões
        btnAbrirModal.setOnAction(e -> fundoModal.setVisible(true));
        btnCancelar.setOnAction(e -> fundoModal.setVisible(false));
        btnSalvar.setOnAction(e -> {
            // Aqui você pode salvar os dados
            fundoModal.setVisible(false); // e fechar o modal
        });

        Scene scene = new Scene(root, larguraTela, alturaTela * 0.9);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Modal na Mesma Tela");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
