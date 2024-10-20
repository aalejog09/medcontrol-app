package com.hmvss.api.services.interfaces;


import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.dto.user.UserDTO;


public interface IUserService {

     UserDTO registerUser(PersonalDataDTO personalDataDTO, Long roleId);

     UserDTO updateUserPersonalData(UserDTO userDTO);

     PaginationDTO getAllUserListPageables(int page, int elements);
}
