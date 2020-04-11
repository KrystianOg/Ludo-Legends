package states;

import Entities.ui.ClickListener;
import Entities.ui.UIImageButton;
import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class MenuState extends State{

    UIImageButton game;
    UIImageButton settings;


    public MenuState(Handler handler){
    super(handler);

    this.game=new UIImageButton((1000 / 2) - (350 / 2), 200, 350, 90, Assets.game_button, new ClickListener() {
        @Override
        public void onClick() {
            State.setState(handler.getGame().gamestate);
        }
    });

    settings=new UIImageButton((1000 / 2) - (350 / 2), 400, 350, 90, Assets.settings_button, new ClickListener() {
        @Override
        public void onClick() {

        }
    });

    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
