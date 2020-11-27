package ru.bellintegrator.practice.view;

/**
 * Класс-обертка для позвращения данных
 */
public class DataView {

    /**
     * Передаваемые данные
     */
    private final Object data;

    public DataView(Object value) {
        this.data = value;
    }

    public Object getData() {
        return data;
    }
}
