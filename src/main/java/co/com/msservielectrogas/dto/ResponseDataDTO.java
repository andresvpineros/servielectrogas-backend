package co.com.msservielectrogas.dto;

import lombok.Data;

@Data
public class ResponseDataDTO {

	private Long idOrder;
	private String status;
	private Long totalCharged;
	private String infoClient;
	private String phone;
	private String address;
	private Integer idSchedule;
	private String date;
	private String hour;
	private Integer idUser;
	private String nameUser;
}
