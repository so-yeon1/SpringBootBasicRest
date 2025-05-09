package com.mycom.myapp.service;

import com.mycom.myapp.dto.OrdersCustomerDto;
import com.mycom.myapp.dto.OrdersDto;
import com.mycom.myapp.entity.Orders;
import com.mycom.myapp.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    public List<Orders> listOrder() {
        return ordersRepository.listOrder();
    }

    @Override
    public List<OrdersDto> listOrdersServiceDto() {
        List<Orders> ordersList = ordersRepository.listOrdersServiceDto();
        List<OrdersDto> ordersDtoList = new ArrayList<>();

        ordersList.forEach( orders -> {
            OrdersDto ordersDto =
                    OrdersDto.builder()
                            .id(orders.getId())
                            .orderQuantity(orders.getOrderQuantity())
                            .orderDate(orders.getOrderDate())
                            .build();

            ordersDtoList.add(ordersDto);
        });

        return ordersDtoList;
    }

    @Override
    public List<OrdersDto> listOrdersRepositoryDto() {
        return ordersRepository.listOrdersRepositoryDto();
    }

    @Override
    public List<OrdersCustomerDto> listOrdersCustomerRepositoryDto() {
        return ordersRepository.listOrdersCustomerRepositoryDto();
    }
}
