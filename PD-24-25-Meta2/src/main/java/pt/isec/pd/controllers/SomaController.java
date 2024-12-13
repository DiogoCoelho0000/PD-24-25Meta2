package pt.isec.pd.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soma")
public class SomaController {
	@GetMapping()
	public String soma(
			@RequestParam(name = "num1", required = false, defaultValue = "-1") int num1,
			@RequestParam(name = "num2", required = false, defaultValue = "-1") int num2) {
		if (num1 == -1 || num2 == -1) {
			return "Dá-me dois números para somar!";
		}

		return "A soma de " + num1 + " com " + num2 + " é " + (num1 + num2) + ".";
	}

	@GetMapping("{item}")
	public String somaItem(
			@PathVariable("item") String item,
			@RequestParam(name = "num1", required = false, defaultValue = "-1") int num1,
			@RequestParam(name = "num2", required = false, defaultValue = "-1") int num2) {

		if (num1 == -1 || num2 == -1) {
			return "Dá-me dois números para somar!";
		}

		return "Tu tens " + num1 + num2 + " " + item + "s. YIIIPEEE";
	}
}
