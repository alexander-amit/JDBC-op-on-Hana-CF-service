
package hana.model;

import java.io.Serializable;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "superheros")
public class SuperHeros implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    public String id;
	@Column(name = "name")
    public String name;

    

    public SuperHeros() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public SuperHeros(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



    @Override
    public String toString() {
        return "SuperHero [id=" + id + ", name=" + name + "]";
    }

    
}
