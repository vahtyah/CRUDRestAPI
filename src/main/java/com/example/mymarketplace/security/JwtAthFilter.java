//package com.example.mymarketplace.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class JwtAthFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//       final String authHeader = request.getHeader("Authorization");
//       final String login;
//       final String jwtToken;
//
//       if(authHeader == null || !authHeader.startsWith("Bearer ")) {
//           filterChain.doFilter(request, response);
//              return;
//       }
//       jwtToken = authHeader.substring(7);
//       login = "user";
//       if(login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//           UserDetails userDetails = userDetailsService.loadUserByUsername(login);
//           if(jwtUtil.validateToken(jwtToken, userDetails)) {
//               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//           }
//       }
//    }
//}
//
//
