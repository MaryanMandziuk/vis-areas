/*
 * Copyright 2011 TauNova (http://taunova.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test.app;


import com.taunova.event.Event;
import com.taunova.event.EventUtil;
import com.taunova.event.Listener;

import org.jdesktop.application.Action;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import net.taunova.app.SingleViewApplication;
import net.test.PreviewPanel;

/**
 *
 * @author Renat Gilmanov
 */
public class VisAreaApp extends SingleViewApplication {


    private Object tilesSubmitSubscriber;

    public VisAreaApp() {
        super();
        setMenuBarEnabled(true);
        setToolBarEnabled(true);
        setStatusBarEnabled(false);
    }

    @Override
    protected void initialize(String[] args) {
        // check resources
        Runtime.getRuntime().addShutdownHook(new Thread(
                new Runnable() {

                    public void run() {
                        // empty shutdown hook
                    }
                }));
    }

    protected JComponent buildContent() {
        JPanel content = new JPanel(new BorderLayout());      


        PreviewPanel pane = new PreviewPanel();


        content.add(BorderLayout.CENTER, pane);
        content.add(BorderLayout.EAST, new ToolsPane());

        subscribeEvents();

        return content;
    }

    protected void subscribeEvents() {
//        tilesSubmitSubscriber = EventUtil.subscribe(TilesSubmitEvent.class, new Listener() {
//
//            @Override
//            public void onEvent(Event event) {
//                processor.clearTiles();
//                for(PreviewPane pane : panes) {
//                    pane.submitTiles(processor);
//                }
//
//                processor.process();
//            }
//        });
    }

    // --- Actions ------------------------------------------------------
    @Action
    public void newItem() {
        System.out.println("New item called");
    }
    
    // --- Entry point --------------------------------------------------
    public static void main(String[] args) {
        launch(VisAreaApp.class, args);
    }
}
