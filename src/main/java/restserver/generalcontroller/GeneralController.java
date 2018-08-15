package restserver.generalcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restserver.generalservice.GeneralService;

@RestController
public class GeneralController {

	@Autowired
	GeneralService generalService;
	
	///
	@RequestMapping("/general")
	public List<String> findByGeneralName(@RequestParam("chain") String chain) {
		return generalService.findAllByChain(chain);
	}
	
}
