package epsi.myalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.model.Alert;
import epsi.myalerts.repository.AlertRepository;

@RestController
@RequestMapping("api/alerts")
public class AlertController {

	@Autowired
	AlertRepository alertRepository;
	
	@GetMapping("")
	public List<Alert> getAllAlerts(){
		return alertRepository.findAll();
	}
}
