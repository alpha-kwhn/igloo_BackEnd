package donggukthon.team10.igloo.common;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        int code,
        String message,
        T data
) {
    public static <T> ApiResponseBuilder<T> nullDataBuilder() {
        return new ApiResponseBuilder<T>().data(null);
    }
}
