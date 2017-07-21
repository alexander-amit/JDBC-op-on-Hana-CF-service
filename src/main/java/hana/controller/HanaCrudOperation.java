package hana.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hana.model.SuperHeros;
import hana.repo.SuperHeroRepo;

@RestController
public class HanaCrudOperation {

	
	@Autowired
	SuperHeroRepo superHeroRepo;

	@RequestMapping("/jpa/save/{id}/{name}")
	public String save(@PathVariable("id") String id, @PathVariable("name") String name) {
		try {
			superHeroRepo.save(new SuperHeros(id, name));
			return "Person data saved successfully please invoke getDetails/{id} method to get data";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error saving data.......plz check the logs with cf logs";
		}
	}
	
	@RequestMapping("/jpa/getdetails/{id}")
	public String get(@PathVariable("id") String id) {
		try {
			SuperHeros sh = superHeroRepo.findOne(id);
			return sh.name;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error getting data.......plz check the logs with cf logs";
		}
	}

}
