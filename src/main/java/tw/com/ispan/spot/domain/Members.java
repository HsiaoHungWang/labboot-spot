package tw.com.ispan.spot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Members")
@Data
public class Members {
	@Id
	@Column(name = "MemberId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;
	
	@Column(name = "Name", columnDefinition = "nvarchar")
	private String name;
	
	@Column(name = "Password", columnDefinition = "nvarchar")
	private String password;
	
	@Column(name = "Email", columnDefinition = "nvarchar")
	private String email;

	@Column(name = "Age")
	private Integer age;

}
