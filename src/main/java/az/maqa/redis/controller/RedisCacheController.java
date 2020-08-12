package az.maqa.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.maqa.redis.service.RedisCacheService;

@RestController
@RequestMapping("/test")
public class RedisCacheController {

	private int counter = 0;

	@Autowired
	private RedisCacheService redisCacheService;

	@GetMapping
	public String cacheControl() throws InterruptedException {
		if (counter == 5) {
			redisCacheService.clearCache();
			counter = 0;
		}
		counter++;
		return redisCacheService.longRunnigMethod();
	}

}
