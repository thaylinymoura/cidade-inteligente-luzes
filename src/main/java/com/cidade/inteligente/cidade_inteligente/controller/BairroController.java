package com.cidade.inteligente.cidade_inteligente.controller;

import com.cidade.inteligente.cidade_inteligente.config.ConfiguracaoCSVReader;
import com.cidade.inteligente.cidade_inteligente.decorator.LampadaComContador;
import com.cidade.inteligente.cidade_inteligente.decorator.LampadaComDimmer;
import com.cidade.inteligente.cidade_inteligente.decorator.LampadaDecorator; // Necessário para o método isPosteVaporSodio
import com.cidade.inteligente.cidade_inteligente.factory.*;
import com.cidade.inteligente.cidade_inteligente.model.PosteDeLuz;
import com.cidade.inteligente.cidade_inteligente.observer.CentroDeMonitoramento;
import com.cidade.inteligente.cidade_inteligente.observer.PainelDeControle;
import com.cidade.inteligente.cidade_inteligente.strategy.ComportamentoLigamento;
import com.cidade.inteligente.cidade_inteligente.strategy.EstrategiaManual;
import com.cidade.inteligente.cidade_inteligente.strategy.EstrategiaPorHorario;
import com.cidade.inteligente.cidade_inteligente.strategy.EstrategiaPorLuminosidade;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button; // Manter se você adicionar outros botões de controle
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

public class BairroController implements Initializable {
    @FXML
    private GridPane gridBairro; // O GridPane para o layout do bairro

    private Map<String, PosteDeLuz> postesDoBairro;
    private Map<String, ImageView> imagensPostesUI;

    // Botao para Luz Manual
    private Map<String, Button> botoesControleManual;

    // Imagens para os estados ligado/desligado de postes LED
    private Image imageOffLED;
    private Image imageOnLED;

    // Imagens para os estados ligado/desligado de postes de Vapor de Sódio
    private Image imageOffVaporSodio;
    private Image imageOnVaporSodio;

    private Map<String, String> configuracoesGlobais, configuracoesPostes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.configuracoesGlobais = ConfiguracaoCSVReader.lerConfiguracoes("ConfigLimiar.csv");
        this.configuracoesPostes = ConfiguracaoCSVReader.lerConfiguracoes("ConfigPostes.csv");

        postesDoBairro = new HashMap<>();
        imagensPostesUI = new HashMap<>();
        this.botoesControleManual = new HashMap<>(); // Inicialização!

        // Carregar as imagens
        imageOffLED = new Image(getClass().getResourceAsStream("/images/Poste_de_Luz-removebg-preview.png"));
        imageOnLED = new Image(getClass()
                .getResourceAsStream("/images/ChatGPT_Image_18_de_jun._de_2025__22_37_42-removebg-preview.png"));

        imageOffVaporSodio = imageOffLED; // Usando a mesma imagem de poste desligado para vapor de sódio
        imageOnVaporSodio = new Image(getClass().getResourceAsStream("/images/Luz_Post_Laranja-removebg-preview.png"));

        gridBairro.getChildren().clear();

        // IDs dos postes para uma grade 3x3
        int maxRow = 3;
        int maxCol = 3;

        // String[] idsPostes = {
        // "P-R1C1", "P-R1C2", "P-R1C3",
        // "P-R2C1", "P-R2C2", "P-R2C3",
        // "P-R3C1", "P-R3C2", "P-R3C3"
        // };
        int posteCounter = 0;
        Random rand = new Random();

        int pointerPoste = 0;
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                // List<String> posteIDs = new ArrayList<>(configuracoesPostes.keySet());
                // String[] posteKeys = configuracoesPostes.keySet().toArray(new String[0]);
                String[] posteIDs = configuracoesPostes.keySet().stream()
                        .filter(key -> !key.equals("id_poste"))
                        .toArray(String[]::new);

                String posteId = posteIDs[pointerPoste];
                pointerPoste++;
                String posteTipo = configuracoesPostes.get(posteId);

                FabricaPoste fabrica;
                ComportamentoLigamento estrategiaInicial;
                boolean isVaporSodioPoste = false;

