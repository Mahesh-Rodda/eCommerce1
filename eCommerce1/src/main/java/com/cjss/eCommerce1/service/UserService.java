package com.cjss.eCommerce1.service;

import com.cjss.eCommerce1.configuration.MyUserDetailsService;
import com.cjss.eCommerce1.entity.UserAddressEntity;
import com.cjss.eCommerce1.entity.UserEntity;
import com.cjss.eCommerce1.model.UserAddressModel;
import com.cjss.eCommerce1.model.UserModel;
import com.cjss.eCommerce1.repository.UserAddRepo;
import com.cjss.eCommerce1.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserAddRepo userAddRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


            public String userRegistration(UserModel userModel){
                UserEntity userEntity = new UserEntity();
                userEntity.setFirstname(userModel.getFirstname());
                userEntity.setLastname(userModel.getLastname());
                userEntity.setEmail(userModel.getEmail());
                userEntity.setMobileNumber(userModel.getMobileNumber());
                userEntity.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
                userRepo.save(userEntity);
                userModel.getUserAddressModels().forEach(aa ->
                {
                    UserAddressEntity userAddressEntity = new UserAddressEntity();
//                    userAddressEntity.setAddressId(aa.getAddressId());
//                    userAddressEntity.setLine1(aa.getLine1());
//                    userAddressEntity.setLine2(aa.getLine2());
//                    userAddressEntity.setCity(aa.getCity());
//                    userAddressEntity.setCountry(aa.getCountry());
//                    userAddressEntity.setState(aa.getState());
//                    userAddressEntity.setPostalCode(aa.getPostalCode());
//                    userAddressEntity.setBillingAddress(aa.isBillingAddress());
//                    userAddressEntity.setShippingAddress(aa.isShippingAddress());
                    BeanUtils.copyProperties(aa,userAddressEntity);
                    userAddressEntity.setUserEntity(userEntity);
                    userAddRepo.save(userAddressEntity);
                });

                return userEntity.getId()+" is Your ID";
            }
            public UserModel getUserModel(){
                Optional<UserEntity> userEntity = userRepo.findById(myUserDetailsService.id);
                if (userEntity.isPresent())
                {
                    UserModel userModel = new UserModel();
                    BeanUtils.copyProperties(userEntity.get(),userModel);
//                    userModel.setPassword(new DCrypt);
                    List<UserAddressModel> userAddressModels = new ArrayList<>();
                   userEntity.get().getUserAddressEntity().forEach(aa -> {
                       UserAddressModel userAddressModel = new UserAddressModel();
                       BeanUtils.copyProperties(aa,userAddressModel);
                       userAddressModels.add(userAddressModel);
                   });
                   userModel.setUserAddressModels(userAddressModels);
                    return userModel;
                }
                return null;
            }

}
