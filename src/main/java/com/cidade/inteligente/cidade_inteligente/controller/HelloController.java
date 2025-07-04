package com.cidade.inteligente.cidade_inteligente.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.cidade.inteligente.cidade_inteligente.HelloApplication;

public class HelloController extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Altera para carregar o novo FXML do bairro
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("bairro-view.fxml"));
        // Define um tamanho maior para a janela para melhor visualização do bairro
        Scene scene = new Scene(fxmlLoader.load(), 700, 600); // Ajuste o tamanho conforme necessário
        stage.setTitle("Cidade Inteligente - Simulação de Bairro"); // Novo título
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