                // Lógica para alternar tipos de postes e estratégias
                // // 0: LED Hora, 1: VaporSodio Manual, 2: LED

                // String tipoPosteRandom = posteTipo;

                if (posteTipo.equals("0")) {
                    fabrica = new FabricaPosteLED();
                    estrategiaInicial = new EstrategiaPorHorario(18, 6);
                } else if (posteTipo.equals("1")) {
                    fabrica = new FabricaPosteVaporSodio();
                    estrategiaInicial = new EstrategiaManual();
                    isVaporSodioPoste = true;
                } else {
                    String limiarLuminosidadeStr = configuracoesGlobais.get("limiar_luminosidade");
                    double limiarLuminosidade = 0.0;

                    if (limiarLuminosidadeStr != null) {
                        try {
                            limiarLuminosidade = Double.parseDouble(limiarLuminosidadeStr);
                        } catch (NumberFormatException e) {
                            System.err.println(
                                    "Erro ao converter 'limiar_luminosidade' para double. Usando valor padrão.");
                            limiarLuminosidade = 150.0;
                        }
                    } else {
                        System.err.println(
                                "Chave 'limiar_luminosidade' não encontrada no arquivo de configuração. Usando valor padrão.");
                        limiarLuminosidade = 150.0;
                    }

                    fabrica = new FabricaPosteLED(); // FabricaLED para postes inteligentes
                    estrategiaInicial = new EstrategiaPorLuminosidade(limiarLuminosidade);
                }

                // Cria componentes base
                Lampada lampadaBase = fabrica.criarLampada();
                Sensor sensorDoPoste = fabrica.criarSensor();
                ModuloComunicacao moduloDoPoste = fabrica.criarModuloComunicacao();

                // Decora a lâmpada
                Lampada lampadaDecorada = lampadaBase;
                if (rand.nextBoolean()) {
                    lampadaDecorada = new LampadaComDimmer(lampadaDecorada);
                }
                if (rand.nextBoolean()) {
                    lampadaDecorada = new LampadaComContador(lampadaDecorada);
                }

                // Cria o PosteDeLuz, ajustando o sensor se a estratégia for por luminosidade e
                // o sensor original não for Luminosidade
                PosteDeLuz novoPoste;
                if (estrategiaInicial instanceof EstrategiaPorLuminosidade
                        && !(sensorDoPoste instanceof SensorLuminosidade)) {
                    novoPoste = new PosteDeLuz(posteId, lampadaDecorada, new SensorLuminosidade(), moduloDoPoste,
                            estrategiaInicial);
                } else {
                    novoPoste = new PosteDeLuz(posteId, lampadaDecorada, sensorDoPoste, moduloDoPoste,
                            estrategiaInicial);
                }

                postesDoBairro.put(posteId, novoPoste);

                // NOVO: Crie um VBox para agrupar os elementos do poste na UI
                VBox posteUIGroup = new VBox(5); // Espaçamento de 5px entre os elementos
                posteUIGroup.setAlignment(Pos.CENTER); // Alinha os elementos no centro do VBox

                // Seleciona a imagem inicial com base no tipo de poste
                ImageView posteImageView;
                if (isVaporSodioPoste) {
                    posteImageView = new ImageView(imageOffVaporSodio);
                } else {
                    posteImageView = new ImageView(imageOffLED);
                }

                posteImageView.setFitWidth(80);
                posteImageView.setFitHeight(80);
                gridBairro.add(posteImageView, col, row);
                imagensPostesUI.put(posteId, posteImageView);

                // Adicionar observadores
                PainelDeControle painel = new PainelDeControle();
                CentroDeMonitoramento centroMonitoramento = new CentroDeMonitoramento();
                novoPoste.adicionarObservador(painel);
                novoPoste.adicionarObservador(centroMonitoramento);

                // Label de informação do poste
                Label infoLabel = new Label(
                        posteId + "\n(" + estrategiaInicial.getClass().getSimpleName().replace("Estrategia", "") + ")");
                infoLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #333; -fx-alignment: center;");
                GridPane.setHalignment(infoLabel, HPos.CENTER);
                GridPane.setValignment(infoLabel, VPos.BOTTOM);
                gridBairro.add(infoLabel, col, row);
                posteUIGroup.getChildren().add(infoLabel); // Adiciona o label ao VBox

