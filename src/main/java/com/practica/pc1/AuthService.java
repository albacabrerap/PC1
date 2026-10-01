package com.practica.pc1;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthService(AccountRepository accountRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager,
                       ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    public Account convertirDtoAEntidad(SignUpRequest request) {
        return modelMapper.map(request, Account.class);
    }

    public UserInfoDto convertirEntidadADto(Account account) {
        return modelMapper.map(account, UserInfoDto.class);
    }

    public TokenResponse signUp(SignUpRequest request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExists("Email '" + request.getEmail() + "' is already registered.");
        }
        Account account = convertirDtoAEntidad(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        Account savedAccount = accountRepository.save(account);

        String token = jwtService.generateToken(savedAccount);
        return new TokenResponse(token);
    }


    public TokenResponse signIn(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        Account account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el email: " + username));

        String token = jwtService.generateToken(account);
        return new TokenResponse(token);
    }
}