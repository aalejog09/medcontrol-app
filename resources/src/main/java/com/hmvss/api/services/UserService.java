package com.hmvss.api.services;

import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.persistence.mapper.UserMapper;
import com.hmvss.api.persistence.model.User;
import com.hmvss.api.persistence.repository.user.IUserPagSortRepository;
import com.hmvss.api.persistence.repository.user.IUserRepository;
import com.hmvss.api.services.interfaces.IUserService;
import com.hmvss.api.util.Utility;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IUserPagSortRepository userPagSortRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PaginationService paginationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Utility utility;




    @Override
    public PaginationDTO  getAllUserListPageables(int page, int elements) {
        page--;
        if (elements <= 0 || page < 0) {
            throw new APIException(APIError.VALIDATION_ERROR);
        }
        Page<User> userPageable = getAllPageable(page, elements);
        if(userPageable.getContent().isEmpty()){
            throw new APIException(APIError.NOT_FOUND);
        }
        return getUserListPaginated(userPageable, userMapper.toUserDTOList(userPageable.getContent()));
    }

    public PaginationDTO  getUserListPaginated(Page<?> userPageable, List<?> data){
        return paginationService.getPageableData(userPageable, data);
    }


    public Page<User> getAllPageable(int page, int elements) throws APIException {
        Pageable pageRequest = PageRequest.of(page, elements);
        Page<User> userPageable = null;
        try {
            userPageable = userPagSortRepository.findAll(pageRequest);
        } catch (Exception e) {
            throw new APIException(APIError.VALIDATION_ERROR);
        }
        return userPageable;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User newUser = userMapper.toUser(userDTO);
        String password = utility.passwordGenerator();
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setLocked(false);
        newUser.setExpired(false);
        newUser.setEnabled(true);//activo
        newUser.setCredentialExpired(true);//passwordExpirado
        newUser.setUsername(userDTO.getPersonalData().getContact().getEmail());
        User savedUser = userRepository.save(newUser);
        savedUser.setPassword("********");


        return userMapper.toUserDTO(savedUser);
    }
}
