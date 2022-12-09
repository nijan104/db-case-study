package de.niclasjanssen.studycasedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class Controller {
	
	@Autowired
	private StationRepo stationRepo;
	
	@GetMapping(value = "/api/v1/distance/{ds1}/{ds2}")
	public Distance getDistance(@PathVariable String ds1, @PathVariable String ds2) {
		Distance d = new Distance();
		boolean exception = false;
		
		try {
		stationRepo.getReferenceById(ds1);
		stationRepo.getReferenceById(ds2);
		} catch (EntityNotFoundException e) {
			exception = true;
		}
		
		if (exception){
			d.setFrom("Not both Stations could");
			d.setTo("be found in database");		
			return d;
		}
		else {
			
			Station s1 = stationRepo.getReferenceById(ds1);
			Station s2 = stationRepo.getReferenceById(ds2);
			d.setFrom(s1.getName());
			d.setTo(s2.getName());
		
			int distance;
		
			double dx = 111.3*Math.cos((s1.getBreite() + s2.getBreite())/2*(Math.PI/180))*(s1.getLaenge()-s2.getLaenge());
			double dy = 111.3*(s1.getBreite()-s2.getBreite());
			distance = (int) Math.sqrt(dx*dx + dy*dy);
			d.setDistance(distance);

		return d;
		}
		
	}
	
	@GetMapping(value = "/api/v1/distance/FV/{ds1}/{ds2}")
	public Distance getFVDistance(@PathVariable String ds1, @PathVariable String ds2) {
		
		Distance d = getDistance(ds1,ds2);
		try {
			if (stationRepo.getReferenceById(ds1).getVerkehr().equals("FV") && stationRepo.getReferenceById(ds2).getVerkehr().equals("FV")) {
				return getDistance(ds1,ds2);
			}
		} catch (EntityNotFoundException e) {
			
		}
		d.setFrom("Not both Stations");
		d.setTo("are included in FV");		
		return d;
	}
	
	@PostMapping(value = "/api/v1/station")
	public String saveStation(@RequestBody Station station) {
		stationRepo.save(station);
		return "new Station saved in Database...\n \n" + station.toString();
	}
	

}
