package com.example.ecommerce.controller.admin;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String listOrders(Model model, @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "7") int size) {
        Page<Order> ordersPage = orderService.findAllPaginated(page, size, "orderDate", "desc");

        model.addAttribute("ordersPage", ordersPage);
        model.addAttribute("orders", ordersPage.getContent());
        model.addAttribute("order", new Order());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ordersPage.getTotalPages());

        return "admin/orders/index";
    }

    @GetMapping("/{id}")
    public String getOrderDetail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("orderDetails", orderService.findOrderDetailsByOrderId(id));
        return "admin/orders/detail";
    }

    @PostMapping("/update-status")
    public String updateOrderStatus(@ModelAttribute("order") Order order,
                                    @RequestParam("orderId") Long id
    ) {
        orderService.updateOrderStatus(id, order.getStatus().getId(), order.getReason());
        return "redirect:/admin/orders/";
    }


}
