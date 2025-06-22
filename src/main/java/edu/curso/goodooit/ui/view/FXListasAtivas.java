package edu.curso.goodooit.ui.view;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXListasAtivas extends Application {

    private boolean menuVisivel = false;
    private Button botaoMenu;
    private Rectangle fundoEscurecido;
    private VBox menuLateralRef;

    @Override
    public void start(Stage stage) {
        double larguraTela = Screen.getPrimary().getBounds().getWidth();
        double alturaTela = Screen.getPrimary().getBounds().getHeight();

        StackPane root = new StackPane();

        VBox conteudo = criarConteudoPrincipal();
        ScrollPane scrollPane = new ScrollPane(conteudo);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        menuLateralRef = criarMenuLateral();
        menuLateralRef.setPrefWidth(250);
        menuLateralRef.setTranslateX(-260);
        StackPane.setAlignment(menuLateralRef, Pos.TOP_LEFT);

        fundoEscurecido = new Rectangle(larguraTela, alturaTela);
        fundoEscurecido.setFill(new Color(0, 0, 0, 0.5));
        fundoEscurecido.setVisible(false);
        fundoEscurecido.setOnMouseClicked(e -> alternarMenu(menuLateralRef));
        StackPane.setAlignment(fundoEscurecido, Pos.CENTER);

        VBox layoutPrincipal = new VBox(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        root.getChildren().addAll(layoutPrincipal, fundoEscurecido, menuLateralRef);

        Scene scene = new Scene(root, larguraTela, alturaTela * 0.9);
        stage.setScene(scene);
        stage.setTitle("Projeto Galo Eletrônico");
        stage.show();
    }

    private VBox criarConteudoPrincipal() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-background-color: #b39ddb; -fx-font-family: monospace;");

        // Header
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);

        botaoMenu = new Button("≡");
        botaoMenu.setStyle("-fx-font-size: 18px; -fx-background-color: transparent;");
        botaoMenu.setOnAction(e -> alternarMenu(menuLateralRef));

        Label title = new Label("Projeto galo eletrônico");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label status = new Label("Em andamento");
        status.setStyle("-fx-background-color: #dadada; -fx-padding: 5 10 5 10; -fx-background-radius: 8; -fx-font-size: 12px;");

        header.getChildren().addAll(botaoMenu, title, spacer, status);

        // Descrição
        Label descricao = new Label("Projeto inicial para confecção dos modelos do Galotron3000");
        descricao.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-background-radius: 8; -fx-text-fill: black; -fx-font-size: 13px;");
        descricao.setMaxWidth(Double.MAX_VALUE);

        // Painel de listas no projeto
        VBox painelListas = new VBox();
        painelListas.setAlignment(Pos.CENTER);
        painelListas.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        painelListas.setPadding(new Insets(15));

        Label titulo = new Label("Listas no projeto");
        titulo.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-font-weight: normal;");

        Label quantidade = new Label("3");
        quantidade.setStyle("-fx-text-fill: black; -fx-font-size: 26px; -fx-font-weight: bold;");

        Label rodape = new Label("Listas");
        rodape.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-font-weight: normal;");

        painelListas.getChildren().addAll(titulo, quantidade, rodape);

        // Botão Criar nova tarefa
        Button criarTarefa = new Button("Criar nova tarefa");
        criarTarefa.setMaxWidth(Double.MAX_VALUE);
        criarTarefa.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-font-weight: bold; -fx-font-size: 13px;");
        VBox.setMargin(criarTarefa, new Insets(0, 0, 0, 0));

        // Listas com tarefas
        VBox lista1 = criarLista("Lista de olho laser");
        VBox lista2 = criarLista("Lista de bico cibernético");

        Button moverTarefa = new Button("Voltar");
        estiloBotaoRoxo(moverTarefa);
        
        mainContent.getChildren().addAll(header, descricao, painelListas, criarTarefa, lista1, lista2, moverTarefa);
        return mainContent;
    }

    private VBox criarLista(String nome) {
        VBox container = new VBox(10);
        container.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        container.setPadding(new Insets(15));

        Label nomeLista = new Label(nome);
        nomeLista.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: black;");

        VBox tarefas = new VBox(10);
        for (int i = 0; i < 3; i++) {
            tarefas.getChildren().add(criarTarefa("Documentar criação de dispositivos", "Alta", "24/06/2025"));
        }

        container.getChildren().addAll(nomeLista, tarefas);
        return container;
    }
    
    private VBox criarTarefa(String nome, String prioridade, String prazo) {
        VBox tarefaBox = new VBox(5);
        tarefaBox.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 10;");
        tarefaBox.setPadding(new Insets(10));

        Label nomeTarefa = new Label(nome);
        nomeTarefa.setStyle("-fx-font-size: 13px; -fx-text-fill: black;");

        Label status = new Label("Status: aguardando dispositivo");
        status.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");

        Label prioridadeLbl = new Label("Prioridade: " + prioridade);
        prioridadeLbl.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");

        Label prazoLbl = new Label("Prazo de conclusão: " + prazo);
        prazoLbl.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");

        HBox linhaVisualizacao = new HBox();
        linhaVisualizacao.setAlignment(Pos.CENTER_RIGHT);
        Label iconeOlho = new Label("👁");
        iconeOlho.setStyle("-fx-font-size: 14px;");
        linhaVisualizacao.getChildren().add(iconeOlho);

        tarefaBox.getChildren().addAll(nomeTarefa, status, prioridadeLbl, prazoLbl, linhaVisualizacao);
        return tarefaBox;
    }


    private VBox criarMenuLateral() {
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(15));
        menu.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-min-width: 250px; -fx-max-width: 250px;");
        menu.setAlignment(Pos.TOP_CENTER);

        ImageView avatar = new ImageView(new Image("/images/Goo.png"));
        avatar.setFitHeight(80);
        avatar.setFitWidth(80);

        Label nome = new Label("Julia Fernandes");
        nome.setStyle("-fx-font-family: monospace; -fx-font-size: 16px;");

        Button fecharMenu = new Button("Fechar menu");
        fecharMenu.setMaxWidth(Double.MAX_VALUE);
        fecharMenu.setStyle("-fx-background-color: #ffaaaa; -fx-font-family: monospace;");
        fecharMenu.setOnAction(e -> alternarMenu(menu));

        menu.getChildren().addAll(avatar, nome,
                botaoMenuLateral("Projetos", "#d763f7"),
                botaoMenuLateral("Tarefas", "#c7c7c7"),
                botaoMenuLateral("Sair", "#d763f7"),
                fecharMenu
        );
        return menu;
    }

    private Button botaoMenuLateral(String texto, String cor) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: " + cor + "; -fx-font-family: monospace; -fx-font-size: 14px; -fx-background-radius: 10px;");
        return btn;
    }

    private void alternarMenu(VBox menuLateral) {
        TranslateTransition slide = new TranslateTransition(Duration.millis(300), menuLateral);
        FadeTransition fade = new FadeTransition(Duration.millis(300), fundoEscurecido);

        if (!menuVisivel) {
            slide.setToX(0);
            fade.setFromValue(0);
            fade.setToValue(1);
            fundoEscurecido.setVisible(true);
            botaoMenu.setText("X");
        } else {
            slide.setToX(-260);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setOnFinished(e -> fundoEscurecido.setVisible(false));
            botaoMenu.setText("≡");
        }

        slide.play();
        fade.play();
        menuVisivel = !menuVisivel;
    }

    private void estiloBotaoRoxo(Button btn) {
        btn.setStyle("-fx-background-color: #8744c2; -fx-text-fill: white; -fx-background-radius: 10px; -fx-font-family: monospace;");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}