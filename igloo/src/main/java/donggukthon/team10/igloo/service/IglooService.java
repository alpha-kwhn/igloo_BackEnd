package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.exception.CustomErrorCode;
import donggukthon.team10.igloo.exception.IglooException;
import donggukthon.team10.igloo.repository.IglooRepository;
import donggukthon.team10.igloo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class IglooService {
    private final IglooRepository iglooRepository;
    private final UserService userService;
    @Transactional
    public void generateIgloo(){
        iglooRepository.save(Igloo.builder()
                        .owner(userService.getLoginUser())
                        .code(generateRandomCode())
                .build()
        );
    }
    public Igloo findById(Long iglooId){
        return iglooRepository.findById(iglooId)
                .orElseThrow(() -> new IglooException(CustomErrorCode.NOT_FOUND_IGLOO));
    }
    public String generateRandomCode() {
        final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int CODE_LENGTH = 6;

        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
}
