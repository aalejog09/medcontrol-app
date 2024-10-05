package com.hmvss.api.services.interfaces;


import com.hmvss.api.dto.pagination.PaginationDTO;


public interface IUserService {

    public PaginationDTO getAllUserListPageables(int page, int elements);
}
