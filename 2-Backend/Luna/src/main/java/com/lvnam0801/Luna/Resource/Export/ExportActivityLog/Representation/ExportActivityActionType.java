package com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation;

public enum ExportActivityActionType {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    private final String value;

    ExportActivityActionType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}