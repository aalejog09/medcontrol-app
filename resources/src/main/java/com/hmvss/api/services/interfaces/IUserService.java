package com.hmvss.api.services.interfaces;


import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.persistence.model.User;


public interface IUserService {

     User registerUser(UserDTO userDTO);

     PaginationDTO getAllUserListPageables(int page, int elements);
}
