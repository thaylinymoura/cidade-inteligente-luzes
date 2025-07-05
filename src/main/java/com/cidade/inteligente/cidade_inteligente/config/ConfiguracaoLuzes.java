package com.cidade.inteligente.cidade_inteligente.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

public class ConfiguracaoLuzes {

    public static Map<String, String> carregarHorarios() {
        Map<String, String> horarios = new HashMap<>();

        try (InputStream input = ConfiguracaoLuzes.class.getResourceAsStream("/luzes.properties")) {

            if (input == null) {
                System.out.println("Arquivo luzes.properties não encontrado!");
                return horarios;
            }

            Properties props = new Properties();
            props.load(input);

            for (String chave : props.stringPropertyNames()) {
                String valor = props.getProperty(chave);
                horarios.put(chave, valor);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return horarios;
    }
}
