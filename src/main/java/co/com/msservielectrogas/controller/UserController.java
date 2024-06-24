package co.com.msservielectrogas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.UserDTO;
import co.com.msservielectrogas.entity.Users;
import co.com.msservielectrogas.services.IUserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	
    @Autowired
    private IUserService userService;
	
    @GetMapping("/login")
    public ResponseEntity<ApiResponseDTO<?>> login(@RequestParam String email, @RequestParam String password) {
        ApiResponseDTO<?> response = userService.login(email, password);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<List<UserDTO>>> searchUsers(@RequestParam String name) {
        List<UserDTO> users = userService.searchUsers(name);
        ApiResponseDTO<List<UserDTO>> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Users>> registerUser(@RequestBody UserDTO dto) {
        ApiResponseDTO<Users> response = userService.registerUser(dto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    /*
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<?> getAll(){
		response = new ResponseDTO();
		try {
			List<Users> list = repository.findAll();
			if (list.size() == 0) {
				response.setMessage("No se encuentran usuarios registrados");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				UserDTO dto = null;
				List<UserDTO> dtos = new ArrayList<>();
				for(Users users : list) {
					dto = convertsDtosInEntitys.convertEntityInDtoUser(users);
					dtos.add(dto);
				}
				return new ResponseEntity<>(dtos, HttpStatus.OK);
			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/byName", produces = "application/json")
	public ResponseEntity<?> getByName(@RequestParam String name){
		response = new ResponseDTO();
		try {
			Users users = repository.findByNameOrEmail(name, null);
			if (Objects.nonNull(users)) {
				return new ResponseEntity<>(convertsDtosInEntitys.convertEntityInDtoUser(users), HttpStatus.OK);
			}else {
				response.setMessage("No se encuentra el usuario");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateUsers(@RequestBody UserDTO dto){
		response = new ResponseDTO();
		Optional<Users> getUsers = repository.findById(dto.getId());
		if (Objects.nonNull(getUsers.get())) {
			Users users = new Users();
			users.setId(dto.getId());
			users.setName(dto.getName() != getUsers.get().getName() ? dto.getName() : getUsers.get().getName());
			users.setEmail(dto.getEmail() != getUsers.get().getEmail() ? dto.getEmail() : getUsers.get().getEmail());
			users.setPassword(dto.getPassword() != getUsers.get().getPassword() ? dto.getPassword() : getUsers.get().getPassword());
			users.setRol(dto.getType() != getUsers.get().getRol() ? dto.getType() : getUsers.get().getRol());
			if (Objects.nonNull(repository.save(users))) {
				response.setMessage("Usuario Modificado Con Exito");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				response.setMessage("No se ha podido modificar el usuario");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			response.setMessage("No se encuentra ningun usuario registrado");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteUsers(@PathVariable("id") Integer id){
		response = new ResponseDTO();
		repository.deleteById(id);
		response.setMessage("Usuario Eliminado con Exito");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/scheduleByUser")
	public ResponseEntity<?> getCallSchedules(@RequestParam String email){
		List<String> getCallOrder;
		List<ResponseDataDTO> dtos = new ArrayList<>();
		Users getUsers = repository.findByNameOrEmail(null, email);
		if (getUsers.getRol().equals(ERoles.TECNICO)) {
			getCallOrder = repository.findByEmail(getUsers.getId());
		} else {
			getCallOrder = repository.findAllSchedules();
		}
		if (getCallOrder.size() >= 1) {
			try {
				for(String fila : getCallOrder) {
					String[] list = fila.split(",");
					ResponseDataDTO dataDTO = new ResponseDataDTO();
					System.out.println(list.length);
					dataDTO.setIdOrder(Long.parseLong(list[0]));
					dataDTO.setStatus(list[1]);
					dataDTO.setTotalCharged(Long.parseLong(list[2]));
					dataDTO.setInfoClient(list[3]);
					dataDTO.setPhone(list[4]);
					dataDTO.setAddress(list[5]);
					dataDTO.setIdSchedule(Integer.parseInt(list[6]));
					dataDTO.setDate(list[7]);
					dataDTO.setHour(list[8]);
					dataDTO.setIdUser(list.length == 9 ? getUsers.getId() : Integer.parseInt(list[9]));
					dataDTO.setNameUser(list.length == 9 ? getUsers.getName() : list[10]);
					dtos.add(dataDTO);	
				} 
			} catch (Exception | NumberFormatException  e) {
			    System.err.println("Error al analizar una cadena a un número: " + e.getMessage());
			}
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	*/

}
