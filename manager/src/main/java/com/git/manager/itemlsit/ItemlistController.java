package com.git.manager.itemlsit;

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
public class ItemlistController {

	private ItemlistRepository repo;
	
	@Autowired
	public ItemlistController(ItemlistRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value = "/itemlists")
	public List<Itemlist> getItemLists() throws InterruptedException {
		return repo.findAll(Sort.by("id").descending());
	}
	
	@GetMapping("/itemLists/paging")
	public Page<Itemlist> getItemListsPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}
	
	@PostMapping(value = "/itemlists")
	public Itemlist addItemList(@RequestBody Itemlist itemlist, HttpServletResponse res) {
		
		if ((itemlist.getCntHave() == null || itemlist.getCntHave().isEmpty())
				|| (itemlist.getCntWant() == null || itemlist.getCntWant().isEmpty())) {
			// res.setStatus(400);
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return null;
	}
		
	Itemlist itemlistItem = Itemlist.builder().hostName(itemlist.getHostName())
			.crcHave(itemlist.getCrcHave()).cntHave(itemlist.getCntHave())
			.memo(itemlist.getMemo())
			.crcWant(itemlist.getCrcWant()).cntWant(itemlist.getCntWant())
			.bidderName(itemlist.getBidderName()).status(itemlist.getStatus()).createdTime(new Date().getTime()).build();
	Itemlist itemlistSaved = repo.save(itemlistItem);
				
		res.setStatus(HttpServletResponse.SC_ACCEPTED);
		
		return itemlistSaved;
	}
}
