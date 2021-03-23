package launcher;

import controller.AdminController;
import controller.EmployeeController;
import controller.LoginController;
import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.AuthenticationService;
import service.AuthenticationServiceMySQL;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

import java.sql.Connection;

public class ComponentFactory {
    private final LoginView loginView;
    private final AdminView adminView;
    private final EmployeeView employeeView;

    private final LoginController loginController;
    private final AdminController adminController;
    private final EmployeeController employeeController;

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final ClientService clientService;
    private final AccountService accountService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DatabaseConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.accountRepository = new AccountRepositoryMySQL(connection);

        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.userService = new UserServiceImpl(this.userRepository, this.rightsRolesRepository);
        this.clientService = new ClientServiceImpl(this.clientRepository);
        this.accountService = new AccountServiceImpl(this.accountRepository);

        this.loginView = new LoginView();
        this.adminView = new AdminView();
        this.employeeView = new EmployeeView();

        this.loginController = new LoginController(loginView, authenticationService);
        this.adminController = new AdminController(adminView, userService);
        this.employeeController = new EmployeeController(employeeView, clientService, accountService);

    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public AdminView getAdminView() {return adminView;}

    public EmployeeView getEmployeeView(){return employeeView;}

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }
    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
