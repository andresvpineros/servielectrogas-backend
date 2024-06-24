package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.Users;
import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.entity.ServiceActivity;
import co.com.msservielectrogas.entity.Services;
import co.com.msservielectrogas.dto.OrderDTO;
import co.com.msservielectrogas.dto.OrderServiceDTO;
import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.ClientDTO;
import co.com.msservielectrogas.dto.ServiceActivityDTO;
import co.com.msservielectrogas.dto.CreateServiceActivityDTO;
import co.com.msservielectrogas.dto.UpdateServiceActivityDTO;
import co.com.msservielectrogas.dto.UserDTO;
import co.com.msservielectrogas.repository.IServiceActivityRepository;
import co.com.msservielectrogas.repository.IUsersRepository;
import co.com.msservielectrogas.repository.IOrderServiceRepository;
import co.com.msservielectrogas.repository.IUsersRepository;
import co.com.msservielectrogas.specification.OrderSpecifications;
import co.com.msservielectrogas.enums.EClientType;
import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.ERoles;
import co.com.msservielectrogas.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IUserService {

    @Autowired
    private IUsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponseDTO<?> login(String email, String password) {
        Users user = userRepository.findByEmail(email);
        if (Objects.isNull(user)) {
            return new ApiResponseDTO<>("El correo electrónico no se encuentra registrado", HttpStatus.UNAUTHORIZED.value(), null);
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ApiResponseDTO<>("La contraseña no es válida", HttpStatus.UNAUTHORIZED.value(), null);
        } else {
            return new ApiResponseDTO<>("Sesión iniciada con éxito", HttpStatus.OK.value(), null);
        }
    }
    
    public List<UserDTO> searchUsers(String query) {
        List<Users> user = userRepository.searchUsers(query);
        
        if (user == null) {
            return null;
        }
        
        return user.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }
    
    public UserDTO getUserById(Integer id) {
        Optional<Users> userOptional = userRepository.findById(id);
        return userOptional.map(this::convertToDTO).orElse(null);
    }
    
    public ApiResponseDTO<Users> registerUser(UserDTO dto) {
        Users existingUser = userRepository.findByEmail(dto.getEmail());
        if (Objects.nonNull(existingUser)) {
            return new ApiResponseDTO<>("El usuario ya se encuentra registrado", 409, null);
        }

        Users user = new Users();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        Users savedUser = userRepository.save(user);
        if (Objects.nonNull(savedUser)) {
            return new ApiResponseDTO<>("Usuario Registrado Con Éxito", HttpStatus.CREATED.value(), savedUser);
        } else {
            return new ApiResponseDTO<>("No se ha podido registrar el Usuario", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }
    
    private UserDTO convertToDTO(Users user) {
    	ERoles roleName = ERoles.values()[user.getRole().intValue()];

        return new UserDTO(
        		user.getId(),
        		user.getName(),
        		user.getEmail(),
        		roleName,
        		user.getRole()
        );
    }
    
 
}