package com.hmvss.auth.services.interfaces;


import com.hmvss.auth.dto.token.RefreshTokenDTO;
import com.hmvss.auth.dto.token.TokenRequestDTO;
import com.hmvss.auth.dto.token.TokenResponseDTO;

public interface ILoginService {

    public TokenResponseDTO getToken(TokenRequestDTO userRequest);

    public TokenResponseDTO getRefreshToken(RefreshTokenDTO request);
}
