/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test.app.event;

import com.taunova.event.AbstractEvent;
/**
 *
 * @author maryan
 */
public class AngleScanEvent extends AbstractEvent {
    private boolean value;
    public AngleScanEvent(boolean value) {
        super(null);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
