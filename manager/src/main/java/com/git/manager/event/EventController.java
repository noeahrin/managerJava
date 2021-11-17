package com.git.manager.event;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {



	private EventRepository repo;
	
	@Autowired
	public EventController(EventRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value = "/events")
	public List<Event> getApplyings() throws InterruptedException {
		return repo.findAll(Sort.by("id").descending());
	}
	
	
	@PostMapping(value = "/events")
	public Event addEvent(@RequestBody Event event, HttpServletResponse res) {
		
		if ((event.getTitle() == null || event.getTitle().isEmpty())
				|| (event.getMemo() == null || event.getMemo().isEmpty())) {
			// res.setStatus(400);
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return null;
	}
		
		Event eventItem = Event.builder().groupId(event.getGroupId()).title(event.getTitle()).memo(event.getMemo()).start(new Date().getTime()).build();
		Event eventSaved = repo.save(eventItem);
				
		res.setStatus(HttpServletResponse.SC_ACCEPTED);
		
		return eventSaved;
	}
}
