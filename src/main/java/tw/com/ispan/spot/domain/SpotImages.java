package tw.com.ispan.spot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "SpotImages")
@Data
public class SpotImages {
	@Id
	@Column(name = "ImageId")
	private Integer imageId;
	
	@Column(name = "SpotId")
	private Integer spotId;
	
	@Column(name = "ImageTitle", columnDefinition = "nvarchar")
	private String imageTitle;

	@Column(name = "ImagePath", columnDefinition = "nvarchar")
	private String imagePath;
	
}
