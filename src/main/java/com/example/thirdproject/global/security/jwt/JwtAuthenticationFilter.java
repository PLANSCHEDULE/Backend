package com.example.thirdproject.global.security.jwt;

import com.example.thirdproject.auth.service.RefreshTokenService;
import com.example.thirdproject.global.security.AuthErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = resolveToken(request);

        try {
            if(token != null && jwtTokenProvider.validateToken(token)) {
                // 로그아웃 관리
                // redis에서 토큰 자체를 키로 조회했을 때 값이 있다면 로그아웃된 토큰
                String isLogout = refreshTokenService.getRefreshToken(token);

                if(isLogout == null) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    request.setAttribute("exception", AuthErrorCode.LOGOUT_TOKEN.getCode() );

                }

            }
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", AuthErrorCode.INVALID_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", AuthErrorCode.EXPIRED_ACCESS_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", AuthErrorCode.UNSUPPORTED_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", AuthErrorCode.ILLEGAL_TOKEN.getCode());
        } catch (Exception e) {
            request.setAttribute("exception", AuthErrorCode.UNAUTHORIZED.getCode());
        }

        filterChain.doFilter(request, response);
    }

    // jwt 토큰 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

}
