/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.test.core.Area;
import net.test.Context;
import net.test.core.Line;
import net.test.ScanResult;
import net.test.async.VerticalScanJob;

/**
 *
 * @author renat
 */
public class ParallelScanStrategy extends DefaultScanStrategy {
    private int parallel = 8;
    private ExecutorService executor = Executors.newFixedThreadPool(parallel);
            

    
  public ParallelScanStrategy(int[][] pixels, int width, int height) {
      super(pixels, width, height);          
    }    
    
    public ScanResult scanImage(Context context) {
        ScanResult result = null;
        try {
            List<Line> vertical = scanVerticallyParallel(context, executor, parallel);
            List<Line> horizontal = scanHorizontally(context);
            List<Line> intersections = scanIntersections(horizontal, vertical);
            List<Area> areas = restoreAreas(intersections);
            result = new ScanResult(areas, vertical, horizontal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * @param context
     * @param executor
     * @param parallel
     * @return
     * @throws InterruptedException 
     */
    public final List<Line> scanVerticallyParallel(Context context, ExecutorService executor, int parallel) throws InterruptedException {
        final int step = width/parallel;
        
        List<Line> result = new ArrayList();       
        List<VerticalScanJob> jobs = new ArrayList();
        
        for(int i=0; i+step<width; i+=step) {
            VerticalScanJob job = new VerticalScanJob(i, i+step, height, context.getScanStep(), context);
            jobs.add(job);
            executor.execute(job);
        }
        
        executor.awaitTermination(context.getTimeout(), context.getTimeoutUnit());
        
        jobs.stream().forEach((job) -> {
            result.addAll(job.getResult());
        });
        
        return result;
    }    
    

}

