package auto.community;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestCtl {
	ExecutorService pool = Executors.newFixedThreadPool(3);
	HashMap<String, String> map = new HashMap<String, String>();

	@GetMapping("pool")
	public String pool() {
		for (int i = 0; i < 5; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						System.out.println("execute task ing...");
					}
				}
			});
		}
		return "success";
	}

	@GetMapping("hashloop")
	public String hashloop() {
		for (int i = 0; i < 5; i++) {
			final int a = i;
			pool.execute(new Runnable() {
				String name = String.valueOf(a);

				@Override
				public void run() {
					int j = 0;
					while (true) {
						map.put(name + " - " + j, "1");
						j++;
					}
				}
			});
		}
		return "success";
	}
}
