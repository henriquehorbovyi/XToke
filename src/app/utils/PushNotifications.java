package app.utils;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * Created by henry on 09/04/17.
 */
public class PushNotifications {

    public Notifications notify(String title,String text, String iconURL){
        ImageView icon = new ImageView( new Image(iconURL) );
        icon.setFitHeight(50);
        icon.setFitWidth(50);

        Notifications notification = Notifications.create()
                .title(title)
                .text(text)
                .graphic(icon)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(actionEvent -> {
                    System.out.println("Ok!");
                });
        return notification;
    }


}
