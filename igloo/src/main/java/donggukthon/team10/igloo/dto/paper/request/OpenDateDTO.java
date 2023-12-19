package donggukthon.team10.igloo.dto.paper.request;

import lombok.Builder;

import java.util.Date;

@Builder
public record OpenDateDTO(
        Date date
) {}
