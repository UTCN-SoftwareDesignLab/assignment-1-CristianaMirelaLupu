package launcher;
import database.Bootstaper;

import java.sql.SQLException;

public class Launcher {
    public static boolean BOOTSTRAP = true;

    public static void main(String[] args) {
        bootstrap();

        ComponentFactory componentFactory = ComponentFactory.instance(true);
        componentFactory.getLoginView().setVisible(true);
        componentFactory.getAdminView().setVisible(false);
        componentFactory.getEmployeeView().setVisible(false);

    }

    private static void bootstrap() {
        if (BOOTSTRAP) {
            try {
                new Bootstaper().execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
