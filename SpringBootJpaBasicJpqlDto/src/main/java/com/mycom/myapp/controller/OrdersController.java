package com.mycom.myapp.controller;

import com.mycom.myapp.dto.OrdersCustomerDto;
import com.mycom.myapp.dto.OrdersDto;
import com.mycom.myapp.entity.Orders;
import com.mycom.myapp.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {

    private final OrdersService ordersService;

    // #0.
    // Controller에서 Entity를 JSON으로 변환, 응답.
//    @GetMapping("/listorders")
//    public List<Orders> listOrders() {
//        return ordersService.listOrder();
//    }


    // #1
    // Controller에서 entity -> dto 변경 후, dto를 json 변환, 응답.
//    @GetMapping("/listorders")
//    public List<OrdersDto> listOrders() {
//        List<Orders> ordersList =  ordersService.listOrder();
//        List<OrdersDto> ordersDtoList = new ArrayList<>();
//
//        ordersList.forEach( orders -> {
//           OrdersDto ordersDto =
//                   OrdersDto.builder()
//                           .id(orders.getId())
//                           .orderQuantity(orders.getOrderQuantity())
//                           .orderDate(orders.getOrderDate())
//                           .build();
//
//           ordersDtoList.add(ordersDto);
//        });
//
//        return ordersDtoList;
//    }


    // #2
    // Service에서 entity -> dto 변경 후, dto를 json 변환, 응답.
//    @GetMapping("/listordersservicedto")
//    public List<OrdersDto> listOrders() {
//        return ordersService.listOrdersServiceDto();
//    }


    // #3
    // Repository에서 entity -> dto 변경 후, dto를 json 변환, 응답.
//    @GetMapping("/listordersrepositorydto")
//    public List<OrdersDto> listOrders() {
//        return ordersService.listOrdersRepositoryDto();
//    }

    // #4
    // Repository에서 entity -> dto 변경, 특정 컬럼만 뽑아서.
    @GetMapping("/listorderscustomerrepositorydto")
    public List<OrdersCustomerDto> listOrders() {
        return ordersService.listOrdersCustomerRepositoryDto();
    }
}
