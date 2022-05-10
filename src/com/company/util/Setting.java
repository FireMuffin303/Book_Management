package com.company.util;

import java.awt.*;
import java.net.URI;

public class Setting {
    int theme = 0;

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getTheme() {
        return theme;
    }

    public void sendReport(){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
