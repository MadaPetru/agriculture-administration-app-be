package ro.adi.agroadmin.user.exception;

public class UserEmailAlreadyUsedException extends RuntimeException{

    public UserEmailAlreadyUsedException(String message){
        super(message);
    }
}
