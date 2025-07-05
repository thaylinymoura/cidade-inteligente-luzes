package com.cidade.inteligente.cidade_inteligente.controller;

import com.cidade.inteligente.cidade_inteligente.config.ConfiguracaoLuzes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button; // Manter se você adicionar outros botões de controle
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BairroController implements Initializable {

    @FXML
    private Button botaoLigaDesliga;

    @FXML
    private Button botaoReiniciar;

    @FXML
    private GridPane gridBairro;

    @FXML
    private ImageView posteImage00;

    @FXML
    private ImageView posteImage01;

    @FXML
    private ImageView posteImage02;

    @FXML
    private Text textHorarioDesligar;

    @FXML
    private Text textHorarioLigar;

    @FXML
    private Text textLuminosidade;

    @FXML
    private Text textHoraAtual;

    private Map<String, String>  horarios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void init() {
        desligarLuz(3);
        carregarInfos();
        simularHoras();
        actions();
    }

    private void carregarInfos() {
       horarios = ConfiguracaoLuzes.carregarHorarios();

        horarios.forEach((chave, valor) -> {
            if (chave.equals("luz.poste.luminosidade")) {
                textLuminosidade.setText("Luminosidade : " + valor);

                if (valor.equals("400.0")) {
                    ligarLuz(0);
                }

            } else if (chave.equals("luz.poste.ligar")) {
                textHorarioLigar.setText("Ligar: " + valor);
            } else if (chave.equals("luz.poste.desligar")) {
                textHorarioDesligar.setText("Desligar: " + valor);
            }
        });
    }

    private void ligarLuz(int poste) {
        Image imageOnLED = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/posteLigado.png")));

        switch (poste) {
            case 0:
                posteImage00.setImage(imageOnLED);
                break;
            case 1:
                posteImage01.setImage(imageOnLED);
                break;
            case 2 :
                posteImage02.setImage(imageOnLED);
                break;
            case 3 :
                posteImage00.setImage(imageOnLED);
                posteImage01.setImage(imageOnLED);
                posteImage02.setImage(imageOnLED);
                break;
        }
    }

    private void desligarLuz(int poste) {

        Image imageOffLED = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/posteDesligado.png")));

        switch (poste) {
            case 0:
                posteImage00.setImage(imageOffLED);
                break;
            case 1:
                posteImage01.setImage(imageOffLED);
                break;
            case 2 :
                posteImage02.setImage(imageOffLED);
                break;
            case 3 :
                posteImage00.setImage(imageOffLED);
                posteImage01.setImage(imageOffLED);
                posteImage02.setImage(imageOffLED);
                break;
        }
    }

    private void simularHoras() {
        LocalTime[] horaAtual = {LocalTime.of(16, 0)};
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        String horaFormatada = horaAtual[0].format(FORMATTER);

                        textHoraAtual.setText("Hora atual: " +  horaFormatada);

                        if ("Ligar: ".concat(horaFormatada).equals(textHorarioLigar.getText())) {
                            ligarLuz(1);
                        } else if ("Desligar: ".concat(horaFormatada).equals(textHorarioDesligar.getText())) {
                            desligarLuz(1);
                        }

                        Thread.sleep(3_000);

                        // Incrementa 1 hora
                        horaAtual[0] = horaAtual[0].plusHours(1);
                        ligarLuz(0);

                    } catch (InterruptedException e) {
                        System.out.println("Simulação interrompida.");
                        break;
                    }
                }
            });

            thread.setDaemon(true);
            thread.start();
    }

    private void actions() {

        botaoLigaDesliga.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (botaoLigaDesliga.getText().equals("Ligar")) {
                    botaoLigaDesliga.setText("Desligar");
                    ligarLuz(2);
                } else {
                    botaoLigaDesliga.setText("Ligar");
                    desligarLuz(2);
                }
            }
        });

        botaoReiniciar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                desligarLuz(3);

            }
        });
    }
}
