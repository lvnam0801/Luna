package com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation;

public enum ImportActivityActionType {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    private final String value;

    ImportActivityActionType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
