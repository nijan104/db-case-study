package de.niclasjanssen.studycasedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	private StationRepo stationRepo;
	
	@GetMapping(value = "/api/v1/distance/{ds1}/{ds2}")
	public Distance getDistance(@PathVariable String ds1, @PathVariable String ds2) {
		
		Station s1 = stationRepo.getReferenceById(ds1);
		Station s2 = stationRepo.getReferenceById(ds2);
		Distance d = new Distance();
		d.setFrom(s1.getName());
		d.setTo(s2.getName());
		
		int distance;
		
		double dx = 111.3*Math.cos((s1.getBreite() + s2.getBreite())/2*(Math.PI/180))*(s1.getLaenge()-s2.getLaenge());
		double dy = 111.3*(s1.getBreite()-s2.getBreite());
		distance = (int) Math.sqrt(dx*dx + dy*dy);
		//int roundedDistance = Math.floor(distance);
		d.setDistance(distance);
		
		return d;
	}

}
