package com.example.promotionengine.service;

import com.example.promotionengine.model.SKU;

import java.util.List;

/**
 * Interface to define functionality to get specific or all SKU's and add or remove SKU's.
 */
public interface SKUManager {

    void addSKU(SKU sku);

    void removeSKU(char skuName);

    List<SKU> getAllSKU();

    SKU getSKU(char skuName);
}
