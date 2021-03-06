package screen;

import main.*;
import misc.JeremysBox2DTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * Main menu of the Smash game.
 */
public class Settings implements Screen {
    private Smash game;
    private ImageButton fullscreen, back;
    private Stage stage;
    private Skin skin;
    private TextureAtlas atlas;
    private boolean full;

    /**
     * Creates a new Settings screen. Defines and positions various buttons used.
     * 
     * @param gameRef
     *            Game linked to.
     */
    public Settings(Smash gameRef) {
	game = gameRef;

	atlas = new TextureAtlas("resource/settings/settings.pack");
	skin = new Skin(atlas);
	fullscreen = new ImageButton(skin.getDrawable("fullscreen-no"),
		skin.getDrawable("fullscreen-yea"),
		skin.getDrawable("fullscreen-yea"));
	back = new ImageButton(skin.getDrawable("back"),
		skin.getDrawable("back-pressed"));
	full = false;

	back.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		game.setScreen(Smash.menu);
	    }
	});

	fullscreen.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		if (!full) {
		    Gdx.graphics.setDisplayMode(1280, 720, true);
		    full = true;
		} else {
		    Gdx.graphics.setDisplayMode(1280, 720, false);
		}
	    }
	});
	stage = new Stage();
	stage.addActor(back);
	back.setX(10 * 4);
	back.setY(155 * 4);
	stage.addActor(fullscreen);
	fullscreen.setX(20 * 4);
	fullscreen.setY(120 * 4);

    }

    /**
     * Renders Menu. Clears screen, processes Stage action, and draws Stage.
     */
    @Override
    public void render(float arg0) {
	float g = (float) 140 / (float) 255;
	Gdx.gl.glClearColor(g, g, g, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	stage.act();
	stage.draw();

	if (Gdx.input.isKeyJustPressed(Keys.T))
	    Smash.swapTheme();
    }

    @Override
    public void dispose() {
	game.dispose();
	stage.dispose();
	atlas.dispose();
	skin.dispose();
    }

    @Override
    public void show() {
	Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
    }

}
