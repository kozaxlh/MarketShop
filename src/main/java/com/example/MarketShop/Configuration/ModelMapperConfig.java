package com.example.MarketShop.Configuration;

import com.example.MarketShop.DTO.UsersDTO;
import com.example.MarketShop.Model.Orders;
import com.example.MarketShop.Model.Users;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        convertUsersToUserDTO(modelMapper);

        return modelMapper;
    }


    private void convertUsersToUserDTO(ModelMapper modelMapper) {
        Converter<List<Orders>, List<Integer>> ordersListToOrderIdListConverter =
                converter -> converter.getSource()
                        .stream()
                        .mapToInt(Orders::getOrderID)
                        .boxed().toList();

        modelMapper.createTypeMap(Users.class, UsersDTO.class, "OrdersDTOList")
                .addMapping(Users::getOrders, UsersDTO::setOrders);

        modelMapper.createTypeMap(Users.class, UsersDTO.class)
                .addMappings(map -> map
                        .using(ordersListToOrderIdListConverter)
                        .map(
                                Users::getOrders,
                                UsersDTO::setOrders
                        )
                );
    }

    private void convertOrdersDTOWithProductIdList(ModelMapper modelMapper) {
//        Converter<Collection<Orderdetail>, List<Integer>> orderDetailListToProductIdListConverter =
//                converter -> converter.getSource()
//                        .stream()
//                        .map()
//                        .boxed().toList();

//        modelMapper.createTypeMap(Orders.class, OrdersDTO.class)
//                .addMapping(source -> source.getProductList()
//                        .stream()
//                        .map(Orderdetail::getProduct, OrderdetailDTO::setProductID)
//                );
    }
}
