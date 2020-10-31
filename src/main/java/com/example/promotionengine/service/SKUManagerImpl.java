package com.example.promotionengine.service;

import com.example.promotionengine.model.SKU;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of SKUManager interface. Implemented logic to provide functionality to get specific or all SKU's and add or remove SKU's.
 */
@Service
public class SKUManagerImpl implements SKUManager {

    private List<SKU> skuList = new ArrayList<>();

    /**
     * Default constructor to always add some basic SKUs to list as per requirement.
     */
    public SKUManagerImpl() {
        skuList.add(new SKU('A', 50));
        skuList.add(new SKU('B', 30));
        skuList.add(new SKU('C', 20));
        skuList.add(new SKU('D', 15));
    }

    @Override
    public void addSKU(SKU sku) {
        skuList.add(sku);
    }

    @Override
    public void removeSKU(char skuName) {

    }

    /**
     * Method to return list of all available SKUs.
     *
     * @return is list of all available SKUs.
     */
    @Override
    public List<SKU> getAllSKU() {
        return skuList;
    }

    @Override
    public SKU getSKU(char skuName) {
        return null;
    }
}
