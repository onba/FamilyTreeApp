package model;

import java.util.Date;

import model.Exceptions.GayException;
import model.Exceptions.MarriageOutOfLifeException;

public class Marriage extends Relationship{

	private Date weddingDay;
	private Date endDay;
	public Date getEndDay() {
		return endDay;
	}
	public void finishMarriage(Date endDay) throws Exception{
		if (this.endDay!=null && this.endDay.before(endDay)) {
			throw new Exception("This marriage is finished yet.");
		}
		this.endDay = endDay;
	}
	public Date getWeddingDay() {
		return weddingDay;
	}
	public Marriage(Person male, Person female, Date weddingDay, Date endDay) throws GayException, MarriageOutOfLifeException{
		super(male, female);
		if (male.getBirthDay().after(weddingDay) || female.getBirthDay().after(weddingDay) || male.getDeathDay().before(weddingDay) || female.getDeathDay().before(weddingDay)) {
			throw new MarriageOutOfLifeException("Wrong Wedding day! The Persons who goes to marriage must be alive.");
		}
		this.weddingDay = weddingDay;
		
		Date firstDeathDay = male.getDeathDay();
		if (firstDeathDay==null || firstDeathDay.before(female.getDeathDay()))
			firstDeathDay = female.getDeathDay();
		if (firstDeathDay!=null) {
			if (endDay ==null) this.endDay = firstDeathDay;
			else if (endDay!=null && firstDeathDay.before(endDay)) throw new MarriageOutOfLifeException("The marriage can't be active, just with 2 living Person.");
			else this.endDay = endDay;
		}
		else {
			this.endDay = endDay;
		}
	}
	public Marriage(Person male, Person female) throws GayException {
		super(male, female);
	}

	@Override
	public String getName() {
		return "Marriage";
	}

}
