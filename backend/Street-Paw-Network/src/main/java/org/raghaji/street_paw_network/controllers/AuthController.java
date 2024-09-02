package org.raghaji.street_paw_network.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.raghaji.street_paw_network.models.BlacklistedToken;
import org.raghaji.street_paw_network.models.ERole;
import org.raghaji.street_paw_network.models.Role;
import org.raghaji.street_paw_network.models.User;
import org.raghaji.street_paw_network.payload.request.LoginRequest;
import org.raghaji.street_paw_network.payload.request.SignupRequest;
import org.raghaji.street_paw_network.payload.response.JwtResponse;
import org.raghaji.street_paw_network.payload.response.MessageResponse;
import org.raghaji.street_paw_network.repository.BlacklistedTokenRepository;
import org.raghaji.street_paw_network.repository.RoleRepository;
import org.raghaji.street_paw_network.repository.UserRepository;
import org.raghaji.street_paw_network.security.jwt.JwtUtils;
import org.raghaji.street_paw_network.services.Convertto;
import org.raghaji.street_paw_network.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  BlacklistedTokenRepository blacklistedTokenRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  private Convertto convertto;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
    if (user.isPresent()) {
      try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
      }

    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not found.");
    }

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {

      return new ResponseEntity<String>("Username is already taken!", HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity<String>("Email is already in use!", HttpStatus.BAD_REQUEST);
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
      if (userRole.isPresent()) {
        roles.add(userRole.get());
      }
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
            if (adminRole.isPresent()) {
              roles.add(adminRole.get());
            }
            break;
          case "user":
            Optional<Role> usnRole = roleRepository.findByName(ERole.ROLE_USER);
            if (usnRole.isPresent()) {
              roles.add(usnRole.get());
            }
            break;
        }
      });
    }

    boolean isAdmin = roles.stream().anyMatch(role -> role.getName().equals(ERole.ROLE_ADMIN));
    if (isAdmin) {
      return new ResponseEntity<String>("You are not allowed to register as Admin", HttpStatus.FORBIDDEN);
    }
      user.setRoles(roles);
      userRepository.save(user);
      return new ResponseEntity<String>("User registered successfully!", HttpStatus.OK);
  }

  @PostMapping("/signupadmin")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) {    if (userRepository.existsByUsername(signUpRequest.getUsername())) {

    return new ResponseEntity<String>("Username is already taken!", HttpStatus.BAD_REQUEST);
  }

  if (userRepository.existsByEmail(signUpRequest.getEmail())) {
    return new ResponseEntity<String>("Email is already in use!", HttpStatus.BAD_REQUEST);
  }

  // Create new user's account
  User user = new User(signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()));

  Set<String> strRoles = signUpRequest.getRole();
  Set<Role> roles = new HashSet<>();

  if (strRoles == null) {
    Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
    if (userRole.isPresent()) {
      roles.add(userRole.get());
    }
  } else {
    strRoles.forEach(role -> {
      switch (role) {
        case "admin":
          Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
          if (adminRole.isPresent()) {
            roles.add(adminRole.get());
          }
          break;
        case "user":
          Optional<Role> usnRole = roleRepository.findByName(ERole.ROLE_USER);
          if (usnRole.isPresent()) {
            roles.add(usnRole.get());
          }
          break;
      }
    });
  }
    user.setRoles(roles);
    userRepository.save(user);
    return new ResponseEntity<String>("User registered successfully!", HttpStatus.OK);
  }

  @GetMapping("logout")
  @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    String token = extractToken(request);
    if (token != null) {
      BlacklistedToken blacklistedToken = new BlacklistedToken();
      blacklistedToken.setToken(token);
      blacklistedTokenRepository.save(blacklistedToken);
    }
    return ResponseEntity.ok("Logout successful");
  }

  private String extractToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  @GetMapping("userdetails")
  @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
  public ResponseEntity<?> getUserDetails(HttpServletRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      // Now you can use userDetails to get the user's information
      return ResponseEntity.ok(userDetails);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
    }
  }

  @PostMapping("delete/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      userRepository.deleteById(id);
      boolean isDeleted = userRepository.findById(id).isEmpty();
      if (isDeleted) {
        return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } else {
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

  }

  @PostMapping("resetpassword")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> resetPassword(@RequestBody Map<String, Object> request) {
    Optional<User> user = userRepository.findByUsername(((String) request.get("username")));
    String password = encoder.encode(((String) request.get("password")));
    if (user.isPresent()) {
      int ab = userRepository.resetPassword(password, user.get().getId());
      return new ResponseEntity<>(ab, HttpStatus.ACCEPTED);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @GetMapping("user/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> getUserById(@PathVariable Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return new ResponseEntity<UserDetailsImpl>(UserDetailsImpl.build(user.get()), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("all")
  public ResponseEntity<?> getAllUsers() {
    List<User> allUsers = userRepository.findAll();
    List<UserDetailsImpl> userDetails = allUsers.stream()
        .map(user -> convertto.converttoDetailsImpl(user))
        .collect(Collectors.toList());
    return new ResponseEntity<List<UserDetailsImpl>>(userDetails, HttpStatus.OK);
  }

}