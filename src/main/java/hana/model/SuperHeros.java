
package hana.model;

import java.io.Serializable;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "superheros")*/
public class SuperHeros implements Serializable{

	private static final long serialVersionUID = 1L;
	//@Id
    public String id;
	//@Column(name = "name")
    public String name;

    

    public SuperHeros(String id, String name) {
		this.id = id;
		this.name = name;
	}



    @Override
    public String toString() {
        return "SuperHero [id=" + id + ", name=" + name + "]";
    }

    
}
