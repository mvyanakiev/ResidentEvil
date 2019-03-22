package residentevil.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import residentevil.domain.models.serviceModel.UserServiceModel;

public interface UserService extends UserDetailsService {

    boolean registerUser(UserServiceModel userServiceModel);
}
