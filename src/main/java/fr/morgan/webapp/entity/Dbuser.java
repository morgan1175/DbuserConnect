package fr.morgan.webapp.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dbuser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dbuser_id")
	private Long id;
	private String username;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "dbuser_role", joinColumns = @JoinColumn(name = "dbuser_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;


	public Dbuser(String username, String password, List<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}


//	@Override
//	public String toString() {
//		return "Dbuser [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
//	}
	
	

}
