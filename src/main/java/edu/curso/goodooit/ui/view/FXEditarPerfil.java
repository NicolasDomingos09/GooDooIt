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

public class FXEditarPerfil extends Application {

    private VBox areaPrincipal; // referência para trocar o conteúdo dinamicamente

    @Override
    public void start(Stage primaryStage) {
        double larguraTela = Screen.getPrimary().getBounds().getWidth();
        double alturaTela = Screen.getPrimary().getBounds().getHeight();

        primaryStage.setTitle("Tarefas - Julia Fernandes");

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
                botaoMenu("Editar perfil", true, true), // ativa ação aqui
                botaoMenu("Sair", false)
        );

        // Painel principal
        StackPane painelCinza = new StackPane();
        painelCinza.setStyle("-fx-background-color: #dddddd; -fx-background-radius: 20px;");
        painelCinza.setPadding(new Insets(15));
        painelCinza.setMaxWidth(Double.MAX_VALUE);

        areaPrincipal = new VBox();
        areaPrincipal.setSpacing(20);
        areaPrincipal.setPadding(new Insets(20));
        areaPrincipal.setStyle("-fx-background-color: #b39ddb; -fx-background-radius: 15px;");
        areaPrincipal.setPrefWidth(700);

        painelCinza.getChildren().add(areaPrincipal);

        // Layout principal
        HBox root = new HBox();
        root.setPadding(new Insets(10));
        root.getChildren().addAll(sidebar, painelCinza);
        HBox.setHgrow(painelCinza, Priority.ALWAYS);

        // Cena
        Scene scene = new Scene(root, larguraTela, alturaTela * 0.9);
        primaryStage.setScene(scene);
        primaryStage.show();

        mostrarTelaEditarPerfil(); // Exibe a tela de perfil ao iniciar
    }

    private Button botaoMenu(String texto, boolean roxo) {
        return botaoMenu(texto, roxo, false);
    }

    private Button botaoMenu(String texto, boolean roxo, boolean ativaEditarPerfil) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(40);
        btn.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-background-radius: 15;" +
                (roxo ? "-fx-background-color: #d681f0; -fx-text-fill: black;" : "-fx-background-color: #cccccc;"));

        if (ativaEditarPerfil) {
            btn.setOnAction(e -> mostrarTelaEditarPerfil());
        }

        return btn;
    }

    private void mostrarTelaEditarPerfil() {
        areaPrincipal.getChildren().clear();

        Label titulo = new Label("Editar perfil");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-family: monospace; -fx-text-fill: black;");

        VBox form = new VBox(18);
        form.setPadding(new Insets(10));
        form.setStyle("-fx-font-family: monospace;");

        form.getChildren().addAll(
            criarCampoComIcone("Username", new TextField("jfernandes"), "⛔"),
            criarCampoComIcone("Nome", new TextField("Julia"), "✏️"),
            criarCampoComIcone("Sobrenome", new TextField("Fernandes"), "✏️"),
            criarCampoComIcone("Email", new TextField("julia@example.com"), "✏️"),
            criarCampoComIcone("Senha", new PasswordField(), "✏️")

        );

        HBox botoes = new HBox();
        botoes.setSpacing(10);
        botoes.setPadding(new Insets(20, 0, 0, 0));
        botoes.setAlignment(Pos.CENTER);

        Button salvarBtn = new Button("Salvar");
        Button cancelarBtn = new Button("Cancelar");

        salvarBtn.setPrefHeight(35);
        cancelarBtn.setPrefHeight(35);
        salvarBtn.setStyle("-fx-background-color: #d681f0; -fx-font-family: monospace;");
        cancelarBtn.setStyle("-fx-background-color: #cccccc; -fx-font-family: monospace;");

        Region espacoEntre = new Region();
        HBox.setHgrow(espacoEntre, Priority.ALWAYS);

        botoes.getChildren().addAll(salvarBtn, espacoEntre, cancelarBtn);

        areaPrincipal.getChildren().addAll(titulo, form, botoes);
    }

    private VBox criarCampoComIcone(String labelTexto, TextField campo, String icone) {
        Label label = new Label(labelTexto);
        label.setStyle("-fx-font-size: 14px; -fx-font-family: monospace; -fx-text-fill: black;");

        campo.setPrefHeight(35);
        campo.setStyle("-fx-background-radius: 10; -fx-font-size: 14px;");

        // Desabilita se for o campo username
        if (labelTexto.equalsIgnoreCase("Username") || labelTexto.equalsIgnoreCase("Senha")) {
            campo.setDisable(true);
            campo.setStyle(
                    "-fx-background-radius: 10; " +
                    "-fx-font-size: 14px; " +
                    "-fx-opacity: 1.0; " + // mantém o texto visível mesmo desabilitado
                    "-fx-background-color: white;" + // mesmo fundo que os outros campos
                    "-fx-text-fill: black;" // cor da fonte padrão
                );

        }

        Label iconeLabel = new Label(icone);
        iconeLabel.setStyle("-fx-font-size: 16px;");
        StackPane.setAlignment(iconeLabel, Pos.CENTER_RIGHT);
        StackPane.setMargin(iconeLabel, new Insets(0, 10, 0, 0));

        // Abre modal de alteração de senha
        if (labelTexto.equalsIgnoreCase("Senha")) {
            campo.setEditable(false); // impede digitação direta
            iconeLabel.setOnMouseClicked(e -> abrirModalSenha());
        }

        StackPane campoComIcone = new StackPane(campo, iconeLabel);
        return new VBox(5, label, campoComIcone);
    }
    
    private void abrirModalSenha() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Alterar senha");

        Label novaSenha = new Label("Nova senha:");
        PasswordField novaSenhaField = new PasswordField();
        VBox conteudo = new VBox(10, novaSenha, novaSenhaField);
        conteudo.setPadding(new Insets(10));

        dialog.getDialogPane().setContent(conteudo);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return novaSenhaField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(novaSenhaStr -> {
            System.out.println("Senha atualizada para: " + novaSenhaStr); // substitua isso por lógica real
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}