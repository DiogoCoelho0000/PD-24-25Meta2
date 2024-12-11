package pt.isec.pd.controllers;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lorem")
public class LoremController {
	@GetMapping("{type}")
	public ResponseEntity getText(@PathVariable("type") String type,
	                              @RequestParam(value = "length", required = false) Integer length) {
		if (length == null)
			length = 1;

		return generateLorem(type, length);
	}

	private ResponseEntity generateLorem(String type, Integer length) {
		Lorem lorem = LoremIpsum.getInstance();

		switch (type.toLowerCase()) {
			case "word" -> {
				return ResponseEntity.ok(lorem.getWords(length));
			}
			case "paragraph" -> {
				return ResponseEntity.ok(lorem.getParagraphs(length, length));
			}
			default -> {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Invalid type: " + type + ".");
			}
		}
	}

	/*@PostMapping
	public ResponseEntity postText(@RequestBody LoremConfig config) {
		if (config.getType() == null)
			return ResponseEntity.badRequest().body("Type is mandatory.");

		if (config.getLength() == null)
			config.setLength(1);

		return generateLorem(config.getType(), config.getLength());
	}*/

	@GetMapping
	public ResponseEntity getTextRandomType(@RequestParam(value = "type", required = false) String type,
	                                        @RequestParam(value = "length", required = false) Integer length) {
		if (type == null)
			type = (Math.random() < 0.5 ? "word" : "paragraph");

		if (length == null)
			length = 1;

		return generateLorem(type, length);
	}

}
