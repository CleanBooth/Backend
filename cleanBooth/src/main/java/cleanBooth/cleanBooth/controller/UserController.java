package cleanBooth.cleanBooth.controller;

import cleanBooth.cleanBooth.domain.User;
import cleanBooth.cleanBooth.repository.UserRepository;
import cleanBooth.cleanBooth.service.AuthTokensGenerator;
import cleanBooth.cleanBooth.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class UserController {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{accessToken}")
    public ResponseEntity<User> findByAccessToken(@PathVariable String accessToken) {
        if (jwtTokenProvider.isBlacklisted(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long memberId = authTokensGenerator.extractMemberId(accessToken);
        Optional<User> userOptional = userRepository.findById(memberId);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/logout/{accessToken}")
    public ResponseEntity<Void> logout(@PathVariable String accessToken) {
        jwtTokenProvider.addToBlacklist(accessToken);
        return ResponseEntity.ok().build();
    }
}
