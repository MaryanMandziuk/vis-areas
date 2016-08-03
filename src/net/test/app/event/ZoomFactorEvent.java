/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.test.app.event;

import com.taunova.event.AbstractEvent;

/**
 *
 * @author renat
 */
public class ZoomFactorEvent extends AbstractEvent {
    private int value;
    public ZoomFactorEvent(int value) {
        super(null);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
