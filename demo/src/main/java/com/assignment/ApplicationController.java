package com.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.view.Viewer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ApplicationController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToHierarchyScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hierarchyScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSoldierArrangement(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("soldierArrangement.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBorrowingArrow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("borrowingArrow.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEnemyFortress(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("enemyFortress.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFood(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("foodHarvesting.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTextConverter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("textConverter.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRedCliff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("redCliff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEngaging(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("engagingCaoCao.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void printHierarchy(ActionEvent event) throws IOException {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new MultiGraph("Hierarchy of Wu Kingdom");

        int managementGeneralCounter = 1;
        int militaryGeneralCounter = 1;

        graph.addNode("Sun Quan").setAttribute("xyz",0,5,0);
        graph.getNode("Sun Quan").setAttribute("ui.label", "Sun Quan");
        graph.getNode("Sun Quan").setAttribute("ui.style", "shape: circle; fill-color: red; size: 120px; text-alignment: center; " +
                "text-size: 20px;");
        graph.getNode("Sun Quan").setAttribute("layout.weight", "10");

        graph.addNode("Zhang Zhao").setAttribute("xyz",-1,4,0);
        graph.getNode("Zhang Zhao").setAttribute("ui.label", "Zhang Zhao");
        graph.getNode("Zhang Zhao").setAttribute("ui.style", "shape:circle;fill-color:orange;size: 100px ;text-alignment: center; " +
                "text-size: 18px;");
        graph.getNode("Zhang Zhao").setAttribute("layout.weight", "10");


        graph.addNode("Zhou Yu").setAttribute("xyz",1,4,0);
        graph.getNode("Zhou Yu").setAttribute("ui.label", "Zhou Yu");
        graph.getNode("Zhou Yu").setAttribute("ui.style", "shape:circle;fill-color:orange;size: 100px ;text-alignment: center; text-size:" +
                " 18px;");
        graph.getNode("Zhou Yu").setAttribute("layout.weight", "1");
        graph.addEdge("Emperor-Chief1", "Sun Quan", "Zhang Zhao", true);
        graph.addEdge("Emperor-Chief2", "Sun Quan", "Zhou Yu", true);
        DataOfKingdom dataOfKingdom = new DataOfKingdom();
        ArrayList<CharacterNode<String>> generalList = dataOfKingdom.getGeneralList();
        // For General Only
        for (CharacterNode<String> node : generalList) {
            if(node.department != null && node.department.equals("Management")) {
                graph.addNode(node.name);
                graph.getNode(node.name).setAttribute("ui.label", node.name);
                graph.getNode(node.name).setAttribute("ui.style", "shape:circle;fill-color:yellow;size: 100px, 30px; text-alignment: " +
                        "center; text-size: 16px;");
                graph.getNode(node.name).setAttribute("layout.weight", "0.5");
                graph.addEdge("ManagementChief-General" + managementGeneralCounter++, "Zhang Zhao", node.name, true);
            } else if(node.department != null && node.department.equals("Military")){
                graph.addNode(node.name);
                graph.getNode(node.name).setAttribute("ui.label", node.name);
                graph.getNode(node.name).setAttribute("ui.style", "shape:circle;fill-color:yellow;size: 100px, 30px; text-alignment: " +
                        "center; text-size: 16px;");
                graph.addEdge("MilitaryChief-General" + militaryGeneralCounter++,"Zhou Yu", node.name, true);
            } else {
                System.out.println("error");
            }
        }

        SpriteManager sman = new SpriteManager(graph);
        Sprite s1 = sman.addSprite("S1");
        s1.setPosition(0.5);
        s1.setAttribute("ui.style", "shape:circle;fill-mode:none;size: 50px ; text-alignment: above; text-size: 16px;");
        s1.setAttribute("ui.label", "Management");
        s1.attachToEdge("Emperor-Chief1");

        Sprite s2 = sman.addSprite("S2");
        s2.setPosition(0.5);
        s2.setAttribute("ui.style", "shape:circle;fill-mode:none;size: 50px ; text-alignment: above; text-size: 16px;");
        s2.setAttribute("ui.label", "Military");
        s2.attachToEdge("Emperor-Chief2");

        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        viewer.getView(viewer.getDefaultID()).getCamera().getViewCenter();
        viewer.enableAutoLayout();
    }

    void printError(StringBuilder errors) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Warning");
        alert.setContentText(errors.toString());

        alert.showAndWait();
    }
}