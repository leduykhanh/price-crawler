package pricecrawler.models.bean;

public class BasicPageLinkElement {

	private String id;
	private String description;
	private String originalHrefValue;
	private String absoluteHrefValue;

	public String getOriginalHrefValue() {
		return originalHrefValue;
	}

	public void setOriginalHrefValue(String originalHrefValue) {
		this.originalHrefValue = originalHrefValue;
	}

	public String getAbsoluteHrefValue() {
		return absoluteHrefValue;
	}

	public void setAbsoluteHrefValue(String absoluteHrefValue) {
		this.absoluteHrefValue = absoluteHrefValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
