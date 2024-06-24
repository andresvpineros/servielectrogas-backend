package co.com.msservielectrogas.dto;

import lombok.Data;

@Data
public class DataDTO {

	private ClientDTO client;
	private OrderDTO order;
	private SchedulesDTO schedules;
}
