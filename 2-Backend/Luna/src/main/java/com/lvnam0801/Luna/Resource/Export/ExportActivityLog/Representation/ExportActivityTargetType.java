package com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation;

public enum ExportActivityTargetType {
    ORDER("Order"),
    LINE_ITEM("LineItem"),
    PICKING_TASK("PickingTask"),
    PACKING("Packing"),
    SHIPMENT("Shipment"),
    SKU_ITEM("SKUItem"),;

    private final String value;

    ExportActivityTargetType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}