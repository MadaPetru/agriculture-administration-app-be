package ro.adi.agroadmin.common.exception;

public class NotFoundException extends RuntimeException {

    private final static String FARMING_LAND_NOT_FOUND_BY_TITLE_MESSAGE = "Farming land not found by title: %s";

    private final static String FARMING_LAND_NOT_FOUND_BY_ID_MESSAGE = "Farming land not found by id: %s";

    public static NotFoundException getFarmingLandNotFoundById(Integer id) {
        return new NotFoundException(String.format(FARMING_LAND_NOT_FOUND_BY_ID_MESSAGE, id));
    }

    public static NotFoundException getFarmingLandNotFoundByTitle(String title) {
        return new NotFoundException(String.format(FARMING_LAND_NOT_FOUND_BY_TITLE_MESSAGE, title));
    }

    public NotFoundException(String message) {
        super(message);
    }
}
