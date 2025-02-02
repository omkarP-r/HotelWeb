package com.project.emrald.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name ="bookings")
public class Booking {

	
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	 
	 @NotNull(message = "Check In Date is Required")
	  private LocalDate checkInDate;
	 @Future(message  = "Check Out Date must be in Future.")
	  private LocalDate checkOutDate;
	 @Min(value = 1, message  = "Minimum number of Adults Should be atleast one.")
	  private Integer numOfAdults;
	 @Min(value = 0, message  = "Number of Children should not be less than zero")
	  private Integer numOfChildren;
	  private Integer totalNumOfGuests;
	  
	  private String bookingConfirmationCode;
	  
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "user_id")
	  private User user;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "room_id")
	  private Room room;
	  
	  
	  public void calculateTotalNumOfGuests() {
		  this.totalNumOfGuests = this.numOfAdults + this.numOfChildren;
	  }
	  
	  public void setNumOfAdults(Integer numOfAdults) {
		  this.numOfAdults = numOfAdults;
		  calculateTotalNumOfGuests();
	  }
	  public void setNumOfChildren(Integer numOfChildren) {
		  this.numOfChildren = numOfChildren;
		  calculateTotalNumOfGuests();
	  }

	@Override
	public String toString() {
		return "Booking [id=" + id + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", numOfAdults=" + numOfAdults + ", numOfChildren=" + numOfChildren + ", totalNumOfGuests="
				+ totalNumOfGuests + ", bookingConfirmationCode=" + bookingConfirmationCode + "]";
	}
	  
	  
	  
}
