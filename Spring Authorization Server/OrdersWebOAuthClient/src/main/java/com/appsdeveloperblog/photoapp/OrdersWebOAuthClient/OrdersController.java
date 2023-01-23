/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.photoapp.OrdersWebOAuthClient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

	@GetMapping("/orders")
	public String getOrders(Model model) {

		List<Order> orders = new ArrayList<Order>();
		model.addAttribute("orders", orders);

		return "orders-page";

	}
}
