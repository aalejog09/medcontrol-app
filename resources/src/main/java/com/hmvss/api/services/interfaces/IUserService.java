package com.hmvss.api.services.interfaces;


import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.user.UserDTO;


public interface IUserService {

     UserDTO registerUser(UserDTO userDTO);

     PaginationDTO getAllUserListPageables(int page, int elements);
}
