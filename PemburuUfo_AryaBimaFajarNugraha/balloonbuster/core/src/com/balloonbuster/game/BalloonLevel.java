package com.balloonbuster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

//ballon level ke base screen itu generalication
//ballon level ke base BaseActor itu compositon

public class BalloonLevel extends BaseScreen {
    protected final int mapWidth = 640;
    protected final int mapHeight = 640;
    private BaseActor background;
    private float spawnTimer;
    private float spawnInterval;
    private int popped;
    private int escaped;
    private int clickCount;
    private Label poppedLabel;
    private Label escapedLabel;
    private Label hitRatioLabel;
    private int popToWin = 50;
    private int escapedToFail = 15;

    public BalloonLevel(Game game) {

        super(game);
    }

    @Override
    protected void create() {
        background = new BaseActor();
        background.setTexture(new Texture(Gdx.files.internal("bg.jpg") ));
        background.setOrigin(background.getWidth() / 2, background.getHeight() / 2);
        background.setPosition((viewWidth / 2) - background.getOriginX(),(viewHeight / 2) - background.getOriginY());
        background.setVisible(true);
        mainStage.addActor(background);

        spawnTimer = 0;
        spawnInterval = 0.5f;

        BitmapFont font = new BitmapFont();
        LabelStyle style  = new LabelStyle(font, Color.NAVY);

        popped = 0;
        poppedLabel = new Label("Popped: 0", style);
        poppedLabel.setFontScale(2);
        poppedLabel.setPosition(20, 440);
        uiStage.addActor(poppedLabel);

        escaped = 0;
        escapedLabel = new Label("Escaped: 0", style);
        escapedLabel.setFontScale(2);
        escapedLabel.setPosition(220, 440);
        uiStage.addActor(escapedLabel);

        clickCount = 0;
        hitRatioLabel = new Label("Hit ratio: ---", style);
        hitRatioLabel.setFontScale(2);
        hitRatioLabel.setPosition(420,440);
        uiStage.addActor(hitRatioLabel);
    }

    @Override
    protected void update(float dt) {
        spawnTimer += dt;

        if (spawnTimer > spawnInterval) {

            spawnTimer -= spawnInterval;
            final Balloon b = new Balloon(this);
            b.addListener(
                    new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            popped++;
                            b.remove();
                            return true;
                        }
                    }
            );
            mainStage.addActor(b);
        }


        poppedLabel.setText("Popped: " + popped);
        escapedLabel.setText("Escaped: " + escaped);

        if (clickCount > 0) {
            int percent = (int) (100 * popped / clickCount);
            hitRatioLabel.setText("Hit ratio: " + percent + "%");
        }

        if (popped >= popToWin) {
            game.setScreen(new BalloonMenu(game));
        }

        if (escaped >= escapedToFail) {
            game.setScreen(new BalloonMenu(game));
        }
    }

    public void escapedInc() {
        escaped++;
        clickCount++;
    }
}
