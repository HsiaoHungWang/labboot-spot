package tw.com.ispan.spot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "SpotImagesSpot")
@Data
public class SpotImagesSpot {
	@Id
	@Column(name = "SpotId")
	private Integer spotId;

	@Column(name = "CategoryId")
	private Integer categoryId;
	
	@Column(name = "SpotTitle", columnDefinition = "nvarchar")
	private String spotTitle;
	
	@Column(name = "SpotDescription", columnDefinition = "nvarchar")
	private String spotDescription;

	@Column(name = "Address", columnDefinition = "nvarchar")
	private String address;
	
	@Column(name = "TrafficInfo", columnDefinition = "nvarchar")
	private String trafficInfo;
	
	@Column(name = "Longitude", columnDefinition = "nvarchar")
	private String longitude;

	@Column(name = "Latitude", columnDefinition = "nvarchar")
	private String latitude;

	@Column(name = "OpenTime", columnDefinition = "nvarchar")
	private String openTime;
	
	@Column(name = "ContactPhone", columnDefinition = "nvarchar")
	private String contactPhone;

	@Column(name = "DateCreated")
	private java.util.Date dateCreated;
	
	@Column(name = "SpotImage", columnDefinition = "nvarchar")
	private String spotImage;
}
