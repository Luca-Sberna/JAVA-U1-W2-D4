package Exercise;

public class Customer {
	private Long id;
	private String name;
	private Integer tier;

	public Customer(long id, String name, Integer tier) {
		this.id = id;
		this.name = name;
		this.tier = tier;
	}

	public int getTier() {
		return tier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTier(Integer tier) {
		this.tier = tier;
	}
}