                if (estrategiaInicial instanceof EstrategiaManual) {
                    Button controleButton = new Button(novoPoste.isLigado() ? "Desligar" : "Ligar");
                    controleButton.setPrefWidth(80); // Ajusta a largura do botão

                    // Armazena o botão para atualização posterior
                    botoesControleManual.put(posteId, controleButton);

                    controleButton.setOnAction(event -> {
                        if (novoPoste.isLigado()) {
                            novoPoste.desligar();
                        } else {
                            novoPoste.ligar();
                        }
                        // O texto do botão será atualizado no AnimationTimer via o Observer
                    });

                    posteUIGroup.getChildren().add(controleButton); // Adiciona o botão ao VBox
                } else {
                    // Para postes automáticos, pode-se adicionar um Label indicando "Automático" ou
                    // similar
                    Label autoLabel = new Label(
                            "Auto (" + estrategiaInicial.getClass().getSimpleName().replace("Estrategia", "") + ")");
                    autoLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #666; -fx-alignment: center;");
                    posteUIGroup.getChildren().add(autoLabel);
                }

                // Adiciona o VBox completo (com imagem, label e talvez botão) ao GridPane
                gridBairro.add(posteUIGroup, col, row);

                // Evento de clique para controle manual ou mudança de estratégia
                posteImageView.setOnMouseClicked(event -> {
                    if (novoPoste.getComportamento() instanceof EstrategiaManual) {
                        if (novoPoste.isLigado()) {
                            novoPoste.desligar();
                        } else {
                            novoPoste.ligar();
                        }
                    } else {
                        System.out.println("Poste " + novoPoste.getId() + " está em modo automático ("
                                + novoPoste.getComportamento().getClass().getSimpleName() + "). Mudar para manual?");
                        novoPoste.setComportamentoLigamento(new EstrategiaManual());
                        System.out.println("Estratégia do Poste " + novoPoste.getId()
                                + " mudou para Manual. Clique novamente na imagem para ligar/desligar.");
                    }
                });
            }
        }

        // AnimationTimer para simulação de tempo
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000L) {
                    System.out.println("Hora simulada: " + LocalTime.now());

                    for (PosteDeLuz poste : postesDoBairro.values()) {
                        poste.executarComportamento();

                        ImageView currentImageView = imagensPostesUI.get(poste.getId());
                        if (currentImageView != null) {
                            Image imageToUseOn = isPosteVaporSodio(poste) ? imageOnVaporSodio : imageOnLED;
                            Image imageToUseOff = isPosteVaporSodio(poste) ? imageOffVaporSodio : imageOffLED;

                            if (poste.isLigado() && currentImageView.getImage() != imageToUseOn) {
                                currentImageView.setImage(imageToUseOn);
                            } else if (!poste.isLigado() && currentImageView.getImage() != imageToUseOff) {
                                currentImageView.setImage(imageToUseOff);
                            }
                        }

                        // NOVO: Atualizar o texto do botão de controle manual, se o poste tiver um
                        Button controleButton = botoesControleManual.get(poste.getId());
                        if (controleButton != null) {
                            if (poste.isLigado()) {
                                controleButton.setText("Desligar");
                            } else {
                                controleButton.setText("Ligar");
                            }
                        }
                    }
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    // Método auxiliar para verificar se o poste tem uma lâmpada de Vapor de Sódio
    private boolean isPosteVaporSodio(PosteDeLuz poste) {
        Lampada baseLampada = poste.getLampada();
        // Percorre os decorators para encontrar a lâmpada base
        while (baseLampada instanceof LampadaDecorator) {
            baseLampada = ((LampadaDecorator) baseLampada).getLampadaDecorada();
        }
        return baseLampada instanceof LampadaVaporSodio;
    }

    @FXML
    private void handleReiniciarSimulacao(ActionEvent event) {
        System.out.println("Simulação Reiniciada!");
        postesDoBairro.clear();
        imagensPostesUI.clear();
        gridBairro.getChildren().clear();
        initialize(null, null); // Reinicializa o controlador e recria tudo
    }

}
