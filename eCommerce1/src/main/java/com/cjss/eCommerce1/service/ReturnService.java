package com.cjss.eCommerce1.service;

import com.cjss.eCommerce1.entity.InventoryEntity;
import com.cjss.eCommerce1.entity.OrderEntity;
import com.cjss.eCommerce1.entity.ReturnEntity;
import com.cjss.eCommerce1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnService {
    @Autowired
    private ShippingRepo shippingRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SKURepo skuRepo;
    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private InventoryRepo inventoryRepo;
    @Autowired
    private PackingRepo packingRepo;
    @Autowired
    private ReturnRepo returnRepo;

    public String updateReturn(String orderCode, String status){
        Optional<OrderEntity> orderEntity = orderRepo.findById(orderCode);
        if (orderEntity.isPresent() && status.equalsIgnoreCase("RETURN")){
            if(orderEntity.get().getOrderStatus().equalsIgnoreCase("DELIVERED"))
            {
                ReturnEntity returnEntity = new ReturnEntity(orderEntity.get().getOrderCode(),status);
                orderEntity.get().setOrderStatus("RETURN INITIATED");
                orderRepo.save(orderEntity.get());
                returnRepo.save(returnEntity);
                return returnEntity.getOrderCode()+" IS INITIATED FOR RETURN";
            }
            return  " Wait until the order delivery "+orderEntity.get().getOrderCode();
        }
        else if (orderEntity.get().getOrderStatus().equalsIgnoreCase("RETURN INITIATED") || orderEntity.get().getOrderStatus().equalsIgnoreCase("RETURN ACCEPTED"))
        {
            Optional<ReturnEntity> returnEntity = returnRepo.findById(orderCode);
            if (returnEntity.isPresent() && status.equalsIgnoreCase("ACCEPT"))
        {
            returnEntity.get().setStatus(status);
            orderEntity.get().setOrderStatus("RETURN ACCEPTED");
            orderRepo.save(orderEntity.get());
            returnRepo.save(returnEntity.get());
            return  returnEntity.get().getOrderCode()+" IS ACCEPTED";
        }
            else if (returnEntity.isPresent() && status.equalsIgnoreCase("RETURNED")){
                returnEntity.get().setStatus(status);
                orderEntity.get().setOrderStatus("RETURNED");
                InventoryEntity inventoryEntity = inventoryRepo.getById(orderEntity.get().getSkuEntity().getSkuCode());
                inventoryEntity.setQuantity(inventoryEntity.getQuantity()+orderEntity.get().getQuantity());
                inventoryRepo.save(inventoryEntity);
                return "RETURNED";
            }
        return orderCode+" IS NOT RETURN INITIATED";
    }
        return orderCode+" IS NOT EXISTS";
    }
}
