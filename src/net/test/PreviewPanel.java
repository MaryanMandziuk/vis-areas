/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test;

import com.taunova.event.Event;
import com.taunova.event.EventUtil;
import com.taunova.event.Listener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import net.test.app.event.AngleScanEvent;
import net.test.app.event.AreaSeparatorEvent;
import net.test.app.event.ColorThresholdEvent;
import net.test.app.event.LineThresholdEvent;
import net.test.app.event.ScanStepEvent;
import net.test.app.event.ZoomFactorEvent;
import net.test.app.event.MinAreaSizeEvent;
import net.test.app.event.StrokeSizeEvent;
import net.test.core.Area;
import net.test.core.Line;
import net.test.strategy.DefaultScanStrategy;

/**
 *
 * @author renat
 */
public class PreviewPanel extends JPanel {

    public static int ZOOM = 1;

    private int[][] pixels;
    private int[][] pixels2;
    private int width;
    private int height;

    private int zoom = ZOOM;
    private int scanStep = 1;
    private int lineThreshold = 2;
    private int colorThreshold = 80;
    private int minAreaSize = 1;
    private int strokeSize = 4;
    private int areaSeparator = 15;
    private boolean anglePaint = false;

    public static int SCAN_STEP = 1;
    public static int LINE_THRESHOLD = 2;
    public static int COLOR_THRESHOLD = 80;
    public static int MIN_AREA_SIZE = 1;
    public static int STROKE_SIZE = 4;
    public static int AREA_SEPARATOR = 15;

    private boolean ENABLE_RENDERING_HINTS = false;
    
    public PreviewPanel() {
        super(true);
        subscribeEvents();
        try {
            BufferedImage image = ImageIO.read(new File("images/image006.png"));
            BufferedImage image2 = ImageIO.read(new File("images/image005.png"));

            width = image.getWidth();
            height = image.getHeight();

            pixels = new int[width][height];
            pixels2 = new int[width][height];

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int rgb = image.getRGB(i, j);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = (rgb & 0xFF);

                    pixels[i][j] = rgb;

                    if (null != image2) {
                        pixels2[i][j] = image2.getRGB(i, j);
                    }
                    //0.21 R + 0.72 G + 0.07 B
                    //int gray = ((int) (0.21 * r + 0.72 * g + 0.07 * b) & 0xFF);
                    //pixels[i][j] = 0xFF000000 | gray << 16 | gray << 8 | gray;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics graphics) {

        Dimension d = getSize();
        Graphics2D g = (Graphics2D) graphics;

        super.paint(g);

        if (ENABLE_RENDERING_HINTS) {
            RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            g.setRenderingHints(qualityHints);
        }
        
        // zoom functionality
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int value = pixels[i][j];
                g.setColor(new Color(value));
                g.fillRect(i * zoom, j * zoom, zoom, zoom);
                g.fillRect(zoom * width + i * zoom, j * zoom, zoom, zoom);

                if (null != pixels2) {
                    int value2 = pixels2[i][j];
                    g.setColor(new Color(value2));
                    g.fillRect(i * zoom, zoom * height + j * zoom, zoom, zoom);
                    g.fillRect(zoom * width + i * zoom, zoom * height + j * zoom, zoom, zoom);
                }
            }
        }

        Context context = new Context(pixels, colorThreshold, lineThreshold);
        context.setScanStep(scanStep);

        DefaultScanStrategy strategy = new DefaultScanStrategy(pixels, width, height);
        ImageScanner scanner = new ImageScanner(strategy, pixels, width, height);
        ScanResult results;
        if (anglePaint) {
            results = scanner.angleScanImage(context);
            System.out.println("Here");
        } else {
            results = scanner.scanImage(context);
        }
        System.out.println(anglePaint);
        g.setColor(Color.RED);

        results.getVerticalLines().stream().forEach((l) -> {
            g.drawLine(zoom * l.p1.x + zoom * width, zoom * l.p1.y, zoom * l.p2.x + zoom * width, zoom * l.p2.y);
        });

        g.setColor(Color.ORANGE);

        results.getHorizontalLines().stream().forEach((l) -> {
            g.drawLine(zoom * l.p1.x + zoom * width, zoom * l.p1.y, zoom * l.p2.x + zoom * width, zoom * l.p2.y);
        });

        g.setStroke(new BasicStroke(strokeSize));

        for (Area a : results.getAreas()) {
            if (a.getSize() > minAreaSize) {
                Line p = null;
                List<Line> areaLines = a.getLines();
                if (areaLines.size() > areaSeparator * (3 - scanStep)) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.MAGENTA);
                }
                for (int i = 0; i < areaLines.size(); i++) {
                    Line l = areaLines.get(i);

                    if (i == 0 || i == areaLines.size() - 1) {
                        g.drawLine(zoom * width + zoom * l.p1.x, zoom * height + zoom * l.p1.y,
                                zoom * width + zoom * l.p2.x, zoom * height + zoom * l.p2.y);
                    }

                    if (null != p) {
                        g.drawLine(zoom * width + zoom * p.p1.x, zoom * height + zoom * p.p1.y,
                                zoom * width + zoom * l.p1.x, zoom * height + zoom * l.p1.y);
                        g.drawLine(zoom * width + zoom * p.p2.x, zoom * height + zoom * p.p2.y,
                                zoom * width + zoom * l.p2.x, zoom * height + zoom * l.p2.y);
                    }
                    p = l;
                }
            }
        }
    }

    private Object zoomFactorSubscriber;
    private Object scanStepSubscriber;
    private Object lineThresholdSubscriber;
    private Object colorThresholdSubscriber;
    private Object minAreaSizeSubscriber;
    private Object strokeSizeSubscriber;
    private Object areaSeparatorSubscriber;
    private Object anglePaintSubscriber;

    protected final void subscribeEvents() {

        zoomFactorSubscriber = EventUtil.subscribe(ZoomFactorEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
                zoom = ((ZoomFactorEvent) event).getValue();
                repaint();
            }
        });

        scanStepSubscriber = EventUtil.subscribe(ScanStepEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
                scanStep = ((ScanStepEvent) event).getValue();
                repaint();
            }
        });

        lineThresholdSubscriber = EventUtil.subscribe(LineThresholdEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
                lineThreshold = ((LineThresholdEvent) event).getValue();
                repaint();
            }
        });

        colorThresholdSubscriber = EventUtil.subscribe(ColorThresholdEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
                colorThreshold = ((ColorThresholdEvent) event).getValue();
                repaint();
            }
        });
        
        minAreaSizeSubscriber = EventUtil.subscribe(MinAreaSizeEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
                minAreaSize = ((MinAreaSizeEvent) event).getValue();
                repaint();
            }
        });
        
        strokeSizeSubscriber = EventUtil.subscribe(StrokeSizeEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
                strokeSize = ((StrokeSizeEvent) event).getValue();
                repaint();
            }
        });
        
        areaSeparatorSubscriber = EventUtil.subscribe(AreaSeparatorEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
                areaSeparator = ((AreaSeparatorEvent) event).getValue();
                repaint();
            }
        });
        
        anglePaintSubscriber = EventUtil.subscribe(AngleScanEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
                anglePaint = ((AngleScanEvent) event).getValue();
                repaint();
            }
        });

    }
}
