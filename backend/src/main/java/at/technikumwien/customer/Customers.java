package at.technikumwien.customer;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_customers")
@NamedQuery(
	name = "Customers.selectAll",
	query = "SELECT c FROM Customers c "
)
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false, length = 50)
	private String secondName;
	
	@Column(nullable = false, length = 50)
	private LocalDate dateOfBirth;
	
	@Column(nullable = false, length = 50)
	private boolean activated;
	
	public Customers(String firstName, String secondName, LocalDate dateOfBirth,boolean activated) {
		this(null, firstName, secondName, dateOfBirth, activated );
	}
}	