package net.bean.java.gui.util;

import javafx.scene.control.Control;

import java.lang.reflect.Field;

public class FxComponentStatusChanger {

    private final Object controller;

    public FxComponentStatusChanger(Object controller) {
        this.controller = controller;
    }

    public void changeStatusOfNodes(boolean isDisabled) {
        try {
            Field[] fields = controller.getClass().getDeclaredFields();
            for (Field field : fields)
                if (Control.class.isAssignableFrom(field.getType())) {
                    if (!field.canAccess(controller)) {
                        field.setAccessible(true);
                    }
                    Control control = (Control) field.get(controller);
                    control.setDisable(isDisabled);
                }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
