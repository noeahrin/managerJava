package com.git.manager.applying;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplyingController {

	private ApplyingRepository repo;
	
	@Autowired
	public ApplyingController(ApplyingRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value = "/applyings")
	public List<Applying> getApplyings() throws InterruptedException {
		return repo.findAll(Sort.by("id").descending());
	}
	
	@GetMapping("/applyings/paging")
	public Page<Applying> getApplyingsPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}
	
	@PostMapping(value = "/applyings")
	public Applying addApplying(@RequestBody Applying applying, HttpServletResponse res) {
		
		if ((applying.getCntHave() == null || applying.getCntHave().isEmpty())
				|| (applying.getCntWant() == null || applying.getCntWant().isEmpty())) {
			// res.setStatus(400);
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return null;
	}
		
	Applying applyingItem = Applying.builder().hostName(applying.getHostName())
			.crcHave(applying.getCrcHave()).cntHave(applying.getCntHave())
			.memo(applying.getMemo())
			.crcWant(applying.getCrcWant()).cntWant(applying.getCntWant())
			.bidderName(applying.getBidderName()).status(applying.getStatus()).createdTime(new Date().getTime()).build();
	Applying applyingSaved = repo.save(applyingItem);
				
		res.setStatus(HttpServletResponse.SC_ACCEPTED);
		
		return applyingSaved;
	}
}
