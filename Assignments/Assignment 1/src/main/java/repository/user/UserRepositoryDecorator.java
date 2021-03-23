package repository.user;

public abstract class UserRepositoryDecorator implements UserRepository{

    protected UserRepository decoratedRepository;

    public UserRepositoryDecorator(UserRepository userRepository) {

        this.decoratedRepository = userRepository;
    }

}
