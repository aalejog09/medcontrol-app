package com.hmvss.api.services;

import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.persistence.mapper.UserMapper;
import com.hmvss.api.persistence.model.PersonalData;
import com.hmvss.api.persistence.model.Role;
import com.hmvss.api.persistence.model.User;
import com.hmvss.api.persistence.model.UserRole;
import com.hmvss.api.persistence.repository.user.IUserPagSortRepository;
import com.hmvss.api.persistence.repository.user.IUserRepository;
import com.hmvss.api.services.interfaces.IPersonalDataService;
import com.hmvss.api.services.interfaces.IRoleService;
import com.hmvss.api.services.interfaces.IUserService;
import com.hmvss.api.services.interfaces.IUserRoleService;
import com.hmvss.api.util.Utility;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserPagSortRepository userPagSortRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IPersonalDataService personalDataService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PaginationService paginationService;

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
    public UserDTO registerUser(PersonalDataDTO personalDataDTO, Long roleId) {

        PersonalData personalData = personalDataService.register(personalDataDTO);
        if(personalData==null){
            throw new APIException(APIError.DB_SAVING_ERROR);
        }

        User newUser= new User();
        String password = utility.passwordGenerator();
        log.info("password {}",password);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setLocked(false);
        newUser.setExpired(false);
        newUser.setEnabled(true);//activo
        newUser.setCredentialExpired(true);//passwordExpirado
        newUser.setUsername(personalData.getContact().getEmail());
        newUser.setPersonalData(personalData);
        User savedUser = userRepository.save(newUser);
        Role role = roleService.getRoleById(roleId);
        UserRole userRoles=userRoleService.assingRoleToUser(newUser, role);
        //setear password oculto para no mostrarlo
        savedUser.setPassword("********");
        return userMapper.toUserDTO(savedUser);
    }


    public PersonalData getUserDataByDNI(PersonalData personalData){
        PersonalData personalDataFound = personalDataService.getPersonalDataEntityByDniAndBornDate(
                personalData.getIdentificationDocumentNumber()
                ,personalData.getBornDate());
        if(personalDataFound==null){
            throw new APIException(APIError.NOT_FOUND);
        }
        return personalDataFound;
    }

    @Override
    public UserDTO updateUserPersonalData(UserDTO userDTO) {
        log.info("userDTO:{}",userDTO);
        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(()-> new APIException(APIError.NOT_FOUND));
        PersonalData personalData = getUserDataByDNI(user.getPersonalData());
        //user.setPersonalData(personalData);
        PersonalDataDTO updatePersonalData=userDTO.getPersonalData();
        updatePersonalData.setId(personalData.getId());
        user.setUsername(updatePersonalData.getContact().getEmail());
        personalDataService.update(userDTO.getPersonalData());
        userRepository.save(user);
        return userMapper.toUserDTO(user);
    }


}
