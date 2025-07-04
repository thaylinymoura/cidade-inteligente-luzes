package com.cidade.inteligente.cidade_inteligente.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream; // Importe esta classe
import java.io.InputStreamReader; // Importe esta classe
import java.util.HashMap;
import java.util.Map;

public class ConfiguracaoCSVReader {
    // Não precisa mais do parâmetro caminhoArquivo, pois o ClassLoader encontra
    public static Map<String, String> lerConfiguracoes(String path) {
        Map<String, String> configuracoes = new HashMap<>();
        String linha;
        String separadorCSV = ",";

        // Aqui é onde você usa a linha sugerida!
        try (InputStream is = ConfiguracaoCSVReader.class.getClassLoader().getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) { // Use InputStreamReader aqui

            if (is == null) { // Adicione uma verificação caso o arquivo não seja encontrado
                System.err.println("Arquivo não encontrado nos recursos do projeto.");
                return configuracoes; // Retorna mapa vazio ou lança exceção
            }

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separadorCSV);
                if (dados.length == 2) {
                    configuracoes.put(dados[0].trim(), dados[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de configuração: " + e.getMessage());
        } catch (NullPointerException e) { // Adicionado para caso o InputStream seja nulo e não tratado acima
            System.err.println("Erro: InputStream é nulo. O arquivo pode não ter sido encontrado.");
        }
        return configuracoes;
    }
}