package com.example.promotionengine.service;

import com.example.promotionengine.model.SKU;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Method to remove specific SKU from the list.
     *
     * @param skuName Name of SKU to be removed.
     */
    @Override
    public void removeSKU(char skuName) {
        skuList.removeIf(sku -> sku.getName() == skuName);
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


    /**
     * Method to return SKU for specific SKU name.
     *
     * @param skuName Name of SKU to be returned.
     * @return is the SKU matching input skuName.
     */
    @Override
    public SKU getSKU(char skuName) {
        List<SKU> listOfSKU = skuList.stream().filter(sku -> sku.getName() == skuName).collect(Collectors.toList());
        if (!listOfSKU.isEmpty()) {
            return listOfSKU.get(0);
        } else
            return null;
    }
}
