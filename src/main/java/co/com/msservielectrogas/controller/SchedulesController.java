package co.com.msservielectrogas.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.OrderDTO;
import co.com.msservielectrogas.dto.ResponseDTO;
import co.com.msservielectrogas.dto.SchedulesDTO;
import co.com.msservielectrogas.entity.Schedules;
import co.com.msservielectrogas.entity.Users;
import co.com.msservielectrogas.repository.ISchedulesRepository;
import co.com.msservielectrogas.repository.IUsersRepository;
import co.com.msservielectrogas.services.ISchedulesService;
import co.com.msservielectrogas.util.CreatedExcel;

@RestController
@RequestMapping(value = "/api/schedules")
public class SchedulesController {

	@Autowired
	private ISchedulesRepository repository;

	@Autowired
	private IUsersRepository usersRepository;
	
	@Autowired
	private ISchedulesService schedulesService;

	@Autowired
	private CreatedExcel createdExcel;

	private ResponseDTO response;
	
    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<SchedulesDTO>>> getAllSchedules(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idSchedule,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(getSortSchedules(sort)));

        Page<SchedulesDTO> schedules = schedulesService.getAllSchedules(search, date, pageable);
        ApiResponseDTO<Page<SchedulesDTO>> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), schedules);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    private List<Sort.Order> getSortSchedules(String[] sort) {
        List<Sort.Order> schedules = new ArrayList<>();

        if (sort[0].contains(",")) {
            // sort order like: "field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                schedules.add(new Sort.Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            // default sort order
        	schedules.add(new Sort.Order(Sort.Direction.ASC, sort[0]));
        }

        return schedules;
    }

/*	@GetMapping(value = "/byIdUser", produces = "application/json")
	public ResponseEntity<?> getCallByOrder(@RequestParam String email) {
		response = new ResponseDTO();
		Users users = usersRepository.findByNameOrEmail(null, email);
		if (Objects.isNull(users)) {
			response.setMessage("No se encuentra el usuario");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			List<Schedules> list = repository.findByUsers(users.getId());
			if (list.size() == 0) {
				response.setMessage("No se encuentra la agenda relacionada con la orden");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				List<SchedulesDTO> dtos = new ArrayList<>();
				for (Schedules schedules : list) {
					SchedulesDTO dto = convertsDtosInEntitys.convertEntityInDtoSchedules(schedules);
					dtos.add(dto);
				}
				return new ResponseEntity<>(dtos, HttpStatus.OK);
			}
		}
	}

	@GetMapping(value = "/byIdOrder", produces = "application/json")
	public ResponseEntity<?> getCallByUser(@RequestParam Long idOrder) {
		response = new ResponseDTO();
		Schedules schedules = repository.findByOrders(idOrder);
		if (Objects.isNull(schedules)) {
			response.setMessage("No se encuentra la agenda relacionada con la orden");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			SchedulesDTO dto = convertsDtosInEntitys.convertEntityInDtoSchedules(schedules);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/byDate", produces = "application/json")
	public ResponseEntity<?> getByDate(@RequestParam String date) throws ParseException {
		SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
		Date dates = dateFor.parse(date);
		List<Schedules> list = repository.findByDate(dates);
		if (list.size() == 0) {
			response.setMessage("No se encuentra la agenda relacionada con la fecha seleccionada");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			List<SchedulesDTO> dtos = new ArrayList<>();
			for (Schedules schedules : list) {
				SchedulesDTO dto = convertsDtosInEntitys.convertEntityInDtoSchedules(schedules);
				dtos.add(dto);
			}
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/byHour", produces = "application/json")
	public ResponseEntity<?> getByHour(@RequestParam String hour) {
		List<Schedules> list = repository.findByHour(hour);
		if (list.size() == 0) {
			response.setMessage("No se encuentra la agenda relacionada con la fecha seleccionada");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			List<SchedulesDTO> dtos = new ArrayList<>();
			for (Schedules schedules : list) {
				SchedulesDTO dto = convertsDtosInEntitys.convertEntityInDtoSchedules(schedules);
				dtos.add(dto);
			}
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllClients() {
		response = new ResponseDTO();
		try {
			List<Schedules> list = repository.findAll();
			if (list.size() == 0) {
				response.setMessage("No se encuentran agendas registradas");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				SchedulesDTO dto = null;
				List<SchedulesDTO> dtos = new ArrayList<>();
				for (Schedules schedules : list) {
					dto = convertsDtosInEntitys.convertEntityInDtoSchedules(schedules);
					dtos.add(dto);
				}
				return new ResponseEntity<>(dtos, HttpStatus.OK);
			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> createSchedules(@RequestBody SchedulesDTO dto) {
		response = new ResponseDTO();
		Schedules schedules = convertsDtosInEntitys.convertSchedules(dto);
		if (Objects.nonNull(schedules)) {
			response.setMessage("Agenda Creada Exitosamente");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response.setMessage("No se ha podido crear la agenda");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateScheldules(@RequestBody SchedulesDTO dto) {
		response = new ResponseDTO();
		Optional<Schedules> getSchedules = repository.findById(dto.getId());
		if (Objects.nonNull(getSchedules.get())) {
			Schedules schedule = convertsDtosInEntitys.convertSchedulesUpdate(dto, getSchedules.get());
			if (Objects.nonNull(schedule)) {
				response.setMessage("Agenda Actualizada Correctamente");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setMessage("No se pudo actualizar la agenda");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			response.setMessage("No se pudo encontrar nignuna agenda");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteScheldule(@PathVariable("id") Integer id) {
		response = new ResponseDTO();
		repository.deleteById(id);
		response.setMessage("Agenda elimina con exito");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/download/reporte.xlsx")
	public void downloadCsv(@RequestParam String date, @RequestParam(required = false) String status,
			HttpServletResponse response) throws IOException, ParseException {
		SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = dateFor.parse(date);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=Rutero_" + date + ".xlsx");
		List<String> list = repository.findByDateOrStatus(date2, status);
		ByteArrayInputStream stream = createdExcel.writeExcel(list);
		IOUtils.copy(stream, response.getOutputStream());
	}
	*/
}
