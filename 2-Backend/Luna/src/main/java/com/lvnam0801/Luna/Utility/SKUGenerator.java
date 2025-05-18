package com.lvnam0801.Luna.Utility;

// import java.time.Year;

public class SKUGenerator {
    public static String generateSKU(String productCode, String lotNumber) {
        // String year = String.valueOf(Year.now().getValue());
        return productCode + "-" + lotNumber;
    }
}