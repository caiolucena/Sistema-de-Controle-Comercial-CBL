package com.cbl.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ManualController {
	
	@GetMapping("/manual")
	String manual() {
		return "manual";
	}
	
}
