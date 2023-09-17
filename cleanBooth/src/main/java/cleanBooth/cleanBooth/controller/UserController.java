package cleanBooth.cleanBooth.controller;

import cleanBooth.cleanBooth.domain.User;
import cleanBooth.cleanBooth.repository.UserRepository;
import cleanBooth.cleanBooth.service.AuthTokensGenerator;
import cleanBooth.cleanBooth.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
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
        Long memberId = authTokensGenerator.extractMemberId(accessToken);
        Optional<User> userOptional = userRepository.findById(memberId);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            // 값이 존재하지 않을 때 처리
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/logout/{accessToken}")
    public ResponseEntity<String> logout(@PathVariable String accessToken) {
        String subject = jwtTokenProvider.extractSubject(accessToken);
        String newToken = jwtTokenProvider.regenerateToken(subject);
        return ResponseEntity.ok(newToken); // 새로운 토큰을 반환합니다.
    }
}
