package com.cjss.eCommerce1.service;

import com.cjss.eCommerce1.configuration.MyUserDetailsService;
import com.cjss.eCommerce1.entity.*;
import com.cjss.eCommerce1.model.OrderModel;
import com.cjss.eCommerce1.model.OrderProModel;
import com.cjss.eCommerce1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FulfilmentService {
    @Autowired
    MyUserDetailsService myUserDetailsService;
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
    private OrderProRepo orderProRepo;
    @Autowired
    private UserRepo userRepo;

    public String placeOrder(Integer skuCode, Integer quantity) {
        Optional<UserEntity> userEntity = userRepo.findById(myUserDetailsService.id);
        Optional<SKUEntity> skuEntity = skuRepo.findById(skuCode);
        if (skuEntity.isPresent()) {
            InventoryEntity inventoryEntity = inventoryRepo.getById(skuCode);
            if (inventoryEntity.getQuantity() >= quantity) {
                OrderEntity orderEntity = new OrderEntity(quantity, "RECEIVED", skuEntity.get(),userEntity.get());
                inventoryEntity.setQuantity(inventoryEntity.getQuantity() - quantity);
                // when we ordered from cart the element will delete from cart
               Optional<CartEntity> cartEntity = cartRepo.findBySkuEntity(skuEntity.get());
                if (cartEntity.isPresent()){
                    if(cartEntity.get().getQuantity() == quantity){
                        cartRepo.deleteById(cartEntity.get().getCartCode());
                    }
                    else {
                        cartEntity.get().setQuantity(cartEntity.get().getQuantity()-quantity);
                        cartRepo.save(cartEntity.get());
                    }
                }
                inventoryRepo.save(inventoryEntity);
                orderRepo.save(orderEntity);
                return "YOUR ORDER " + orderEntity.getOrderCode() + " IS PLACED SUCCESSFULLY";
            }
            return "OUT OF STACK \n AVAILABLE STOCK : "+inventoryEntity.getQuantity();
        }
        return "SKU CODE NOT EXISTS";
    }
    public String placeOrderByCart(){
        Optional<UserEntity> userEntity = userRepo.findById(myUserDetailsService.id);
        System.out.println(myUserDetailsService.id+"mmmmmm");
        List<CartEntity> cartEntities = cartRepo.findByUserEntity(userEntity.get());
        if (!cartEntities.isEmpty()){
        OrderEntity orderEntity = new OrderEntity("RECEIVED");
        orderEntity.setUserEntity(userEntity.get());
        orderRepo.save(orderEntity);
        cartEntities.stream().forEach(cart -> {
            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setSkuCode(cart.getSkuEntity().getSkuCode());
            orderProducts.setQuantity(cart.getQuantity());
            orderProducts.setOrderEntity(orderEntity);
            orderProRepo.save(orderProducts);
            cartRepo.deleteById(cart.getCartCode());
        });
            return "YOUR ORDER " + orderEntity.getOrderCode() + " IS PLACED SUCCESSFULLY";
        }
        return cartEntities+"PLEASE ADD ITEMS TO CART BEFORE ORDER";
    }

    public String acceptToPacking(Integer orderCode) {
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

    public String updatePacking(String status, Integer orderCode) {
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

    public String acceptToShipping(Integer orderCode) {
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

    public String updateShipping(String status, Integer orderCode) {
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

        public List<OrderModel> getOrder(Integer orderCode) {
        Optional<UserEntity> userEntity = userRepo.findById(myUserDetailsService.id);
        List<OrderEntity> orderEntity1 = orderRepo.findByUserEntity(userEntity.get());
        return orderEntity1.stream().map(aa -> {
            Optional<PriceEntity> priceEntity = priceRepo.findById(aa.getSkuEntity().getSkuCode());
            List<OrderProModel> orderProModels = new ArrayList<>();
            aa.getOrderProducts().forEach(ss -> {
                OrderProModel orderProModel = new OrderProModel(ss.getSkuCode(), ss.getQuantity());
                orderProModels.add(orderProModel);
            });
            OrderModel orderModel = new OrderModel(
                    aa.getOrderCode(),aa.getSkuEntity().getProductEntity().getProductName(),
                    aa.getSkuEntity().getSkuCode(),
                    aa.getSkuEntity().getSkuSize(),
                    aa.getQuantity(),
                    priceEntity.get().getPrice() * aa.getQuantity(),
                    aa.getOrderStatus(),
                    orderProModels
            );
            return orderModel;
        }).collect(Collectors.toList());}
//        orderEntity1.stream().map(a1 -> {
//            Optional<OrderEntity> orderEntity = orderRepo.findById(a1.getOrderCode());
//
//
//        if (orderEntity.isPresent())
//        {
//            List<OrderProducts> orderProducts = orderProRepo.findByOrderEntity(orderEntity.get());
//            if(orderProducts.isEmpty()) {
//                Optional<PriceEntity> priceEntity = priceRepo.findById(orderEntity.get().getSkuEntity().getSkuCode());
//                OrderModel orderModel = new OrderModel(orderEntity.get().getOrderCode(),
//                        orderEntity.get().getSkuEntity().getProductEntity().getProductName(),
//                        orderEntity.get().getSkuEntity().getSkuCode(),
//                        orderEntity.get().getSkuEntity().getSkuSize(),
//                        orderEntity.get().getQuantity(),
//                        priceEntity.get().getPrice() * orderEntity.get().getQuantity(),
//                        orderEntity.get().getOrderStatus());
//                return Collections.singletonList(orderModel);
//            }
//            else{
//                return orderProducts.stream().map(orp -> {
//                    PriceEntity priceEntity = priceRepo.getById(orp.getSkuCode());
//                    Optional<SKUEntity> skuEntity = skuRepo.findById(orp.getSkuCode());
//                    return  new OrderModel(
//                            orderEntity.get().getOrderCode(),
//                            skuEntity.get().getProductEntity().getProductName(),
//                            skuEntity.get().getSkuCode(),
//                            skuEntity.get().getSkuSize(),
//                            orp.getQuantity(),
//                            priceEntity.getPrice()*orp.getQuantity(),
//                            orderEntity.get().getOrderStatus()
//                    );
//                }).collect(Collectors.toList());
//            }
//        }
//        })
//        return  null;

}
