/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test.async;

import java.util.List;
import net.test.Context;
import net.test.Util;
import net.test.core.Line;

/**
 *
 * @author renat
 */
public class HorizontalScanJob implements Runnable {
    private final int offset;
    private final int heightLimit;
    private final int height;
    private final int scanStep;
    private final Context context;
    private List<Line> result;

    public HorizontalScanJob(int offset, int heightLimit, int height, int scanStep, Context context) {
        this.offset = offset;
        this.heightLimit = heightLimit;
        this.height = height;
        this.scanStep = scanStep;
        this.context = context;
    }

    @Override
    public void run() {
        //result = Util.scannVertically(offset, widthLimit, height, scanStep, context);
    }

    public List<Line> getResult() {
        return result;
    }    
}
