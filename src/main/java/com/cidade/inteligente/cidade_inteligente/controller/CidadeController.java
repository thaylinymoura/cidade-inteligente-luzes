package com.cidade.inteligente.cidade_inteligente.controller;

import com.cidade.inteligente.cidade_inteligente.HelloApplication;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void init() {

        setOnAction();
        setImagensDefault();

        text_luzes_ligadas.setText("0");
        text_luzes_desligadas.setText("10.555");
        text_alertas.setText("0");
    }

    private void setImagensDefault() {
        Image imageOffLED = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Poste_de_Luz-removebg-preview.png")));

        img_01.setFitHeight(55);
        img_01.setFitWidth(38);
        img_01.setImage(imageOffLED);
    }

    private void ligarLuz(String idLuz) {

        Image imageOnLED = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Luz_Post_Laranja-removebg-preview.png")));


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
                    HelloApplication.abrirTela();

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
