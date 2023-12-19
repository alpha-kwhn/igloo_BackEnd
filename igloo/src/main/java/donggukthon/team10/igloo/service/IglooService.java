package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.dto.user.response.IglooPageResponseDTO;
import donggukthon.team10.igloo.exception.CustomErrorCode;
import donggukthon.team10.igloo.exception.IglooException;
import donggukthon.team10.igloo.repository.IglooRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class IglooService {
    private final IglooRepository iglooRepository;
    private final UserService userService;
    private final VisitService visitService;
    @Transactional
    public void generateIgloo(User savedUser){
        iglooRepository.save(Igloo.builder()
                .owner(savedUser)
                .code(generateRandomCode())
                .build()
        );
    }
    public Igloo findById(Long iglooId){
        return iglooRepository.findById(iglooId)
                .orElseThrow(() -> new IglooException(CustomErrorCode.NOT_FOUND_IGLOO));
    }
    public Igloo findByCode(String code){
        return iglooRepository.findByCode(code)
                .orElseThrow(() -> new IglooException(CustomErrorCode.NOT_FOUND_IGLOO));
    }
    public Igloo findMyIgloo(User user){
        return iglooRepository.findByOwner(user)
                .orElseThrow(() -> new IglooException(CustomErrorCode.NOT_FOUND_IGLOO));
    }
    public IglooPageResponseDTO showIglooInfo(String code){
        System.out.println("result: " + iglooRepository.existsByCode(code));
        Igloo findIgloo = findByCode(code);
        User owner = findIgloo.getOwner();
        User loginUser = userService.getLoginUser();
        Igloo loginIgloo = findMyIgloo(loginUser);
        if (!owner.equals(loginUser))
            visitService.saveRecord(loginIgloo, findIgloo);

        return IglooPageResponseDTO.builder()
                .nickname(owner.getNickname())
                .code(findIgloo.getCode())
                .info(owner.getInfo())
                .owner(owner.equals(loginUser))
                .build();
    }
    public String generateRandomCode() {
        final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int CODE_LENGTH = 6;

        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();

        while(true){
            for (int i = 0; i < CODE_LENGTH; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                char randomChar = CHARACTERS.charAt(randomIndex);
                codeBuilder.append(randomChar);
            }
            if (!iglooRepository.existsByCode(codeBuilder.toString()))
                break;
        }
        return codeBuilder.toString();
    }
}
