package com.assignment;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.view.Viewer;

import java.util.ArrayList;

public class HierarchyController extends ApplicationController {
    public void printHierarchy(ActionEvent event) {
        System.setProperty("org.graphstream.ui", "swing"); // Set to using Swing display
        Graph graph = new MultiGraph("Hierarchy of Wu Kingdom"); // Create a new graph

        int managementGeneralCounter = 1;
        int militaryGeneralCounter = 1;

        // Add three nodes to the graph first which is emperor and chiefs
        // Emperor
        graph.addNode("Sun Quan").setAttribute("xyz",0,5,0);
        graph.getNode("Sun Quan").setAttribute("ui.label", "Sun Quan");
        graph.getNode("Sun Quan").setAttribute("ui.style", "shape: circle; fill-color: red; size: 120px; text-alignment: center; " +
                "text-size: 20px;");
        graph.getNode("Sun Quan").setAttribute("layout.weight", "10");

        // Chief of Management
        graph.addNode("Zhang Zhao").setAttribute("xyz",-1,4,0);
        graph.getNode("Zhang Zhao").setAttribute("ui.label", "Zhang Zhao");
        graph.getNode("Zhang Zhao").setAttribute("ui.style", "shape:circle;fill-color:orange;size: 100px ;text-alignment: center; " +
                "text-size: 18px;");
        graph.getNode("Zhang Zhao").setAttribute("layout.weight", "10");

        // Chief of Military
        graph.addNode("Zhou Yu").setAttribute("xyz",1,4,0);
        graph.getNode("Zhou Yu").setAttribute("ui.label", "Zhou Yu");
        graph.getNode("Zhou Yu").setAttribute("ui.style", "shape:circle;fill-color:orange;size: 100px ;text-alignment: center; text-size:" +
                " 18px;");
        graph.getNode("Zhou Yu").setAttribute("layout.weight", "1");

        // Add edges to the nodes
        graph.addEdge("Emperor-Chief1", "Sun Quan", "Zhang Zhao", true);
        graph.addEdge("Emperor-Chief2", "Sun Quan", "Zhou Yu", true);

        // Create general list
        ArrayList<CharacterNode<String>> generalList = new ArrayList<>();
        generalList.add(new CharacterNode<>("Xu Sheng", "General","Archer", 78, 90, 72, 40, 94));
        generalList.add(new CharacterNode<>("Zhu Ge Jin", "General", "Archer", 61, 63, 88, 82, 71));
        generalList.add(new CharacterNode<>("Lu Su", "General", "Infantry",87, 43, 94, 88, 53));
        generalList.add(new CharacterNode<>("Tai Shi Ci", "General", "Cavalry", 91, 96, 43, 33, 97));
        generalList.add(new CharacterNode<>("Xiao Qiao", "General", "Infantry",52, 42, 89, 77, 34));
        generalList.add(new CharacterNode<>("Da Qiao", "General", "Cavalry", 62, 39, 90, 62, 41));
        generalList.add(new CharacterNode<>("Zhou Tai", "General", "Infantry", 89, 92, 72, 43, 99));
        generalList.add(new CharacterNode<>("Gan Ning", "General", "Archer",92 , 98, 45, 23, 97));
        generalList.add(new CharacterNode<>("Lu Meng", "General", "Cavalry", 77, 70, 93, 83, 88));
        generalList.add(new CharacterNode<>("Huang Gai", "General", "Infantry", 98, 83, 72, 42, 89));

        // For General Only
        for (CharacterNode<String> node : generalList) {
            // Add nodes and edges to the graph based on the department
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

        // Add sprite to the graph
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

        // Display the graph
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        viewer.getView(viewer.getDefaultID()).getCamera().getViewCenter();
        viewer.enableAutoLayout();
    }
}