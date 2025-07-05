package com.cidade.inteligente.cidade_inteligente.controller;

import com.cidade.inteligente.cidade_inteligente.Application;
import com.cidade.inteligente.cidade_inteligente.config.ConfiguracaoLuzes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class CidadeController implements Initializable {

    @FXML
    private Text text_alertas;

    @FXML
    private Text text_luzes_desligadas;

    @FXML
    private Text text_luzes_ligadas;

    @FXML
    private ImageView img_01;

    @FXML
    private Button btn_ligarLuzes;

    private Map<String, String> horarios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void init() {

        setOnAction();
        setImagensDefault();
        carregarInfos();
    }

    private void carregarInfos() {
        horarios = ConfiguracaoLuzes.carregarHorarios();

        horarios.forEach((chave, valor) -> {
            if (chave.equals(" luzes.ligadas")) {
                text_luzes_ligadas.setText(valor);
            } else if (chave.equals("luzes.desligadas")) {
                text_luzes_desligadas.setText(valor);
            } else if (chave.equals("luzes.alertas")) {
                text_alertas.setText(valor);
            }
        });
    }

    private void setImagensDefault() {
        Image imageOffLED = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/posteDesligado.png")));

        img_01.setFitHeight(55);
        img_01.setFitWidth(38);
        img_01.setImage(imageOffLED);
    }

    private void ligarLuz(String idLuz) {

        Image imageOnLED = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/posteLigado.png")));


        if (idLuz.equals(img_01.getId())) {

            img_01.setFitHeight(90);
            img_01.setFitWidth(38);
            img_01.setImage(imageOnLED);
//            System.out.println(img_01.getImage().getUrl());
        }
    }

    private void setOnAction() {
        btn_ligarLuzes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Application.abrirTela();

                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        img_01.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ligarLuz(img_01.getId());
//                if(img_01.getImage().getUrl())

            }
        });
    }
}
