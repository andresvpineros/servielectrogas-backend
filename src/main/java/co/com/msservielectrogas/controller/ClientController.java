package co.com.msservielectrogas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.ClientDTO;
import co.com.msservielectrogas.services.IClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	@Autowired
	private IClientService clientService;
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<List<ClientDTO>>> searchClients(@RequestParam String query) {
        List<ClientDTO> clients = clientService.searchClients(query);
        ApiResponseDTO<List<ClientDTO>> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), clients);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDTO<ClientDTO>> getClientById(@PathVariable Long id) {
	    ClientDTO clientDTO = clientService.getClientById(id);
	    if (clientDTO != null) {
	        ApiResponseDTO<ClientDTO> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), clientDTO);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        ApiResponseDTO<ClientDTO> response = new ApiResponseDTO<>("Cliente no encontrado", HttpStatus.NOT_FOUND.value(), null);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}
	
	/*

	@PostMapping(value = "/register")
	public ResponseEntity<?> registerClient(@RequestBody ClientDTO dto) {
		try {
			response = new ResponseDTO();
			if (repository.existsByPhone(dto.getPhone()) || repository.existsByAddress(dto.getAddress())) {
				return new ResponseEntity<>("El cliente ya se encuentra registrado", HttpStatus.BAD_REQUEST);
			}
			Clients client = convertsDtosInEntitys.convertClient(dto);
			if (Objects.nonNull(client)) {
				response.setMessage("Cliente registrado con exito");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setMessage("No se ha podido regisrar el cliente");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllClients() {
		response = new ResponseDTO();
		try {
			List<Clients> list = repository.findAll();
			if (list.size() == 0) {
				response.setMessage("No se encuentran clientes registrados");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				ClientDTO dto = null;
				List<ClientDTO> dtos = new ArrayList<>();
				for(Clients clients : list) {
					dto = convertsDtosInEntitys.convertEntityInDtoClient(clients);
					dtos.add(dto);
				}
				return new ResponseEntity<>(dtos, HttpStatus.OK);
			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/clientBy", produces = "application/json")
	public ResponseEntity<?> getClientByPhone(@RequestParam String phone) {
		try {
			response = new ResponseDTO();
			Clients clients = repository.findByPhone(phone);
			if (Objects.nonNull(clients)) {
				ClientDTO dto = convertsDtosInEntitys.convertEntityInDtoClient(clients);
				return new ResponseEntity<>(dto, HttpStatus.OK);
			} else {
				response.setMessage("No se encuentra registrado ningun cliente");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateClient(@RequestBody ClientDTO dto) {
		response = new ResponseDTO();
		Clients getClient = repository.findByPhone(dto.getPhone());
		if (Objects.nonNull(getClient)) {
			Clients client = convertsDtosInEntitys.convertClientUpdate(dto, getClient);
			if (Objects.nonNull(client)) {
				response.setMessage("Cliente Actualizado Con Exito");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setMessage("No se ha podido actualizar el cliente");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			response.setMessage("No se encuentra registrado ningun cliente");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable("id") Integer id) {
		repository.deleteById(id);
		response = new ResponseDTO();
		response.setMessage("Cliente Eliminado Exitosamente");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
*/
}
