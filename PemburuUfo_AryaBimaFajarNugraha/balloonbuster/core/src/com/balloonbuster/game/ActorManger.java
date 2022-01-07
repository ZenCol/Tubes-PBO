package com.balloonbuster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;



public class ActorManger {

    public static BaseActor createActor(Stage stage, String pathToTexture, float x, float y)
    {
        return createActor(stage, pathToTexture, x, y, true);
    }

    public static BaseActor createActor(Stage stage, String pathToTexture, float x, float y, boolean visible)
    {
        BaseActor actor = new BaseActor();
        actor.setTexture(new Texture(
                Gdx.files.internal(pathToTexture)
        ));
        actor.setOrigin(actor.getWidth() / 2, actor.getHeight() / 2);
        actor.setPosition(x - actor.getOriginX(),y - actor.getOriginY());
        actor.setVisible(visible);
        stage.addActor(actor);

        return actor;
    }
}
