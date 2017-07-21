package hana.repo;
import org.springframework.data.repository.CrudRepository;

import hana.model.SuperHeros;

public interface SuperHeroRepo extends CrudRepository<SuperHeros, String>{


}
