module com.cidade.inteligente.cidade_inteligente {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.incubator.foreign;
    requires java.xml;

    opens com.cidade.inteligente.cidade_inteligente to javafx.fxml;

    exports com.cidade.inteligente.cidade_inteligente;
    exports com.cidade.inteligente.cidade_inteligente.controller;

    opens com.cidade.inteligente.cidade_inteligente.controller to javafx.fxml;

    exports com.cidade.inteligente.cidade_inteligente.model;

    opens com.cidade.inteligente.cidade_inteligente.model to javafx.fxml;

    exports com.cidade.inteligente.cidade_inteligente.factory;

    opens com.cidade.inteligente.cidade_inteligente.factory to javafx.fxml;

    exports com.cidade.inteligente.cidade_inteligente.observer;

    opens com.cidade.inteligente.cidade_inteligente.observer to javafx.fxml;

    exports com.cidade.inteligente.cidade_inteligente.strategy;

    opens com.cidade.inteligente.cidade_inteligente.strategy to javafx.fxml;

    exports com.cidade.inteligente.cidade_inteligente.decorator;

    opens com.cidade.inteligente.cidade_inteligente.decorator to javafx.fxml;

}