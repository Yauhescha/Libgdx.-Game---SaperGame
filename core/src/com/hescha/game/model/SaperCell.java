package com.hescha.game.model;

import lombok.Data;

@Data
public class SaperCell {
    // '-1' is nothing, '0' is bomb
    private int value =-1;
    private boolean isSelected;
    private boolean isOpened;
    private boolean isFlagged;
}
