package guru.springframework.spring7restmvc.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
public class BeerOrder {

	public BeerOrder(UUID id, Long version, Timestamp createDate, String customerRef, Customer customer, Set<BeerOrderLine> beerOrderLines, BeerOrderShipment beerOrderShipment) {
		this.id = id;
		this.version = version;
		this.createDate = createDate;
		this.customerRef = customerRef;
		this.setCustomer(customer);
		this.beerOrderLines = beerOrderLines;
		this.setBeerOrderShipment(beerOrderShipment);
	}

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
	@JdbcTypeCode(SqlTypes.CHAR)
	private UUID id;

	@Version
	private Long version;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createDate;

	private String customerRef;

	private void setCustomer(Customer customer) {
		this.customer = customer;
    	customer.getBeerOrders().add(this);
	}

	@ManyToOne
	private Customer customer;

	@OneToMany(mappedBy = "beerOrder")
	private Set<BeerOrderLine> beerOrderLines;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private BeerOrderShipment beerOrderShipment;

	private void setBeerOrderShipment(BeerOrderShipment beerOrderShipment) {
		this.beerOrderShipment = beerOrderShipment;
		beerOrderShipment.setBeerOrder(this);
	}

}
