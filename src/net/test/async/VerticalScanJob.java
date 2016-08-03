/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test.async;

import net.test.core.Line;
import java.util.List;
import net.test.Context;
import net.test.Util;

/**
 *
 * @author renat
 */
public class VerticalScanJob implements Runnable {

    private final int offset;
    private final int widthLimit;
    private final int height;
    private final int scanStep;
    private final Context context;
    private List<Line> result;

    public VerticalScanJob(int offset, int widthLimit, int height, int scanStep, Context context) {
        this.offset = offset;
        this.widthLimit = widthLimit;
        this.height = height;
        this.scanStep = scanStep;
        this.context = context;
    }

    @Override
    public void run() {
        result = Util.scannVertically(offset, widthLimit, height, scanStep, context);
    }

    public List<Line> getResult() {
        return result;
    }
}
