package com.example.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LogModel;
import com.example.repository.LogRepository;

@RestController
public class Controller {

	
	@Autowired
	LogRepository LogRepo;
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	@GetMapping("/")
	public String returnWelcome() {
		return "REST Server Started, and running in port 8080";
		
	}


	@GetMapping("/checkIn")
	public void checkIn(@RequestParam(name="name") String name) {
		LogModel log = new LogModel();
		LocalDateTime now = LocalDateTime.now();
		String[] s = dtf.format(now).split(" ");
		log.setName(name);
		log.setLogType("IN");
		log.setDate(s[0]);
		log.setTime(s[1]);
		log.setId(name+s[1]);
		LogRepo.save(log);
	}
	
	@GetMapping("/checkOut")
	public void checkOut(@RequestParam(name="name") String name) {
		LogModel log = new LogModel();
		LocalDateTime now = LocalDateTime.now();
		String[] s = dtf.format(now).split(" ");
		log.setName(name);
		log.setLogType("OUT");
		log.setDate(s[0]);
		log.setTime(s[1]);
		log.setId(name+s[1]);
		LogRepo.save(log);
	}
	
	@GetMapping("/getLog")
	public List<LogModel> getCheckIn(@RequestParam(name="date") String date){
		return (List<LogModel>)LogRepo.findByDate(date);
	}
	
	@GetMapping("/getAllLog")
	public List<LogModel> getAllLogs(){
		return (List<LogModel>) LogRepo.findAll();
	}
}
