package com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation;

public enum ImportActivityTargetType {
    RECEIPT("Receipt"),
    LINE_ITEM("LineItem"),
    QUALITY_INSPECTION("QualityInspection"),
    PUTAWAY("Putaway"),
    SKU_ITEM("SKUItem");

    private final String value;

    ImportActivityTargetType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
