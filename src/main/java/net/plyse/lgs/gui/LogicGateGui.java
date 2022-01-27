/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Raphael Dichler on 27.01.2022.
 */
public class LogicGateGui extends JFrame{

    private JPanel parent;
    private JMenuBar menuBar;
    private JMenu menuBarFile;
    private JTree elementTree;

    public LogicGateGui(String title){
        super(title);

        setContentPane(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setVisible(true);
    }

    public static void runApplication(String[] args) {
        new LogicGateGui("LGS");
    }

    private void createUIComponents() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        treeModel.setAsksAllowsChildren(true);
        elementTree = new JTree(treeModel);
    }
}
