package tw.com.ispan.spot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Address")
@Data
public class Address {
	@Id
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "city", columnDefinition = "nvarchar")
	private String city;
	
	@Column(name = "site_id", columnDefinition = "nvarchar")
	private String siteId;
	
	@Column(name = "road", columnDefinition = "nvarchar")
	private String road;
}
