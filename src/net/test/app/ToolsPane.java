/*
 * Copyright 2011 TauNova (http://taunova.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test.app;

import com.taunova.event.EventUtil;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.test.PreviewPanel;
import net.test.app.event.ColorThresholdEvent;
import net.test.app.event.LineThresholdEvent;
import net.test.app.event.ScanStepEvent;
import net.test.app.event.ZoomFactorEvent;

/**
 *
 * @author Renat Gilmanov
 */
public class ToolsPane extends JPanel {

    private static final int WIDTH = 400;
    private GroupLayout layout;

    public ToolsPane() {
        super(true);
        layout = new GroupLayout(this);
        setLayout(layout);
        setPreferredSize(new Dimension(WIDTH, 0));

        layout.setAutoCreateContainerGaps(true);

        JLabel mainLabel = new JLabel("Main");
        JLabel zoomLabel = new JLabel("Zoom");
        JLabel scanStepLabel = new JLabel("Scan step");
        JLabel verticalLabel = new JLabel("Line threshold");
        JLabel leftLabel = new JLabel("Color threshold");

        JButton loadButton = new JButton("Open");

        loadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // select new image and let preview panel refresh
            }
        });

        // --- Color Panel ----------------------------------------------------
        JSlider zoomThreshold = new JSlider(JSlider.HORIZONTAL,
                1,
                10,
                PreviewPanel.ZOOM);

        zoomThreshold.setPaintTicks(true);
        zoomThreshold.setMajorTickSpacing(1);
        zoomThreshold.setMinorTickSpacing(10);
        zoomThreshold.setPaintLabels(true);

        zoomThreshold.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s1 = (JSlider) e.getSource();
                if (!s1.getValueIsAdjusting()) {
                    EventUtil.publish(new ZoomFactorEvent(s1.getValue()));
                }
            }
        });

        // --- Color Panel ----------------------------------------------------
        JSlider scanStepThreshold = new JSlider(JSlider.HORIZONTAL,
                1,
                10,
                PreviewPanel.SCAN_STEP);

        scanStepThreshold.setPaintTicks(true);
        scanStepThreshold.setMajorTickSpacing(1);
        scanStepThreshold.setMinorTickSpacing(10);
        scanStepThreshold.setPaintLabels(true);

        scanStepThreshold.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s1 = (JSlider) e.getSource();
                if (!s1.getValueIsAdjusting()) {
                    EventUtil.publish(new ScanStepEvent(s1.getValue()));
                }
            }
        });

        // --- Color Panel ----------------------------------------------------
        JSlider lineThreshold = new JSlider(JSlider.HORIZONTAL,
                1,
                20,
                PreviewPanel.LINE_THRESHOLD);

        lineThreshold.setPaintTicks(true);
        lineThreshold.setMajorTickSpacing(10);
        lineThreshold.setMinorTickSpacing(1);
        lineThreshold.setPaintLabels(true);

        lineThreshold.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s1 = (JSlider) e.getSource();
                if (!s1.getValueIsAdjusting()) {
                    EventUtil.publish(new LineThresholdEvent(s1.getValue()));
                }
            }
        });

        // --- Left comp ----------------------------------------------------
        JSlider colorThreshold = new JSlider(JSlider.HORIZONTAL,
                1,
                200,
                PreviewPanel.COLOR_THRESHOLD);

        colorThreshold.setPaintTicks(true);
        colorThreshold.setMajorTickSpacing(50);
        colorThreshold.setMinorTickSpacing(10);
        colorThreshold.setPaintLabels(true);

        colorThreshold.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s1 = (JSlider) e.getSource();
                if (!s1.getValueIsAdjusting()) {
                    EventUtil.publish(new ColorThresholdEvent(s1.getValue()));
                }
            }
        });

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainLabel)
                        .addComponent(zoomLabel)
                        .addComponent(scanStepLabel)
                        .addComponent(verticalLabel)
                        .addComponent(leftLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(loadButton)
                        .addComponent(zoomThreshold)
                        .addComponent(scanStepThreshold)
                        .addComponent(lineThreshold)
                        .addComponent(colorThreshold)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mainLabel)
                        .addComponent(loadButton)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(zoomLabel)
                        .addComponent(zoomThreshold)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(scanStepLabel)
                        .addComponent(scanStepThreshold)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(verticalLabel)
                        .addComponent(lineThreshold)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(leftLabel)
                        .addComponent(colorThreshold)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)));
    }
}
