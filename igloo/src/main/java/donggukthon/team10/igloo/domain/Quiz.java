package donggukthon.team10.igloo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Quiz {
    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "igloo_id")
    private Igloo igloo;
    private String question;
    private String answer;
    private String optionFirst;
    private String optionSecond;
    private String optionThird;
    private String optionFourth;
    @Builder
    public Quiz(Igloo igloo, String question, String answer, String optionFirst, String optionSecond, String optionThird, String optionFourth) {
        this.igloo = igloo;
        this.question = question;
        this.answer = answer;
        this.optionFirst = optionFirst;
        this.optionSecond = optionSecond;
        this.optionThird = optionThird;
        this.optionFourth = optionFourth;
    }
}
