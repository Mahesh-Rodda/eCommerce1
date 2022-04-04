package com.cjss.eCommerce1.service;

import com.cjss.eCommerce1.entity.*;
import com.cjss.eCommerce1.model.OrderModel;
import com.cjss.eCommerce1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FulfilmentService {
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

    public String placeOrder(String skuCode, Integer quantity) {
        Optional<SKUEntity> skuEntity = skuRepo.findById(skuCode);
        if (skuEntity.isPresent()) {
            InventoryEntity inventoryEntity = inventoryRepo.getById(skuCode);
            if (inventoryEntity.getQuantity() >= quantity) {
                OrderEntity orderEntity = new OrderEntity(quantity, "RECEIVEDu", skuEntity.get());
                inventoryEntity.setQuantity(inventoryEntity.getQuantity() - quantity);
                // when we ordered from cart the element will delete from cart
               List<CartEntity> a = cartRepo.findByQuantityAndSkuEntity(quantity, skuEntity.get());
                if (!a.isEmpty()){a.stream().forEach(e -> cartRepo.deleteById(e.getCartCode()));}
                inventoryRepo.save(inventoryEntity);
                orderRepo.save(orderEntity);
                return "YOUR ORDER " + orderEntity.getOrderCode() + " IS PLACED SUCCESSFULLY";
            }
            return "OUT OF STOCK";
        }
        return "SKU CODE NOT EXISTS";
    }

    public String acceptToPacking(String orderCode) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(orderCode);
        if (orderEntity.isPresent()) {
            PackingEntity packingEntity = new PackingEntity(orderEntity.get().getOrderCode(), "Accepted");
            if (orderEntity.get().getOrderStatus().equalsIgnoreCase("RECEIVED"))
            {orderEntity.get().setOrderStatus("PROCESSING");
            orderRepo.saveAndFlush(orderEntity.get());
            packingRepo.save(packingEntity);
            return orderEntity.get().getOrderCode() + " IS ACCEPTED";
            }
            return "ERROR : \n ORDER CODE : "+orderEntity.get().getOrderCode()+"\n ORDER STATUS : "+orderEntity.get().getOrderStatus();
        }
        return orderCode + " IS NOT EXISTS";

    }

    public String updatePacking(String status, String orderCode) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(orderCode);
        if (orderEntity.isPresent()) {
            Optional<PackingEntity> packingEntity = packingRepo.findById(orderEntity.get().getOrderCode());
            if (packingEntity.isPresent()) {
                packingEntity.get().setStatus(status);
                if (packingEntity.get().getStatus().equalsIgnoreCase("PACKING") &&  orderEntity.get().getOrderStatus().equalsIgnoreCase("PROCESSING")) {
                    orderEntity.get().setOrderStatus("PACKING");
                    packingRepo.save(packingEntity.get());
                    orderRepo.save(orderEntity.get());
                    return "STATUS UPDATED AS PACKING";
                }
                else if (status.equalsIgnoreCase("Completed") && orderEntity.get().getOrderStatus().equalsIgnoreCase("PACKING")){
                    packingEntity.get().setStatus("Completed");
                    packingRepo.save(packingEntity.get());
                    return "STATUS UPDATED AS COMPLETED";
                }
                return "ERROR : \n ORDER CODE : "+orderEntity.get().getOrderCode()+"\n ORDER STATUS : "+orderEntity.get().getOrderStatus()+"\n PACKING STATUS : "+packingEntity.get().getStatus();
            }
            return orderEntity.get().getOrderCode() + " IS NOT EXISTS IN PACKING DEPARTMENT";
        }
        return orderCode + " IS NOT EXISTS";
    }

    public String acceptToShipping(String orderCode) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(orderCode);
        if (orderEntity.isPresent()) {
            Optional<PackingEntity> packingEntity = packingRepo.findById(orderEntity.get().getOrderCode());
            if (packingEntity.isPresent()) {
                if (orderEntity.get().getOrderStatus().equalsIgnoreCase("PACKING") && packingEntity.get().getStatus().equalsIgnoreCase("Completed")) {
                    ShippingEntity shippingEntity = new ShippingEntity(packingEntity.get().getOrderCode(), "Accepted");
                    orderEntity.get().setOrderStatus("SHIPPING");
                    shippingRepo.save(shippingEntity);
                    orderRepo.save(orderEntity.get());
                    return shippingEntity.getOrderCode()+ " IS ACCEPTED";
                }
                return "ERROR : \n ORDER CODE : "+packingEntity.get().getOrderCode()+"\n ORDER STATUS : "+packingEntity.get().getStatus();
            }
            return orderEntity.get().getOrderCode() + " IS NOT IN PACKING DEPARTMENT";
        }
        return orderCode + " IS NOT EXISTS";
    }

    public String updateShipping(String status, String orderCode) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(orderCode);
        if (orderEntity.isPresent()) {
            Optional<PackingEntity> packingEntity = packingRepo.findById(orderEntity.get().getOrderCode());
            if (packingEntity.isPresent()) {
                Optional<ShippingEntity> shippingEntity = shippingRepo.findById(packingEntity.get().getOrderCode());
                if (shippingEntity.isPresent()){
                    shippingEntity.get().setStatus(status);
                    if (shippingEntity.get().getStatus().equalsIgnoreCase("DELIVERED") && orderEntity.get().getOrderStatus().equalsIgnoreCase("SHIPPING")){
                        orderEntity.get().setOrderStatus("DELIVERED");
                        orderRepo.save(orderEntity.get());
                        shippingRepo.save(shippingEntity.get());
                        return shippingEntity.get().getOrderCode()+" IS UPDATED";
                    }
                    return "ERROR : \n ORDER CODE : "+shippingEntity.get().getOrderCode()+"\n ORDER STATUS : "+orderEntity.get().getOrderStatus();
                }
                return packingEntity.get().getOrderCode()+"IS NOT EXISTS IN SHIPPING DEPT";
            }
            return orderEntity.get().getOrderCode()+" IS NOT EXISTS IN PACKING DEPT";
        }
        return orderCode+" IS NOT EXISTS";

    }

    public OrderModel getOrder(String orderCode) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(orderCode);
        if (orderEntity.isPresent())
        {
            Optional<PriceEntity> priceEntity = priceRepo.findById(orderEntity.get().getSkuEntity().getSkuCode());
            Optional<InventoryEntity> inventoryEntity = inventoryRepo.findById(orderEntity.get().getSkuEntity().getSkuCode());
            return new OrderModel(orderEntity.get().getOrderCode(),
                    orderEntity.get().getSkuEntity().getProductEntity().getProductName(),
                    orderEntity.get().getSkuEntity().getSkuCode(),
                    orderEntity.get().getSkuEntity().getSkuSize(),
                    orderEntity.get().getQuantity(),
                    priceEntity.get().getPrice()*orderEntity.get().getQuantity(),
                    orderEntity.get().getOrderStatus()
                    );
        }
        return  null;
    }
}
